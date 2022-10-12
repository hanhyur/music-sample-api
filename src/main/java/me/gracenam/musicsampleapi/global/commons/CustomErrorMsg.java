package me.gracenam.musicsampleapi.global.commons;

import java.util.List;

public class CustomErrorMsg {

    private int statusCode;
    private List<String> messages;

    public CustomErrorMsg(int statusCode, List<String> messages) {
        this.statusCode = statusCode;
        this.messages = messages;
    }

}
