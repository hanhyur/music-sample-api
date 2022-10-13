package me.gracenam.musicsampleapi.domain.soundtrack.exception;

import me.gracenam.musicsampleapi.global.commons.ExceptionMessageUtil;
import org.springframework.validation.BindingResult;

public class SoundtrackValidationException extends RuntimeException {
    public SoundtrackValidationException(BindingResult result) {
        super(ExceptionMessageUtil.messageParse(result));
    }
}
