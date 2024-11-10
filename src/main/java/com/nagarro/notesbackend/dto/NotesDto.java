package com.nagarro.notesbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesDto {
    private int id;
    private int userId;
    private String title;
    private String body;
    private Date date;

}
