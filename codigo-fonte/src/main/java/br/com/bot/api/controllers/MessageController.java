package br.com.bot.api.controllers;

import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Bot;
import br.com.bot.api.models.dto.BotDTO;
import br.com.bot.api.models.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessagesController {

    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody BotDTO botDTO) {
        ResponseEntity<?> response = null;
        try {
            Bot bot = service.save(botDTO.getName());
            response = new ResponseEntity<>(bot, HttpStatus.CREATED);
        } catch (BusinessException e) {
            response = new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
