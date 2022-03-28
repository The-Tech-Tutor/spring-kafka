package com.example.kafka.controller;

import com.example.kafka.model.MessageObject;
import com.example.kafka.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessageToTopic(@RequestBody MessageObject messageObject) {
        messageService.sendMessage(messageObject);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(path = "/header", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendMessageToTopicWithHeaders(@RequestBody MessageObject messageObject) {
        messageService.sendMessageWithHeaders(messageObject);
        return new ResponseEntity(HttpStatus.OK);
    }
}
