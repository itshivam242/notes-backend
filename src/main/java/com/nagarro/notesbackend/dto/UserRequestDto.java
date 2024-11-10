package com.nagarro.notesbackend.dto;

import java.io.Serializable;

public class UserRequestDto implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String emailId;
    private String password;

    public UserRequestDto() {
    }

    public UserRequestDto(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}