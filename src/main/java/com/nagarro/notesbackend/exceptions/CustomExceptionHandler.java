package com.nagarro.notesbackend.exceptions;

import com.nagarro.notesbackend.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NoteNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleProductNotFoundException(NoteNotFoundException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto( HttpStatus.NOT_FOUND,LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationErrors(MethodArgumentNotValidException ex) {
        ErrorResponseDto errorResponse = new ErrorResponseDto( HttpStatus.NOT_ACCEPTABLE,LocalDateTime.now(), ex.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);

    }

}
