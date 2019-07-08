package br.com.bot.api.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {

    private String conversationId;

    private String timestamp;

    private String from;

    private String to;

    private String text;

}
