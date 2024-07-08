package com.rabbitmq.controler;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rabbitmq.producer.Producer;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("/request")
public class RequestController {

    private final Producer producer;

    @GetMapping("/{message}")
    public void getRequest(@PathVariable String message) {
        producer.addToQueue(message);
    }

    @PostMapping
    public String postRequest(@RequestBody String entity) {
        producer.addToQueue(entity);
        return entity;
    }
    
}
