package com.example.study.mapper;

import com.example.study.dto.QuestionDTO;
import com.example.study.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into Question(TITLE,DESCRIPTION,GMT_CREATE,CREATOR,TAG) values(#{title},#{description},#{gmtCreate},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from QUESTION limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();
}
