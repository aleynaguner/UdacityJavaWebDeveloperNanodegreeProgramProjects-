package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private UserService userService;
    private FileService fileService;
    private NoteService noteService;
    private CredentialService credentialService;
    private EncryptionService encryptionService;

    public HomeController(UserService userService, FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.userService = userService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping()
    public String homeView(Authentication authentication,
                           @ModelAttribute("files") File file,
                           @ModelAttribute("notes") Note note,
                           @ModelAttribute("credentials") Credential credential,
                           @ModelAttribute("encryptionService") EncryptionService encryptionService,
                           Model model) {

        Integer userId = userService.getUserIdByUsername(authentication.getName());

        model.addAttribute("files", fileService.getFilesForUser(userId));
        model.addAttribute("notes", noteService.getNotesForUser(userId));
        model.addAttribute("credentials", credentialService.getCredentialsForUser(userId));

        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

}
