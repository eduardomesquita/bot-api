package br.com.bot.api.service;

import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Bot;
import br.com.bot.api.models.dto.MessageDTO;
import br.com.bot.api.repositories.BotRepository;
import br.com.bot.api.utils.ConstantsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateService {

    @Autowired
    private BotRepository botRepository;

    public void valdiateName(String name) throws BusinessException {
        if(StringUtils.isEmpty(name)){
            throw new BusinessException(ConstantsUtils.MSG_ERROR_INVALID_NAME);
        }

        Bot bot = botRepository.findByName(name);
        if(bot != null){
            throw new BusinessException(ConstantsUtils.MSG_ERROR_EXISTS_NAME);
        }
    }

    public void valdiateMessage(MessageDTO dto) throws BusinessException {
        if(dto == null
                || StringUtils.isEmpty(dto.getFrom())
                || StringUtils.isEmpty(dto.getTo())
                || StringUtils.isEmpty(dto.getTimestamp())
                || StringUtils.isEmpty(dto.getText())
                || StringUtils.isEmpty(dto.getConversationId())){

            throw new BusinessException(ConstantsUtils.MSG_ERROR_INVALID_MESSAGE);
        }
    }
}
