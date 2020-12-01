package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/upload/file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Principal principal, Model model) {
        String errorMsg = null;

        User user = userMapper.getUserByUsername(principal.getName());
        if (fileService.isFileNameAvailable(file.getOriginalFilename())) {
            try{
                int addedRows = fileService.save(file, user.getUserId());
                if (addedRows <= 0) errorMsg = "there was an error uploading your file. Please try again.";
            } catch (Exception ex) {
                errorMsg = "there was an error uploading your file. Please try again.";
            }
        } else errorMsg = "a File with that name already exists. Please, update the file's name and try again.";

        if (errorMsg != null) model.addAttribute("errorMsg", errorMsg);

        return "result";
    }

    @GetMapping("/delete/file/{fileId}")
    public String deleteFile(Model model, @PathVariable("fileId") int fileId) {
        String errorMsg = null;

        int deletedRows = fileService.deleteFile(fileId);

        if (deletedRows <= 0) errorMsg = "An error occurred, the note has not been deleted. Please try again.";

        if (errorMsg != null) model.addAttribute("errorMsg", errorMsg);

        return "result";
    }

    @GetMapping("/view/file/{fileId}")
    public ResponseEntity<ByteArrayResource> viewFile(Model model, @PathVariable("fileId") int fileId) {
        File file = fileService.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName() + "\"")
                .body(new ByteArrayResource(file.getFileData()));
    }

}
