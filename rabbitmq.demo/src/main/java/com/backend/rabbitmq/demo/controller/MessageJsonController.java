package com.backend.rabbitmq.demo.controller;

import com.backend.rabbitmq.demo.dto.User;
import com.backend.rabbitmq.demo.publisher.RabbitMQJsonProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    @PostMapping(value = "/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) throws JsonProcessingException {
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message Sent: " + user.toString());
    }
}
