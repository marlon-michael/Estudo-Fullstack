package com.restapi.view.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.restapi.model.AuthorizationMockResponse;


@FeignClient(
    name="AuthorizationMockClient",
    url = "${communication.external.api.url}")
public interface AuthorizationMockClient {
    @GetMapping
    ResponseEntity <AuthorizationMockResponse> isAuthorized();
} 