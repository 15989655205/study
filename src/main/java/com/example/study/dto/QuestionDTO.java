package com.example.study.dto;

import com.example.study.model.Question;
import com.example.study.model.User;
import lombok.Data;

@Data
public class QuestionDTO extends Question{
    private User user;
}
