package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Data;

@Data
public class User {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String username;
    private String salt;
    private String password;

}
