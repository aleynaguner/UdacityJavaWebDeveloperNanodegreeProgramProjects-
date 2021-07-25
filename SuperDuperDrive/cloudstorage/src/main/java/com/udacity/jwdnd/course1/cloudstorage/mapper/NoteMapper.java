package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    //CREATE
    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNewNote(Note note);

    //READ
    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note getNoteById(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getNotesForUser(Integer userId);

    //UPDATE
    @Update("UPDATE NOTES SET noteTitle = #{noteTitle}, noteDescription = #{noteDescription}, userId = #{userId} WHERE noteId = #{noteId}")
    Note updateNote(Note note);

    //DELETE
    @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
    void deleteNoteById(Integer noteId);
}
