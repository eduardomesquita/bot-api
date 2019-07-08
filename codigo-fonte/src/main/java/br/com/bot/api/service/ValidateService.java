package br.com.bot.api.service;

import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Bot;
import br.com.bot.api.repositories.BotRepository;
import br.com.bot.api.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateBotService {

    @Autowired
    private BotRepository botRepository;

    public void valdiateName(String name) throws BusinessException {
        if(name == null || name.isEmpty()){
            throw new BusinessException(MessageUtils.MSG_ERROR_INVALID_NAME);
        }

        Bot bot = botRepository.findByName(name);
        if(bot != null){
            throw new BusinessException(MessageUtils.MSG_ERROR_EXISTS_NAME);
        }
    }

}
