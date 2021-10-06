package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private CredentialService credentialService;
    private UserService userService;
    private EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String homeView(Authentication authentication,
                           @ModelAttribute("credentials") Credential credential,
                           Model model) {

        Integer userId = userService.getUserIdByUsername(authentication.getName());
        model.addAttribute("credentials", credentialService.getCredentialsForUser(userId));

        return "home";
    }

    @PostMapping("/create-credential")
    public String createNewCredential(Authentication authentication,
                                @ModelAttribute("credentials") Credential credential,
                                Model model) {

        String encodedKey = getEncodedKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);

        String username = authentication.getName();
        Integer userId = userService.getUserIdByUsername(username);
        Integer result = 0;

        if(credential != null) {
            result = credentialService.createCredential(credential.getUrl(), credential.getUsername(), encodedKey, encryptedPassword, userId);
        }

        model.addAttribute("credentials", credentialService.getCredentialsForUser(userId));
        model.addAttribute("result", resultStatus(result));

        return "result";
    }

    @GetMapping("/edit-credential/{credentialId}")
    public String editCredential(Authentication authentication,
                           @ModelAttribute("credentials") Credential credential,
                           @PathVariable Integer credentialId,
                           Model model) {

        Integer result = 0;
        result = credentialService.updateCredential(credential.getCredentialId(), credential.getUrl(), credential.getUsername(), credential.getKey(), encryptionService.decryptValue(credential.getPassword(), credential.getKey()));
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        List<Credential> credentialList = credentialService.getCredentialsForUser(userId);

        model.addAttribute("credentials", credentialList);
        model.addAttribute("result", resultStatus(result));

        return "result";
    }

    @GetMapping("/delete-credential/{credentialId}")
    public String deleteCredential(Authentication authentication,
                             @ModelAttribute("credentials") Credential credential,
                             @PathVariable Integer credentialId,
                             Model model) {

        Integer result = 0;
        result = credentialService.deleteCredentialById(credentialId);
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        model.addAttribute("credentials", credentialService.getCredentialsForUser(userId));

        model.addAttribute("result", resultStatus(result));

        return "result";
    }

    public String resultStatus(Integer result) {
        return result > 0 ? "success" : "error";
    }

    public String getEncodedKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}
