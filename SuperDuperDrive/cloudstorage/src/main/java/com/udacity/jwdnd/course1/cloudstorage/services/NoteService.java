package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;
    private UserMapper userMapper;

    public NoteService(NoteMapper noteMapper, UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public Integer createNote(String noteTitle, String noteDescription, String username) {
        Integer userId = userMapper.getUserIdByUsername(username);
        Note note = new Note(0, noteTitle, noteDescription, userId);
        return noteMapper.createNote(note);
    }

    public Note getNoteById(Integer noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public List<Note> getNotesForUser(Integer userId) {
        return noteMapper.getNotesForUser(userId);
    }

    public Integer updateNote(Integer noteId, String noteTitle, String noteDescription) {
        return noteMapper.updateNote(noteId, noteTitle, noteDescription);
    }

    public int deleteNoteById(Integer noteId) {
        return noteMapper.deleteNoteById(noteId);
    }

}
