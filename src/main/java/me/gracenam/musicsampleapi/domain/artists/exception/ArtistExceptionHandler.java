package me.gracenam.musicsampleapi.domain.artists.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArtistExceptionHandler {

    @ExceptionHandler({ArtistNotFoundException.class, ArtistValidationException.class})
    public ResponseEntity<String> handleEventRequestNotValid(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
