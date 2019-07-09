package br.com.bot.api.service;

import br.com.bot.api.exceptions.BusinessException;
import br.com.bot.api.models.Message;
import br.com.bot.api.models.dto.MessageDTO;
import br.com.bot.api.repositories.MessageRepository;
import br.com.bot.api.utils.ConstantsUtils;
import br.com.bot.api.utils.DateUtils;
import br.com.bot.api.utils.IdentifierUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private ValidateService validateService;

    @Autowired
    private MessageRepository repository;

    public Message save(MessageDTO messageDTO) throws BusinessException {
        validateService.valdiateMessage(messageDTO);
        Message message = new Message();

        message.setId(IdentifierUtils.generateRandomID());
        message.setConversationId(messageDTO.getConversationId());
        message.setText(messageDTO.getText());
        message.setFrom(messageDTO.getFrom());
        message.setTo(messageDTO.getTo());
        message.setCreatedAt(DateUtils.format(messageDTO.getTimestamp()));
        return repository.save(message);
    }

    public Message findById(String id) throws BusinessException {
        Message message = repository.findOne(id);
        if (message != null) {
            return message;
        }
        throw new BusinessException(ConstantsUtils.MSG_ERROR_INVALID_ID_MESSAGE);
    }

    public List<Message> findConversations(String conversationId) throws BusinessException {
        if(!StringUtils.isEmpty(conversationId)) {
            List<Message> messages = repository.findConversations(conversationId);
            if (messages != null && !messages.isEmpty()) {
                return messages;
            }
        }
        throw new BusinessException(ConstantsUtils.MSG_ERROR_INVALID_ID_CONVERSATION);
    }

}
