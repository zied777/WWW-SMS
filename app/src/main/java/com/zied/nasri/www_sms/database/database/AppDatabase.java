package com.zied.nasri.www_sms.database.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.zied.nasri.www_sms.database.daos.MessageDao;
import com.zied.nasri.www_sms.database.entities.Message;

@Database(entities = {Message.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MessageDao messageDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){

        if(instance == null){
            instance = Room.databaseBuilder(context, AppDatabase.class, "www-sms-database").build();
        }
        return instance;
    }
}
