package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public String loginView(Model model) {

        String signupSuccess = (String) model.asMap().get("signupSuccess");

        if(signupSuccess != null && !signupSuccess.isEmpty()) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupSuccess", false);
        }

        return "login";
    }

}
