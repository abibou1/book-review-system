package dev.abibou.bookreview;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.models.UserInfo;
import dev.abibou.bookreview.services.UserService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // avoid JUnitException
public class UserServiceTests {
	@Autowired
	private UserService userService;
	
	@BeforeAll
	public void delele_rong_username() {
		String username = "rong";
		
		userService.deleteUser(username);
	}

	@Test
	public void saveUser_shouldSaveUser_whenEntityIsValid() {

		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("rong");
		userInfo.setPassword("12345");

		assertTrue(userService.saveUser(userInfo));
	}

}
