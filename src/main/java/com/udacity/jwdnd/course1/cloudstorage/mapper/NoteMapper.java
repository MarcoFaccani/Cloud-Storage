package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import lombok.Data;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface NoteMapper {

    @Select("SELECT * FROM Notes WHERE notetitle = #{title}")
    public Optional<Note> retrieveNoteByTitle(String title);

    @Select("SELECT * FROM Notes WHERE userid = #{userId}")
    public List<Note> retrieveNotesByUserId(int userId);

    @Options(useGeneratedKeys = true, keyProperty = "note.noteId")
    @Insert("INSERT INTO Notes (noteid, notetitle, notedescription, userid) " +
            "VALUES (#{note.noteId}, #{note.noteTitle}, #{note.noteDescription}, #{userId} )" )
    int saveNote(Note note, int userId);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int deleteNote(int noteId);

    @Update("UPDATE NOTES SET notetitle=#{noteTitle}, notedescription=#{noteDescription} WHERE noteid = #{noteId}")
    int updateNote(Note note);
}


