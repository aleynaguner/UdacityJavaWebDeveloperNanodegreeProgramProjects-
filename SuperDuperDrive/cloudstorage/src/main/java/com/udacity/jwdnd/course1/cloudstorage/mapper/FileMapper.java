package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    //CREATE
    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int uploadFile(File file);

    //READ
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFileById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFilesForUser(Integer userId);

    //UPDATE
    @Update("UPDATE FILES SET fileName = #{fileName}, contentType = #{contentType}, fileSize = #{fileSize}, fileData = #{fileData} WHERE fileId = #{fileId}")
    int updateFile(Integer fileId, String noteTitle, String noteDescription);

    //DELETE
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    int deleteFileById(Integer fileId);

}
