package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;


import java.io.InputStream;
import java.security.Principal;

@Controller
public class PagesController {

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @PostMapping("/upload/file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Authentication auth, Principal principal, Model model) {
        String fileUploadError = null;
        User user = userMapper.getUserByUsername(principal.getName());
        if (user != null) {
            int addedRows = uploadFileService.upload(file, user.getUserId());
            if (addedRows <= 0) fileUploadError = "There was an error uploading your file. Please try again.";
        }
        if (fileUploadError != null) model.addAttribute("fileUploadError", fileUploadError);
        return "home";
    }
}
