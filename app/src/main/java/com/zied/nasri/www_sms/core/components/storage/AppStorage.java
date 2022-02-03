package com.zied.nasri.www_sms.core.components.storage;

import android.content.Context;

import com.zied.nasri.www_sms.Tools.DateTools;
import com.zied.nasri.www_sms.Tools.SmsTools;
import com.zied.nasri.www_sms.core.components.data.IDataRequest;
import com.zied.nasri.www_sms.database.daos.MessageDao;
import com.zied.nasri.www_sms.database.database.AppDatabase;
import com.zied.nasri.www_sms.database.entities.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppStorage {

    private static AppStorage appStorage;
    private Context context;
    private MessageDao messageDao;

    private AppStorage(Context context){
        this.context = context;
        messageDao = AppDatabase.getInstance(context).messageDao();
    }

    public static AppStorage getInstance(Context context){
        if(appStorage == null){
            appStorage = new AppStorage(context);
        }

        return appStorage;
    }

    public void getAll(IDataRequest iDataRequest){

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Message> messages = SmsTools.getAllSms(context);
                for(Message message : messages){
                    if(null == messageDao.findById(message.getPkey())){
                        messageDao.insert(message);
                    }
                }

                messages = new ArrayList<>();
                List<String> threadIds = messageDao.getGroupedByThread();
                for(String i : threadIds){
                    messages.add(messageDao.getLastMessageInThread(i));
                }
                Collections.sort(messages, new Comparator<Message>() {
                    public int compare(Message o1, Message o2) {
                        return DateTools.fromLongString(o2.getDate()).compareTo(DateTools.fromLongString(o1.getDate()));
                    }
                });
                iDataRequest.onDataReady(messages);
            }
        }).start();
    }

    public void getAllByThreadId(String threadId, IDataRequest iDataRequest){

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Message> messages = messageDao.getMessagesByThreadIds(threadId);
                iDataRequest.onDataReady(messages);
            }
        }).start();
    }
}
