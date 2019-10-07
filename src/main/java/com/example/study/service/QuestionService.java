package com.example.study.service;

import com.example.study.dto.PaginationDTO;
import com.example.study.dto.QuestionDTO;
import com.example.study.mapper.QuestionMapper;
import com.example.study.mapper.UserMapper;
import com.example.study.model.Question;
import com.example.study.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size ) {

        Integer offset=size*(page-1);

        List<Question> list = questionMapper.list();
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for(Question question:list){
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return  paginationDTO;
    }
}
