package com.mysecurity.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mysecurity.entity.Message;
import com.mysecurity.repository.MessageRepository;
import java.util.Optional;
@Service
public class MessageService {
    @Autowired
    private MessageRepository repository;

    public Message addMessage(Message message) {

        return repository.save(message);
    }

    public Message updateMessage(int id, Message message) {
        Optional<Message> existingMessage = repository.findById(id);
        if (existingMessage.isPresent()) {
            Message messageToUpdate = existingMessage.get();
            messageToUpdate.setCustomer(message.getCustomer());
            messageToUpdate.setMessage(message.getMessage());
            messageToUpdate.setDateTime(message.getDateTime());
            return repository.save(messageToUpdate);
        } else {
            throw new RuntimeException("Message not found with id: " + id);
        }
    }
}