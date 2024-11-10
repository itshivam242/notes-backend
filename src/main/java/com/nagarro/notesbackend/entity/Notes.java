package com.nagarro.notesbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "userId is required")
    private Integer userId;
    @NotBlank(message = "Title is required")
    private String title;
    @Column(length=500)
    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Pattern(regexp = "^[a-zA-Z0-9@,.;&*+\\-\\s]*$", message = "Invalid characters in body")
    private String body;
    private Date date=new Date();

}
