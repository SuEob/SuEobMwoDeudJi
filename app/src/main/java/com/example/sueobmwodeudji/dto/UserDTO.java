package com.example.sueobmwodeudji.dto;

import com.google.firebase.firestore.PropertyName;

public class UserDTO {
    @PropertyName("schoolName")
    private String school_name;

    private String name;

    public UserDTO() {
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
