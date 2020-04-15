package com.example.entenadu2;

public class Notification {
    String tittle;
    String content;
    String date;

    public Notification() {
        ///defult constructer needed
    }

    public Notification(String tittle, String content, String date) {
        this.tittle = tittle;
        this.content = content;
        this.date = date;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
