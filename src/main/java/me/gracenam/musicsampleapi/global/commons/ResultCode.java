package me.gracenam.musicsampleapi.global.commons;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS("성공"),


    // 아티스트


    // 앨범


    ;

    private final String message;

    ResultCode(String message) {
        this.message = message;
    }

}
