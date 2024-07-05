package com.rabbitmq.controler;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.producer.Producer;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@RestController
@RequestMapping("/request")
public class RequestController {

    private final Producer producer;

    @PostMapping
    public String Request(@RequestBody String entity) {
        producer.addToQueue(entity);
        return entity;
    }
    
}
