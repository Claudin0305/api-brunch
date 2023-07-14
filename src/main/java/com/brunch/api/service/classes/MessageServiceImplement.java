package com.brunch.api.service.classes;

import com.brunch.api.entity.Message;
import com.brunch.api.repository.MessageRepository;
import com.brunch.api.service.interfaces.MessageService;
import com.brunch.api.utils.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageServiceImplement implements MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Override
    public List<Message> getAllMessages() {
        Sort sort = Sort.by("createdAt").descending();
        return messageRepository.findAll(sort);
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Long id, Message messageUpdate) {
        Message message = getMessageById(id);
        if(message == null){
            return null;
        }
        message.setMessageType(messageUpdate.getMessageType());
        message.setLibelleTexte(messageUpdate.getLibelleTexte());
        message.setSubject(messageUpdate.getSubject());
        return messageRepository.save(message);
    }

    @Override
    public Message findByMessageType(MessageType messageType){
        return messageRepository.findByMessageType(messageType);
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
