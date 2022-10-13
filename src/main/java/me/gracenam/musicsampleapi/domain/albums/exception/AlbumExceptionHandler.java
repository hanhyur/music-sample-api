package me.gracenam.musicsampleapi.domain.albums.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlbumExceptionHandler {

    @ExceptionHandler({AlbumNotFoundException.class, AlbumValidationException.class})
    public ResponseEntity<String> handleEventRequestNotValid(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
