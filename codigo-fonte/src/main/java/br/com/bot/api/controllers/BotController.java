package br.com.bot.api.controllers;

import br.com.bot.api.dto.BotDTO;
import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Bot;
import br.com.bot.api.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/bots", produces = MediaType.APPLICATION_JSON_VALUE)
public class BotController {

    @Autowired
    private BotService service;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bot> get(@PathVariable("id") String id) {
        HttpStatus status = HttpStatus.OK;

        Bot bot = service.findOne(id);
        if (bot == null){
            status = HttpStatus.NO_CONTENT;
        }

        return new ResponseEntity<Bot>(bot, status);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody BotDTO botDTO) {
        ResponseEntity<?> response = null;

        try {
            Bot bot = service.save(botDTO.getName());
            response = new ResponseEntity<Bot>(bot, HttpStatus.CREATED);

        } catch (BusinessException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return response;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        ResponseEntity<?> response =  new ResponseEntity<>(HttpStatus.OK);

        try {
            service.delete(id);

        } catch (BusinessException e) {
            System.out.println(e);
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> put(@PathVariable("id") String id,
                                 @RequestBody BotDTO botDTO) {

        ResponseEntity<?> response = new ResponseEntity<Bot>(HttpStatus.OK);

        try {
            service.update(id, botDTO.getName());

        } catch (BusinessException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }

        return response;
    }

}
