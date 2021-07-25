package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {

    //CREATE
    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileData) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int createNewFile(File file);

    //READ
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFileById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFilesForUser(Integer userId);

    //UPDATE

    //DELETE
    @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
    void deleteFileById(Integer fileId);

}
