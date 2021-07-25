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

    public void createNewNote(Note note, String username) {
        Integer userId = userMapper.getUserIdByUsername(username);
        note.setUserId(userId);
        noteMapper.createNewNote(note);
    }

    public Note getNoteById(Integer noteId) {
        return noteMapper.getNoteById(noteId);
    }

    public List<Note> getNotesForUser(Integer userId) {
        return noteMapper.getNotesForUser(userId);
    }

    public void deleteNoteById(Integer noteId) {
        noteMapper.deleteNoteById(noteId);
    }

}
