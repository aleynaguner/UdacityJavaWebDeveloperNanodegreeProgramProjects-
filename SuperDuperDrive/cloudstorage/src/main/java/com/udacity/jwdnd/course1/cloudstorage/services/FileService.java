package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public void createNewFile(File file, String username) {
        Integer userId = userMapper.getUserIdByUsername(username);
        file.setUserId(userId);
        fileMapper.createNewFile(file);
    }

    public File getFileDetail(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    public List<File> getFilesForUser(Integer userId) {
       return fileMapper.getFilesForUser(userId);
    }

    public void deleteFileById(Integer fileId) {
        fileMapper.deleteFileById(fileId);
    }
}
