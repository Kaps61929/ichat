package com.example.chatapplication.Model;

public class Messagemodel {


    String uId,message;
    Long timestamp;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Messagemodel(String uId, String message, Long timestamp) {
        this.uId = uId;
        this.message = message;
        this.timestamp = timestamp;
    }
    public Messagemodel(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }
    public Messagemodel() {

    }




}
