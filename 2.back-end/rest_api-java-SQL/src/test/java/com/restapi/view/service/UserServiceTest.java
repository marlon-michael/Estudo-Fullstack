package com.restapi.view.service;

import com.restapi.model.entity.User;
import com.restapi.view.reposotiry.UserRepository;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


// @SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	// @Autowired
	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	User user = new User();
	@BeforeEach
	public void setUp(){
		user.setName("Marlon");
	}

	@Test
	public void findByNameShouldReturnUser() {
		when(userRepository.findByName("Marlon")).thenReturn(List.of(user));
		List<User> expected = List.of(user);
		List<User> actual = userService.findByName(user.getName());

		assertNotNull(actual);
		assertEquals(expected, actual);
		verify(userRepository).findByName(user.getName()); // verifica metodo foi executado
		verifyNoMoreInteractions(userRepository); // verifica se houve mais alguma interação
	}

	@Test
	public void emptyNameThrowsError(){
		Exception error = assertThrows(Exception.class, ()-> userService.save(null));

		assertEquals(error.getMessage(), "Novo usuário deve ser enviado no corpo da requsição");
		assertEquals(error.getCause(), null);
	}
}
