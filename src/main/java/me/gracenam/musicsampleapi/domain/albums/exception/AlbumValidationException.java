package me.gracenam.musicsampleapi.domain.albums.exception;

import me.gracenam.musicsampleapi.global.commons.ExceptionMessageUtil;
import org.springframework.validation.BindingResult;

public class AlbumValidationException extends RuntimeException {
    public AlbumValidationException(BindingResult result) {
        super(ExceptionMessageUtil.messageParse(result));
    }
}
