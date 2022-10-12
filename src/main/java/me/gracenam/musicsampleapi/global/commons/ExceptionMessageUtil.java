package me.gracenam.musicsampleapi.global.commons;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;

public class ExceptionMessageUtil {

    public static String messageParse(BindingResult result){

        String message = "";

        for (FieldError fieldError : result.getFieldErrors()) {
            message += fieldError.getDefaultMessage() + ",";
        }
        return message;
    }

    public static CustomErrorMsg customErrorMsg(int statusCode, String messages){
        return new CustomErrorMsg(statusCode, getMessageList(messages));
    }

    private static List<String> getMessageList(String messages){
        String[] split = messages.split(",");
        return Arrays.asList(split);
    }

}
