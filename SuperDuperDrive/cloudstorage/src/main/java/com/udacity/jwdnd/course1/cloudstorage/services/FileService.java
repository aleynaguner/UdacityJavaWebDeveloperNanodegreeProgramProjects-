package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;
    private UserMapper userMapper;

    public FileService(FileMapper fileMapper, UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public int uploadFile(MultipartFile file, String username) throws IOException {
        Integer userId = userMapper.getUserIdByUsername(username);
        File uploadFile = new File(0, file.getOriginalFilename(), file.getContentType(), file.getSize(), userId, file.getBytes());
        return fileMapper.uploadFile(uploadFile);
    }

    public ResponseEntity downloadFile(File file) {
        if(file != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+file.getFileName()+"\"")
                    .body(file.getFileData());
        }
        return null;
    }

    public File getFileDetail(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    public List<File> getFilesForUser(Integer userId) {
       return fileMapper.getFilesForUser(userId);
    }

    public int deleteFileById(Integer fileId) {
       return fileMapper.deleteFileById(fileId);
    }
}
