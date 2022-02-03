package com.zied.nasri.www_sms.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Message {

    @NonNull
    @PrimaryKey
    private String pkey;
    @ColumnInfo(name = "www_body")
    private boolean wwwBody;
    @ColumnInfo(name = "www_locked")
    private boolean wwwLocked;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "body")
    private String body;
    @ColumnInfo(name = "creator")
    private String creator;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "date_sent")
    private String date_sent;
    @ColumnInfo(name = "error_code")
    private String error_code;
    @ColumnInfo(name = "locked")
    private String locked;

    @ColumnInfo(name = "person")
    private String person;
    @ColumnInfo(name = "protocol")
    private String protocol;
    @ColumnInfo(name = "read")
    private String read;
    @ColumnInfo(name = "replay_path_present")
    private String replay_path_present;
    @ColumnInfo(name = "seen")
    private String seen;
    @ColumnInfo(name = "service_center")
    private String service_center;
    @ColumnInfo(name = "status")
    private String status;
    @ColumnInfo(name = "subject")
    private String subject;
    @ColumnInfo(name = "subscription_id")
    private String subscription_id;
    @ColumnInfo(name = "thread_id")
    private String thread_id;
    @ColumnInfo(name = "type")
    private String type;

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public boolean isWwwBody() {
        return wwwBody;
    }

    public void setWwwBody(boolean wwwBody) {
        this.wwwBody = wwwBody;
    }

    public boolean isWwwLocked() {
        return wwwLocked;
    }

    public void setWwwLocked(boolean wwwLocked) {
        this.wwwLocked = wwwLocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getReplay_path_present() {
        return replay_path_present;
    }

    public void setReplay_path_present(String replay_path_present) {
        this.replay_path_present = replay_path_present;
    }

    public String getSeen() {
        return seen;
    }

    public void setSeen(String seen) {
        this.seen = seen;
    }

    public String getService_center() {
        return service_center;
    }

    public void setService_center(String service_center) {
        this.service_center = service_center;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getThread_id() {
        return thread_id;
    }

    public void setThread_id(String thread_id) {
        this.thread_id = thread_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
