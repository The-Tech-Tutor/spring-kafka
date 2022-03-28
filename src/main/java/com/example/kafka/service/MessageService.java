package com.example.kafka.service;

import com.example.kafka.model.MessageObject;

public interface MessageService {
    void sendMessage(MessageObject messageObject);

    void sendMessageWithHeaders(MessageObject messageObject);
}
