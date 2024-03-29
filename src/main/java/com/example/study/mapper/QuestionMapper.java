package com.example.study.mapper;

import com.example.study.dto.QuestionDTO;
import com.example.study.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into Question(TITLE,DESCRIPTION,GMT_CREATE,CREATOR,TAG) values(#{title},#{description},#{gmtCreate},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from QUESTION limit #{offset},#{size}")
    List<Question> list(@Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question")
    Integer count();

    @Select("select * from QUESTION where creator=#{userId} limit #{offset},#{size}")
    List<Question> listByUserId(@Param(value = "userId") Long userId, @Param(value = "offset") Integer offset,@Param(value = "size") Integer size);

    @Select("select count(1) from question where creator=#{userId}")
    Integer countByUserId(@Param(value = "userId") Long userId);

    @Select("select * from question where  id=#{id} ")
    Question getById(@Param("id") Integer id);

    @Update("update question set title=#{title},description=#{description},gmt_modified=#{gmtModified} where id=#{ID}")
    void update(Question question);
}
