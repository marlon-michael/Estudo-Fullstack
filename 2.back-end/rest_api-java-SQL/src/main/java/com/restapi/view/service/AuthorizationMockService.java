package com.restapi.view.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restapi.model.AuthorizationMockResponse;
import com.restapi.view.client.AuthorizationMockClient;

@Service
public class AuthorizationMockService {
    @Autowired
    private AuthorizationMockClient authorizationMockClient;

    public boolean isAuthorized(){
        ResponseEntity<AuthorizationMockResponse> response = authorizationMockClient.isAuthorized();
        if (response.getStatusCode().value() != 200) return false;
        return response.getBody().authorized();
    }
}