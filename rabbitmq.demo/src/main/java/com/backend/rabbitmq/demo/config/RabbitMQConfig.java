package com.backend.rabbitmq.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
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

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Bean //spring bean for rabbit queue
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean //spring bean for exchange
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean //binding between queue and exchange using routing key
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    // The following 3 beans will be autoconfigured by spring
    // Connection Factory
    // Rabbit Template
    // Rabbit Admin
}
