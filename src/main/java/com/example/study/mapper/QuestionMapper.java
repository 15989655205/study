package com.example.study.mapper;

import com.example.study.dto.QuestionDTO;
import com.example.study.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into Question(TITLE,DESCRIPTION,GMT_CREATE,CREATOR,TAG) values(#{title},#{description},#{gmt_create},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from Question")
    List<Question> list();
}
