package br.com.bot.api.controllers;

import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Message;
import br.com.bot.api.models.dto.ErrorDTO;
import br.com.bot.api.models.dto.MessageDTO;
import br.com.bot.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.PathParam;
import java.util.List;

@Controller
@RequestMapping(value = "/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody MessageDTO messageDTO) {
        ResponseEntity<?> response = null;
        try {
            Message message = messageService.save(messageDTO);
            response = new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (BusinessException e) {
            response = new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Message> get(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.OK;
        Message message = null;
        try {
            message = messageService.findById(id);
        } catch (BusinessException e) {
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(message, status);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getConversations(@PathParam("conversationId") String conversationId) {
        HttpStatus status = HttpStatus.OK;
        List<Message> message = null;
        try {
            message = messageService.findConversations(conversationId);
        } catch (BusinessException e) {
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<>(message, status);
    }
}
