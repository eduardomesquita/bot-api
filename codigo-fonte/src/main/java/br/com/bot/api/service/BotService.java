package br.com.bot.api.service;

import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Bot;
import br.com.bot.api.repositories.BotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BotService {

    @Autowired
    private BotRepository repository;

    private Bot findByID(String id) throws BusinessException {
        Bot bot = findOne(id);
        if(bot == null){
            throw new BusinessException("Chave Invalida");
        }
        return bot;
    }

    private void validateName(String name) throws BusinessException {
        Bot bot = repository.findByName(name);
        if(bot != null){
            throw new BusinessException("Bot ja cadastrado");
        }
    }

    public Bot save(String name) throws BusinessException {
        if(name == null || name.isEmpty()){
            throw new BusinessException("Chave Invalida");
        }
        validateName(name);

        return repository.save(new Bot(name));
    }

    public Bot findOne(String id) {
        return repository.findOne(id);
    }

    public void delete(String id) throws BusinessException {
        Bot bot = findByID(id);
        repository.delete(bot);
    }

    public void update(String id, String name) throws BusinessException {
        validateName(name);
        Bot bot = findByID(id);
        bot.setName(name);
        repository.saveAndFlush(bot);
    }

}
