package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

    private Integer credentialId;
    private Integer userId;
    private String url;
    private String username;
    private String key; //salt
    private String password;

}
