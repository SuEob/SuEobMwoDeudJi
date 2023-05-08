package com.example.sueobmwodeudji.dto;

public class RatingMyClassData {
    protected String class_name;
    protected String teacher_name;

    public RatingMyClassData(String class_name, String teacher_name) {
        this.class_name = class_name;
        this.teacher_name = teacher_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }
}
