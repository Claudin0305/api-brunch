package com.brunch.api.service.interfaces;



import com.brunch.api.entity.Message;
import com.brunch.api.utils.MessageType;

import java.util.List;

public interface MessageService {
    List<Message> getAllMessages();
    Message getMessageById(Long id);
    Message createMessage(Message message);
    Message updateMessage(Long id, Message messageUpdate);
    void deleteMessage(Long id);
    Message findByMessageType(MessageType messageType);
}
