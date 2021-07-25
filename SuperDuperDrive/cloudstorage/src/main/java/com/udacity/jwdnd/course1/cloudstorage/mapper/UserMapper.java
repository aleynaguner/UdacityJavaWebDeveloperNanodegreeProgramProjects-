package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //CREATE
    @Insert("INSERT INTO USERS (username, salt, password, firstname, lastname) VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int createNewUser(User user);

    //READ
    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    User getUserByUserName(String userName);

    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User getUserById(String userId);

}
