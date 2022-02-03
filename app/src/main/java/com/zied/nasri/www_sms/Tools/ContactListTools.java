package com.zied.nasri.www_sms.Tools;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.zied.nasri.www_sms.R;
import com.zied.nasri.www_sms.database.entities.Contact;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ContactListTools {

    private static final String TAG = "ContactListTools";

    @SuppressLint("Range")
    public static List<Contact> getContactList(Context context) {

        List<Contact> result = new ArrayList<>();

        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        Log.i(TAG, "Name: " + name);
                        Log.i(TAG, "Phone Number: " + phoneNo);


                        Contact contact = new Contact();
                        contact.setName(name);
                        contact.setPhoneNumber(phoneNo);
                        if(!result.contains(contact)) {
                            result.add(contact);
                        }
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }

        return result;
    }

    public static Bitmap retrieveContactPhoto(Context context, String name) {

        String contactId = null;
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection    = new String[] {ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor people = context.getContentResolver().query(uri, projection, null, null, null);

        int indexName = people.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);

        try {
            people.moveToFirst();
            do {
                String Name = people.getString(indexName);
                if (Name.trim().equalsIgnoreCase(name.trim())) {
                    contactId = people.getString(people.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
                    // break;
                }
            } while (people.moveToNext());
        }catch (Exception e){
            e.printStackTrace();
        }
        /*
        ContentResolver contentResolver = context.getContentResolver();
        String contactId = null;
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CON, Uri.encode(number));

        String[] projection = new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID};

        Cursor cursor =
                contentResolver.query(
                        uri,
                        projection,
                        null,
                        null,
                        null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                contactId = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.PhoneLookup._ID));
            }
            cursor.close();
        }

         */

        Bitmap photo = BitmapFactory.decodeResource(context.getResources(), R.drawable.phone);

        try {
            if(contactId != null) {
                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(context.getContentResolver(),
                        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(contactId)));

                if (inputStream != null) {
                    photo = BitmapFactory.decodeStream(inputStream);
                }

                if(inputStream != null) {
                    inputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return photo;
    }

    public static Contact getContactByNameOrPhoneNumber(String nameOrPhoneNumber, List<Contact> contacts){

        Contact result = null;

        for(Contact c: contacts){

            String name = c.getName();
            String phoneNumber = c.getPhoneNumber();

            if(nameOrPhoneNumber.trim().equalsIgnoreCase(name.trim())){
                return c;
            } else if(nameOrPhoneNumber.trim().equalsIgnoreCase(phoneNumber.trim())){
                return c;
            }
        }
        result = new Contact();
        result.setName(nameOrPhoneNumber);
        result.setPhoneNumber(nameOrPhoneNumber);
        return result;
    }

}
