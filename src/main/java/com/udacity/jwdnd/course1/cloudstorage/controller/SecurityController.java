package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SecurityController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model, User user) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUpNewUser(final User user, ModelMap model, RedirectAttributes redirectAttributes) {
        String signUpError = null;

        if (!signUpService.isUsernameAvailable(user.getUsername())) signUpError = "Username already taken";

        if (signUpService.createUser(user) < 0) signUpError = "An error occured. Please try again.";

        if (signUpError != null) {
            model.addAttribute("signUpError", signUpError);
            return "signup";
        }

        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/login";
    }

}
