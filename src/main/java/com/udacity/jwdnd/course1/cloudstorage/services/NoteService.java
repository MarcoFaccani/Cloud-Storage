package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteMapper noteMapper;

    /* public boolean isTitleAvailable(String title) {
        return noteMapper.retrieveNoteByTitle(title).isEmpty();
    } */

    public int saveOrUpdateNote(final Note note, final int userId) {
        int addedRows = 0;
        if (note.getNoteId() == null) addedRows = noteMapper.saveNote(note, userId);
        else addedRows = noteMapper.updateNote(note);
        return addedRows;
    }

    public List<Note> getAllNotes(final int userId) {
        return noteMapper.retrieveNotesByUserId(userId);
    }

    public int deleteNote(final int noteId) {
        return noteMapper.deleteNote(noteId);
    }

}
