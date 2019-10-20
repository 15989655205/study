package com.example.study.model;

import lombok.Data;

@Data
public class Question {
    private Long ID;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private String tag;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;

}
