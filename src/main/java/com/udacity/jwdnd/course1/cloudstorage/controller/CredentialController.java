package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CredentialController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/upload/credential")
    public String uploadCredential(Model model, Principal principal, Credential credential) {
        String errorMsg = null;
        User user = userMapper.getUserByUsername(principal.getName());

        int addedRows = credentialService.saveOrUpdateCrendetial(credential, user.getUserId());
        if (addedRows <= 0) errorMsg = "An error occurred, your credentials have not been saved. Please, try again.";

        if (errorMsg != null) model.addAttribute("errorMsg", errorMsg);

        return "result";
    }

    @GetMapping("/delete/credential/{credentialId}")
    public String deleteCredential(Model model, @PathVariable("credentialId") int credentialId) {
        String errorMsg = null;

        int deletedRows = credentialService.deleteCredential(credentialId);

        if (deletedRows <= 0) errorMsg = "An error occurred, the credential has not been deleted. Please, try again.";

        if (errorMsg != null) model.addAttribute("errorMsg", errorMsg);

        return "result";
    }
}
