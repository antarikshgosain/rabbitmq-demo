package com.backend.rabbitmq.demo.publisher;

import com.backend.rabbitmq.demo.dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing-json.key}")
    private String routingJsonKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user) throws JsonProcessingException {
        LOGGER.info("Sending Json: " + user.toString());
        LOGGER.info("Printing Json: " + new ObjectMapper().writeValueAsString(user));
        LOGGER.info("Printing LONG Json: " + rabbitTemplate.getMessageConverter().fromMessage(rabbitTemplate.getMessageConverter().toMessage(user, null)));

        rabbitTemplate.convertAndSend(exchangeName, routingJsonKey, user);
    }


}
