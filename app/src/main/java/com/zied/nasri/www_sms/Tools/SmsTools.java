package com.zied.nasri.www_sms.Tools;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony;

import com.zied.nasri.www_sms.database.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class SmsTools {

    public static List<Message> getAllSms(Context context) {
        List<Message> lstSms = new ArrayList<Message>();
        ContentResolver cr = context.getContentResolver();

        Cursor c = cr.query(Telephony.Sms.Inbox.CONTENT_URI, // Official CONTENT_URI  from docs
                null, // Select body text
                null, null, Telephony.Sms.Inbox.DEFAULT_SORT_ORDER); // Default sort order);

        int totalSMS = c.getCount();

        if (c.moveToFirst()) {
            for (int i = 0; i < totalSMS; i++) {

                Message message = new Message();
                int addressIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.ADDRESS);
                int bodyIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.BODY);
                int creatorIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.CREATOR);
                int dateIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.DATE);
                int dateSentIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.DATE_SENT);
                int errorCodeIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.ERROR_CODE);
                int lockedIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.LOCKED);
                int personIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.PERSON);
                int protocolIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.PROTOCOL);
                int readIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.READ);
                int replyPathPresentIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.REPLY_PATH_PRESENT);
                int seenIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.SEEN);
                int serviceCenterIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.SERVICE_CENTER);
                int statusIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.STATUS);
                int subjectIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.SUBJECT);
                int subscriptionIdIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.SUBSCRIPTION_ID);
                int threadIdIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.THREAD_ID);
                int typeIndex = c.getColumnIndex(Telephony.TextBasedSmsColumns.TYPE);

                String address = c.getString(addressIndex);
                String body = c.getString(bodyIndex);
                String creator = c.getString(creatorIndex);
                String date=  c.getString(dateIndex);
                String dateSent = c.getString(dateSentIndex);
                String errorCode = c.getString(errorCodeIndex);
                String locked = c.getString(lockedIndex);
                String person = c.getString(personIndex);
                String protocol = c.getString(protocolIndex);
                String read = c.getString(readIndex);
                String replyPathPresent = c.getString(replyPathPresentIndex);
                String seen  =c.getString(seenIndex);
                String serviceCenter = c.getString(serviceCenterIndex);
                String status = c.getString(statusIndex);
                String subject = c.getString(subjectIndex);
                String subscriptionId = c.getString(subscriptionIdIndex);
                String threadId = c.getString(threadIdIndex);
                String type=  c.getString(typeIndex);

                message.setAddress(address);
                message.setBody(body);
                message.setCreator(creator);
                message.setDate(date);
                message.setDate_sent(dateSent);
                message.setError_code(errorCode);
                message.setLocked(locked);
                message.setPerson(person);
                message.setProtocol(protocol);
                message.setRead(read);
                message.setReplay_path_present(replyPathPresent);
                message.setSeen(seen);
                message.setService_center(serviceCenter);
                message.setStatus(status);
                message.setSubject(subject);
                message.setSubscription_id(subscriptionId);
                message.setThread_id(threadId);
                message.setType(type);

                int idIndex = c.getColumnIndex("_id");
                message.setPkey(c.getString(idIndex));
                lstSms.add(message);
                c.moveToNext();
            }
        } else {
            //throw new RuntimeException("You have no SMS in Inbox");
        }
        c.close();

        return lstSms;
    }
}
