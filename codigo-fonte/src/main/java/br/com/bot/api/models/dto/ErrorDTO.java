package br.com.bot.api.models.dto;

import lombok.Getter;

@Getter
public class ErrorDTO {

    private String message;

    public ErrorDTO(String message){
        this.message = message;
    }

}
