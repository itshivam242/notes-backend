package com.nagarro.notesbackend.controller;

import com.nagarro.notesbackend.dto.NotesDto;
import com.nagarro.notesbackend.entity.Notes;
import com.nagarro.notesbackend.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@Validated
public class NotesController {
    @Autowired
    NotesService service;

    @PostMapping("/notes")
    public ResponseEntity<NotesDto> addNotes(@Valid @RequestBody Notes notes) {
        return ResponseEntity.ok().body(service.saveNote(notes));
    }

    @GetMapping("/notes")
    public ResponseEntity<List<NotesDto>> getNotes() {
        return ResponseEntity.ok().body(service.getNote());
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NotesDto> findNoteById(@PathVariable("id") Integer id) {
        NotesDto productDto = service.getNote(id);
        return ResponseEntity.ok().body(productDto);
    }
    @GetMapping("/notes/user/{uId}")
    public ResponseEntity<List<NotesDto>> findNoteByUserId(@PathVariable("uId") Integer id) {
        List<NotesDto> productDto = service.getNoteByUserId(id);
        return ResponseEntity.ok().body(productDto);
    }
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNotes(@PathVariable("id")Integer id) {
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}
