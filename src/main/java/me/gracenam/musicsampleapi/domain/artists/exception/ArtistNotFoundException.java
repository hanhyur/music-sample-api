package me.gracenam.musicsampleapi.domain.artists.exception;

public class ArtistNotFoundException extends RuntimeException {

    public ArtistNotFoundException(String message) {
        super("ID [" + message + "]가 존재하지 않습니다.");
    }

}
