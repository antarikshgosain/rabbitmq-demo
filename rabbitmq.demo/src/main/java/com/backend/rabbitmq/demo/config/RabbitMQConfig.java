package com.backend.rabbitmq.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfig.class);
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.queue-json.name}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing-json.key}")
    private String routingJsonKey;

    @Bean //spring bean for rabbit queue
    public Queue queue() {
        return new Queue(queueName);
    }
    @Bean //spring bean for json messages
    public Queue jsonQueue(){
        return new Queue(jsonQueueName);
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

    @Bean //binding between json-queue and exchange using json-routing key
    public Binding bindingJson() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonKey);
    }

    // The following 3 beans will be autoconfigured by spring
    // Connection Factory
    // Rabbit Template
    // Rabbit Admin

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public AmqpTemplate template(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
