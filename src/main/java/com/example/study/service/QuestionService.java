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
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer count = questionMapper.count();
        paginationDTO.setPagination(count,page,size);

        if (page<1){
            page=1;
        }

        if (page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }

        Integer offset=size*(page-1);

        List<Question> list = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();


        for(Question question:list){
           User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }


        paginationDTO.setList(questionDTOList);
        return  paginationDTO;
    }
}
