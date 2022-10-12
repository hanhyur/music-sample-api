package me.gracenam.musicsampleapi.domain.artists.exception;

import me.gracenam.musicsampleapi.global.commons.ExceptionMessageUtil;
import org.springframework.validation.BindingResult;

public class ArtistValidationException extends RuntimeException {
    public ArtistValidationException(BindingResult result) {
        super(ExceptionMessageUtil.messageParse(result));
    }
}
