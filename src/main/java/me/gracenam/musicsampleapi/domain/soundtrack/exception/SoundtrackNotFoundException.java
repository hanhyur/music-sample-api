package me.gracenam.musicsampleapi.domain.soundtrack.exception;

public class SoundtrackNotFoundException extends RuntimeException {

    public SoundtrackNotFoundException(String message) {
        super("ID [" + message + "]가 존재하지 않습니다.");
    }

}
