package com.example.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Data
@Configuration
@ConfigurationProperties(prefix = "kafka")
//https://docs.spring.io/spring-kafka/docs/2.8.2/reference/html/#record-listener
@EnableKafka
public class KafkaConfiguration {

    //https://docs.spring.io/spring-kafka/docs/2.8.2/reference/html/#messaging-message-conversion
    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    private String firstGroup;

    private String secondGroup;

    private String testTopic;

}
