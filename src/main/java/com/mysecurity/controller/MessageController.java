package com.mysecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mysecurity.entity.Message;
import com.mysecurity.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService service;

    @PostMapping("/addMessage")
    public Message addMessage(@RequestBody Message message) {
        return service.addMessage(message);
    }

    @PutMapping("/updateMessage/{id}")
    public Message updateMessage(@PathVariable int id, @RequestBody Message message) {
        return service.updateMessage(id, message);
    }
}
