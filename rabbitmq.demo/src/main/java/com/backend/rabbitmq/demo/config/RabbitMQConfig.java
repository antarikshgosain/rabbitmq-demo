package com.backend.rabbitmq.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Bean //spring bean for rabbit queue
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean //spring bean for exchange
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

}
