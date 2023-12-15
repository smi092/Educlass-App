package com.example.educlass.model;

public class model1 {
    String ytlink=null,Class,subject;
    public model1()
    {

    }

    public model1(String ytlink1, String aClass, String subject) {
        this.ytlink = ytlink1;
        Class = aClass;
        this.subject = subject;
    }

    public String getYtlink() {
        return ytlink;
    }

    public String getclass() {
        return Class;
    }

    public String getSubject() {
        return subject;
    }



}
