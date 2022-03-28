package com.example.kafka.service;

import com.example.kafka.config.KafkaConfiguration;
import com.example.kafka.model.MessageObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final KafkaConfiguration kafkaConfiguration;

    private final KafkaTemplate<String, MessageObject> kafkaTemplate;

    //https://docs.spring.io/spring-kafka/docs/2.8.2/reference/html/#overview
    public void sendMessage(MessageObject messageObject) {
        ListenableFuture<SendResult<String, MessageObject>> listenableFuture =
                kafkaTemplate.send(kafkaConfiguration.getTestTopic(), messageObject);
        listenableFuture.addCallback(res -> {log.info("Result {}", res);}, ex -> {log.error("Error Occurred", ex);});
    }

    public void sendMessageWithHeaders(MessageObject messageObject) {
        Headers headers = new RecordHeaders();
        headers.add("Message-Type", "Spring Kafka".getBytes());
        ProducerRecord<String, MessageObject> producerRecord = new ProducerRecord(kafkaConfiguration.getTestTopic(),
                null, Instant.now().getEpochSecond(), null, messageObject, headers);

        ListenableFuture<SendResult<String, MessageObject>> listenableFuture =
                kafkaTemplate.send(producerRecord);
        listenableFuture.addCallback(res -> {log.info("Result {}", res);}, ex -> {log.error("Error Occurred", ex);});
    }
}
