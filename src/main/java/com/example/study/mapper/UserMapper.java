package com.example.study.mapper;

import com.example.study.dto.GithubUser;
import com.example.study.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String value);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Long creator);

    @Select("select * from user where account_id=#{account_id}")
    User findByAccountId(@Param("account_id") Long accountId);

    @Select("update user set token=#{token} where account_id=#{accountId}")
    User updateToken(User user);
}
