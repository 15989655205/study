package com.example.study.model;

import lombok.Data;

@Data
public class Question {
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmtModified;
    private String creator;
    private String tag;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;

}
