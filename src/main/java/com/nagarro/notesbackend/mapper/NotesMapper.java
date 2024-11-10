package com.nagarro.notesbackend.mapper;

import com.nagarro.notesbackend.dto.NotesDto;
import com.nagarro.notesbackend.entity.Notes;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotesMapper
{
    Notes noteDtoToNote(NotesDto notesDto);
    NotesDto noteToNoteDto(Notes notes);
}
