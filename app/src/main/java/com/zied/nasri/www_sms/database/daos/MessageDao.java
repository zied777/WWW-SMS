package com.zied.nasri.www_sms.database.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.zied.nasri.www_sms.database.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> getAll();

    @Query("SELECT thread_id FROM message GROUP BY thread_id  ORDER BY date DESC")
    List<String> getGroupedByThread();

    @Query("SELECT * FROM message WHERE thread_id=:tid ORDER BY date DESC")
    Message getLastMessageInThread(String tid);

    @Query("SELECT * FROM message WHERE thread_id=:tid ORDER BY date ASC")
    List<Message> getMessagesByThreadIds(String tid);


    @Query("SELECT * FROM message WHERE pkey IN (:msgIds)")
    List<Message> loadAllByIds(String[] msgIds);

    @Query("SELECT * FROM message WHERE pkey=:id")
    Message findById(String id);

    @Insert
    void insertAll(Message... msgss);

    @Insert
    void insert(Message msg);

    @Delete
    void delete(Message msg);

    @Update
    void update(Message msg);
}
