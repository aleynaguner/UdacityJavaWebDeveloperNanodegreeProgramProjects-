package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
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
    User getUserByUsername(String userName);

    @Select("SELECT * FROM USERS WHERE username = #{userName}")
    Integer getUserIdByUsername(String username);

    @Select("SELECT * FROM USERS WHERE userid = #{userId}")
    User getUserById(Integer userId);

    //UPDATE
    @Update("UPDATE USERS SET username = #{username}, salt = #{salt}, password = #{password}, firstname = #{firstname}, lastname = #{lastname} WHERE userId = #{userId}")
    Note updateNote(Note note);

    //DELETE
    @Delete("DELETE FROM USERS WHERE userId = #{userId}")
    void deleteUserById(Integer userId);

}
