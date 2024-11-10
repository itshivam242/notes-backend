package com.nagarro.notesbackend.service.impl;

import com.nagarro.notesbackend.dto.NotesDto;
import com.nagarro.notesbackend.entity.Notes;
import com.nagarro.notesbackend.exceptions.NoteNotFoundException;
import com.nagarro.notesbackend.mapper.NotesMapper;
import com.nagarro.notesbackend.repository.NotesRepository;
import com.nagarro.notesbackend.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@EnableScheduling
public class NotesServiceImpl implements NotesService {
    @Autowired
    private NotesRepository repo;

    @Autowired
    NotesMapper notesMapper;

    //This method is used to save notes
    @Override
    public NotesDto saveNote(Notes notes) {
            return notesMapper.noteToNoteDto(repo.save(notes));
    }

    //This method is used to get product with help of productCode
    @Override
    public NotesDto getNote(Integer id) {
        Optional<Notes> note= repo.findById(id);
        if (note.isPresent()) {
            return notesMapper.noteToNoteDto(note.get());
        } else {
            throw new NoteNotFoundException("No Notes Found");
        }
    }

    @Override
    public List<NotesDto> getNoteByUserId(Integer userId) {
        List<NotesDto> notesDtoList=new ArrayList<>();
        List<Notes> notesList= repo.findTop10ByUserIdOrderByDateDesc(userId);
        if (notesList.isEmpty()) {
            throw new NoteNotFoundException("No Notes Found");
        } else {
            for(Notes note:notesList){
                notesDtoList.add(notesMapper.noteToNoteDto(note));
            }
            return notesDtoList;
        }
    }

    @Override
    public List<NotesDto> getNote() {
        List<Notes> notes = repo.findAll();
        if (Objects.nonNull(notes)) {
            List<NotesDto> notesDto = new ArrayList<>();
            for (Notes note : notes) {
                notesDto.add(notesMapper.noteToNoteDto(note));
            }
            return notesDto;
        } else {
            throw new NoteNotFoundException("No Notes Found");
        }
    }
    @Override
    public void deleteNote(Integer id){
        try {
            repo.deleteById(id);
        }catch (Exception e){
            throw new NoteNotFoundException("No Notes Found to delete");
        }
    }



}