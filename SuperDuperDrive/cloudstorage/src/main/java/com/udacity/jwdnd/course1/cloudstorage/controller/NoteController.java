package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping
    public String homeView(Authentication authentication,
                           @ModelAttribute("notes") Note note,
                           Model model) {

        Integer userId = userService.getUserIdByUsername(authentication.getName());
        model.addAttribute("notes", noteService.getNotesForUser(userId));

        return "home";
    }

    @PostMapping("/create-note")
    public String createNewNote(Authentication authentication,
                                @ModelAttribute("notes") Note note,
                                Model model) {

        String username = authentication.getName();
        Integer userId = userService.getUserIdByUsername(username);
        Integer result = 0;

        if(note != null) {
            if(note.getNoteId() == null) {
                result = noteService.createNote(note.getNoteTitle(), note.getNoteDescription(), username);
            } else {
                result = noteService.updateNote(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription());
            }
        }

        model.addAttribute("notes", noteService.getNotesForUser(userId));
        model.addAttribute("result", resultStatus(result));

        return "result";
    }

    @GetMapping("/edit-note/{noteId}")
    public String editNote(Authentication authentication,
                           @ModelAttribute("notes") Note note,
                           @PathVariable Integer noteId,
                           Model model) {

        Integer result = 0;
        result = noteService.updateNote(noteId, note.getNoteTitle(), note.getNoteDescription());
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        model.addAttribute("notes", noteService.getNotesForUser(userId));

        model.addAttribute("result", resultStatus(result));

        return "result";
    }

    @GetMapping("/delete-note/{noteId}")
    public String deleteNote(Authentication authentication,
                             @ModelAttribute("notes") Note note,
                             @PathVariable Integer noteId,
                             Model model) {

        Integer result = 0;
        result = noteService.deleteNoteById(noteId);
        Integer userId = userService.getUserIdByUsername(authentication.getName());
        model.addAttribute("notes", noteService.getNotesForUser(userId));

        model.addAttribute("result", resultStatus(result));

        return "result";
    }

    public String resultStatus(Integer result) {
        return result > 0 ? "success" : "error";
    }
}
