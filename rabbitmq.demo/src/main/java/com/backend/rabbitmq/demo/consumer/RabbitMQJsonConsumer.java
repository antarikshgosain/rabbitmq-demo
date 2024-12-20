package com.backend.rabbitmq.demo.consumer;

import com.backend.rabbitmq.demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue-json.name}"})
    public void consumeJson(User user){
        LOGGER.info(String.format("Received Json Message: %s",user.toString()));
    }

}
