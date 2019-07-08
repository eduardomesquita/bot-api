package br.com.bot.api.service;

import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Bot;
import br.com.bot.api.repositories.BotRepository;
import br.com.bot.api.utils.ConstantsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BotService {

    @Autowired
    private BotRepository repository;

    @Autowired
    private ValidateService validate;

    public Bot findById(String id) throws BusinessException {
        Optional<Bot> bot = repository.findById(id);
        if (bot.isPresent()) {
            return bot.get();
        }
        throw new BusinessException(ConstantsUtils.MSG_ERROR_INVALID_ID);
    }

    public Bot save(String name) throws BusinessException {
        validate.valdiateName(name);
        return repository.save(new Bot(name));
    }

    public void delete(String id) throws BusinessException {
        Bot bot = findById(id);
        repository.delete(bot);
    }

    public void update(String id, String name) throws BusinessException {
        validate.valdiateName(name);
        Bot bot = findById(id);
        bot.setName(name);
        repository.saveAndFlush(bot);
    }

}
