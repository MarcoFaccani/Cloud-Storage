package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model, User user) {
        return "signup";
    }

    @PostMapping("/signup")
    public void signUpNewUser(final User user, Model model) {
        String signUpError = null;

        if (!signUpService.isUsernameAvailable(user.getUsername())) signUpError = "Username already taken";

        if (signUpService.createUser(user) < 0) signUpError = "An error occured. Please try again.";

        if (signUpError != null) model.addAttribute("signUpError", signUpError);
        else model.addAttribute("signUpSuccess", true);
    }

}
