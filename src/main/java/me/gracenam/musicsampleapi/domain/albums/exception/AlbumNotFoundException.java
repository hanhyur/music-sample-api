package me.gracenam.musicsampleapi.domain.albums.exception;

public class AlbumNotFoundException extends RuntimeException {

    public AlbumNotFoundException(String message) {
        super("ID [" + message + "]가 존재하지 않습니다.");
    }

}
