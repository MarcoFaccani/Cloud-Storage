package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.Authentication;


import java.security.Principal;

@Controller
public class PagesController {

    @Autowired
    private FileService fileService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/home")
    public String getHomePage(Model model, Principal principal, Note note) {
        User user = userMapper.getUserByUsername(principal.getName());
        model.addAttribute("notes", noteService.getAllNotes(user.getUserId()));
        return "home";
    }

    @PostMapping("/upload/file")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Principal principal, Model model) {
        String errorMsg = null;

        User user = userMapper.getUserByUsername(principal.getName());

        int addedRows = fileService.upload(file, user.getUserId());
        if (addedRows <= 0) errorMsg = "There was an error uploading your file. Please try again.";

        if (errorMsg != null) model.addAttribute("fileUploadError", errorMsg);
        return "home";
    }

    @PostMapping("/upload/note")
    public String uploadNote(Model model, Principal principal, Note note) {
        String errorMsg = null;

        User user = userMapper.getUserByUsername(principal.getName());
        int addedRows = noteService.saveOrUpdateNote(note, user.getUserId());
        if (addedRows <= 0) errorMsg = "There was an error uploading your file. Please try again.";

        if (errorMsg != null) {
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("success", false);
        } else model.addAttribute("success", true);

        return "result";
    }


    @GetMapping("/delete/note/{noteId}")
    public String deleteNote(Model model, @PathVariable("noteId") int noteId) {
        String errorMsg = null;

        int deletedRows = noteService.deleteNote(noteId);

        if (deletedRows <= 0) errorMsg = "An error occurred, the note has not been deleted. Please try again.";

        if (errorMsg != null) {
            model.addAttribute("errorMsg", errorMsg);
            model.addAttribute("success", false);
        } else model.addAttribute("success", true);

        return "result";
    }
}
