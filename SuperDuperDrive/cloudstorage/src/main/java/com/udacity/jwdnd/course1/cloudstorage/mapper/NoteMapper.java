package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoteMapper {

    //CREATE
    @Insert("INSERT INTO NOTES (noteTitle, noteDescription, userId) VALUES(#{noteTitle}, #{noteDescription}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNewNote(Note note);

    //READ
    @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
    Note getNoteById(String noteId);

    @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
    List<Note> getNotesForUser(Integer userId);

}
