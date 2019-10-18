package com.example.study.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private Long accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;

    public String getAvatarUrl() {
        if(avatarUrl==null||avatarUrl==""){
            return  "";
        }
        return avatarUrl;
    }

    private String avatarUrl;
}
