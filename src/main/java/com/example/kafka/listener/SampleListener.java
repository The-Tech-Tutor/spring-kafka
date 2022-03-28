package com.example.kafka.listener;

import com.example.kafka.model.MessageObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleListener {

    //https://docs.spring.io/spring-kafka/docs/2.8.2/reference/html/#kafka-listener-annotation
    @KafkaListener(id = "${kafka.firstGroup}", topics = "${kafka.testTopic}")
    public void listen(MessageObject messageObject) {
        log.info("Received message for test topic in first group: {}", messageObject.getMessageBody());
    }

    @KafkaListener(id = "${kafka.secondGroup}", topics = "${kafka.testTopic}")
    //https://docs.spring.io/spring-kafka/docs/2.8.2/reference/html/#consumer-record-metadata
    public void listenWithHeaders(@Header(name = "Message-Type", required = false) String messageType, @Payload MessageObject messageObject) {
        log.info("Received message for test topic in second group Header: {} Message: {}", messageType, messageObject.getMessageBody());
    }
}
