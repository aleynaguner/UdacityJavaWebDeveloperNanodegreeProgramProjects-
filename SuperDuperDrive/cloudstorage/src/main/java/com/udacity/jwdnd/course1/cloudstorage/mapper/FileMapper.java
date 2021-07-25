package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {

    //CREATE
    @Insert("INSERT INTO FILES (fileName, contentType, fileSize, userId, fileDate) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileDate})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int createNewFile(File file);

    //READ
    @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
    File getFileById(String fileId);

    @Select("SELECT * FROM FILES WHERE userId = #{userId}")
    List<File> getFilesForUser(String userId);

}
