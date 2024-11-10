package com.nagarro.notesbackend.service;

import com.nagarro.notesbackend.dto.NotesDto;
import com.nagarro.notesbackend.entity.Notes;
import java.util.List;

public interface NotesService {
	NotesDto saveNote(Notes notes);
	List<NotesDto> getNote();
	NotesDto getNote(Integer id);
	List<NotesDto> getNoteByUserId(Integer userId);
	void deleteNote(Integer id);


}
