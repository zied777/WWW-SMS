package com.zied.nasri.www_sms.Tools;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.ImageView;

import me.fahmisdk6.avatarview.AvatarView;

public final class ContactTools {

    private static final String[] PHOTO_ID_PROJECTION = new String[] {
            ContactsContract.Contacts.PHOTO_ID
    };

    private static final String[] PHOTO_BITMAP_PROJECTION = new String[] {
            ContactsContract.CommonDataKinds.Photo.PHOTO
    };

    private final AvatarView badge;

    private final String phoneNumber;

    private final ContentResolver contentResolver;

    public ContactTools(final Context context, final AvatarView badge, final String phoneNumber) {

        this.badge = badge;
        this.phoneNumber = phoneNumber;
        contentResolver = context.getContentResolver();

    }

    public void addThumbnail() {

        final Integer thumbnailId = fetchThumbnailId();
        /*
        if (thumbnailId != null) {
            final Bitmap thumbnail = fetchThumbnail(thumbnailId);
            if (thumbnail != null) {
                badge.setImageBitmap(thumbnail);
                return;
            }
        }

         */
        if(thumbnailId != null) {
            final Uri uri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, thumbnailId);
            badge.bind(phoneNumber, uri.toString());
            return;
        }
        badge.bind(phoneNumber, "");

    }

    private Integer fetchThumbnailId() {

        final Uri uri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        final Cursor cursor = contentResolver.query(uri, PHOTO_ID_PROJECTION, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        try {
            Integer thumbnailId = null;
            if (cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_ID);
                thumbnailId = cursor.getInt(index);
            }
            return thumbnailId;
        }
        finally {
            cursor.close();
        }

    }

    final Bitmap fetchThumbnail(final int thumbnailId) {

        final Uri uri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, thumbnailId);
        final Cursor cursor = contentResolver.query(uri, PHOTO_BITMAP_PROJECTION, null, null, null);

        try {
            Bitmap thumbnail = null;
            if (cursor.moveToFirst()) {
                final byte[] thumbnailBytes = cursor.getBlob(0);
                if (thumbnailBytes != null) {
                    thumbnail = BitmapFactory.decodeByteArray(thumbnailBytes, 0, thumbnailBytes.length);
                }
            }
            return thumbnail;
        }
        finally {
            cursor.close();
        }

    }

}
