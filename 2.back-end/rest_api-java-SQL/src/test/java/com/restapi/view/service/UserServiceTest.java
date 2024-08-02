package com.restapi.view.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

  @Autowired
  private UserRepository userRepository;

  // ANOTAÇÃO DE TESTES
  @Test
  public void createUser(){
    // SALVANDO USUARIO
    User savingUser = new User();
    String username = "Marlon Michael";
    savingUser.setName(username);
    userRepository.save(savingUser);
    
    // BUSCANDO USUARIO
    List<User> user = userRepository.findByName(username);

    // VERIFICANDO SE VALORES SÃO OS ESPERADOS
    assertEquals(username, user.get(0).getName());
    assertEquals(1, user.size());
  }

}
