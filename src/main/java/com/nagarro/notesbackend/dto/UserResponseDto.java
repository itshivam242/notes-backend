package com.nagarro.notesbackend.dto;

import java.io.Serializable;

public class UserResponseDto implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final String firstName;
    private final String userName;
    private final int id;

    public UserResponseDto(String token, String firstName, String userName,int id) {
        this.token = token;
        this.firstName = firstName;
        this.userName = userName;
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }
    public int getId() {
        return id;
    }
}