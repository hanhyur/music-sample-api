package me.gracenam.musicsampleapi.domain.soundtrack.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SoundtrackExceptionHandler {

    @ExceptionHandler({SoundtrackNotFoundException.class, SoundtrackValidationException.class})
    public ResponseEntity<String> handleEventRequestNotValid(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
