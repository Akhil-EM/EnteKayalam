package com.example.entenadu2;

public class FireBaseDataInsert {
    String name;
    String mobile;
    String type;
    String parent;

    public FireBaseDataInsert(String name, String mobile, String type, String parent) {
        this.name = name;
        this.mobile = mobile;
        this.type = type;
        this.parent = parent;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public FireBaseDataInsert(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FireBaseDataInsert() {
        //defult constructor
    }
}
