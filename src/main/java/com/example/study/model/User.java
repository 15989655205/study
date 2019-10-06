package com.example.study.model;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private Long accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
