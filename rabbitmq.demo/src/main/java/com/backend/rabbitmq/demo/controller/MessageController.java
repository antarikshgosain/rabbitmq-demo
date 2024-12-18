package com.backend.rabbitmq.demo.controller;

import com.backend.rabbitmq.demo.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private RabbitMQProducer producer;

    //@Autowired - not required as only one constructor
    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World!";
    }


    //http://localhost:8080/api/v1/publish?message=hello
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbitMQ " + message);
    }

}
