package com.example.entenadu2;

public class fireBaseBusInsert {
    String bus_name;
    String to;
    String time;

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public fireBaseBusInsert(String bus_name, String to, String time) {
        this.bus_name = bus_name;
        this.to = to;
        this.time = time;
    }

    public fireBaseBusInsert() {
    }
}
