package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CredentialMapper {

    //CREATE
    @Insert("INSERT INTO CREDENTIALS (url, userName, key, password, userId) VALUES(#{url}, #{userName}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createNewCredential(Note note);

    //READ
    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredentialById(Integer noteId);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getCredentialsForUser(Integer userId);


}
