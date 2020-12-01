package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/upload/note")
    public String uploadNote(Model model, Principal principal, Note note) {
        String errorMsg = null;

        User user = userMapper.getUserByUsername(principal.getName());
        int addedRows = noteService.saveOrUpdateNote(note, user.getUserId());
        if (addedRows <= 0) errorMsg = "There was an error uploading your file. Please try again.";

        if (errorMsg != null) model.addAttribute("errorMsg", errorMsg);

        return "result";
    }


    @GetMapping("/delete/note/{noteId}")
    public String deleteNote(Model model, @PathVariable("noteId") int noteId) {
        String errorMsg = null;

        int deletedRows = noteService.deleteNote(noteId);

        if (deletedRows <= 0) errorMsg = "An error occurred, the note has not been deleted. Please try again.";

        if (errorMsg != null) model.addAttribute("errorMsg", errorMsg);

        return "result";
    }
}
