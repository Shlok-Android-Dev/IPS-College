package com.example.ipscollege.Notification.model;

public class NotificationData {

    private String title;
    public String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationData(String title, String message) {
        this.title = title;
        this.message = message;

    }
}
