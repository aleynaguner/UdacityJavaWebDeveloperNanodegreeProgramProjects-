package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/file")
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping
    public String homeView(Authentication authentication,
                           @ModelAttribute("files") File file,
                           Model model) {

        Integer userId = userService.getUserIdByUsername(authentication.getName());
        model.addAttribute("files", fileService.getFilesForUser(userId));

        return "home";
    }

    @PostMapping("/upload-file")
    public String uploadFile(Authentication authentication,
                             @RequestParam("fileUpload") MultipartFile file,
                             Model model) {

        String username = authentication.getName();
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        Integer result = 0;

        String fileName = file.getOriginalFilename();
        String[] fileNameList = fileService.getFileNameList(userId);

        if(file.getSize() == 0){
            model.addAttribute("result", "errorPlus");
            model.addAttribute("message", "You cannot upload a empty file.");
            return "result";
        } else if(file.getSize() > 1000000){
            model.addAttribute("result", "errorPlus");
            model.addAttribute("message", "Too large file for upload.");
            return "result";
        } else if(username.isEmpty()) {
            model.addAttribute("result", "errorPlus");
            model.addAttribute("message", "Invalid username.");
            return "result";
        } else if(Arrays.asList(fileNameList).contains(fileName)){
            model.addAttribute("result", "errorPlus");
            model.addAttribute("message", "Existing filename. Change filename or file.");
            return "result";
        }

        try{
            result = fileService.uploadFile(file, username);
        } catch (IOException ioException) {
            result = -1;
        }

        model.addAttribute("files", fileService.getFilesForUser(userId));
        model.addAttribute("result", resultStatus(result));

        return "result";
    }

    @GetMapping("/download-file/{fileId}")
    public String downloadFile(Authentication authentication,
                               @PathVariable Integer fileId,
                               Model model) {

        Integer userId = userService.getUserIdByUsername(authentication.getName());
        File file = fileService.getFileDetail(fileId);
        Integer result = 0;

        ResponseEntity responseEntity = fileService.downloadFile(file);

        result = (responseEntity != null) ? 1 : 0;

        model.addAttribute("files", fileService.getFilesForUser(userId));
        model.addAttribute("result", this.resultStatus(result));

        return "result";
    }

    @GetMapping("/delete-file/{fileId}")
    public String deleteFile(Authentication authentication,
                             @PathVariable Integer fileId,
                             Model model) {

        Integer result = 0;
        result = fileService.deleteFileById(fileId);
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        model.addAttribute("files", fileService.getFilesForUser(userId));

        model.addAttribute("result", this.resultStatus(result));

        return "result";
    }

    public String resultStatus(Integer result) {
        return result > 0 ? "success" : "error";
    }

}
