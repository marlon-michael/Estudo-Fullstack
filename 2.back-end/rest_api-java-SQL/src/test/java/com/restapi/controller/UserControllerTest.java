package com.restapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @Test
  public void postUser(){
    String username = "Michael Pereira";
    User newUser = new User();
    newUser.setName(username);

    User user = restTemplate.postForObject("/user/post", newUser, User.class);

    assertNotNull(user);
    assertEquals(username, user.getName());
    userRepository.delete(user);
  }

  @Test
  public void getUserById(){
    String username = "Michael Pereira";
    Long id;
    User newUser = new User();
    newUser.setName(username);
    id = userRepository.save(newUser).getId();

    User user = restTemplate.getForObject("/user/get/"+id, User.class);
    
    assertNotNull(user);
    assertEquals(username, user.getName());
    assertEquals(id, user.getId());
    userRepository.delete(user);
  }

}
