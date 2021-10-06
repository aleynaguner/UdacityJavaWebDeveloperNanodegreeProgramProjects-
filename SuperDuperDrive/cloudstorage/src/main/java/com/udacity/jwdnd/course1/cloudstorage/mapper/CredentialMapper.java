package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    //CREATE
    @Insert("INSERT INTO CREDENTIALS (url, username, key, password, userId) VALUES(#{url}, #{username}, #{key}, #{password}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credential credential);

    //READ
    @Select("SELECT * FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    Credential getCredentialById(Integer credentialId);

    @Select("SELECT * FROM CREDENTIALS WHERE userId = #{userId}")
    List<Credential> getCredentialsForUser(Integer userId);

    //UPDATE
    @Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password}, userId = #{userId} WHERE credentialId = #{credentialId}")
    int updateCredential(Integer credentialId, String url, String username, String key, String password, Integer userId);

    //DELETE
    @Delete("DELETE FROM CREDENTIALS WHERE credentialId = #{credentialId}")
    int deleteCredentialById(Integer credentialId);


}
