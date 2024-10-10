package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.view.service.AuthorizationMockService;

import lombok.Data;


@RestController
@RequestMapping("/api")
public class MockController {
    @Autowired
    private AuthorizationMockService authorizationMockService;

    @GetMapping("/mock")
    public MockDTO getMockResponse() {
        return new MockDTO();
    }

    @GetMapping
    public boolean isAuthorized() {
        return authorizationMockService.isAuthorized();
    }
    
}

@Data
class MockDTO{
    private boolean authorized = true;
}