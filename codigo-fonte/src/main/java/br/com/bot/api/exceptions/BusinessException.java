package br.com.bot.api.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends Exception {

    private String message;

    public BusinessException(String message){
        super(message);
        this.message = message;
    }

}
