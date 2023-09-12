package dev.abibou.bookreview.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.payload.request.UserRequest;
import dev.abibou.bookreview.services.UserDetailsServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // avoid JUnitException
public class UserServiceTest {
	@Autowired
	private UserDetailsServiceImpl userService;
	
	private final UserRequest userADMIN = new UserRequest("ambodji", "1234567", "ADMIN");
	private final UserRequest simpleUser = new UserRequest("simpleuser", "1234567", "USER");
	
	
	@BeforeAll
	public void delele_rong_username() {
		String username = "johnD";
		UserRequest userToDelete = new UserRequest("usertodelete", "password1", "USER");
		
		userService.deleteUser(username);
		
		try {
			userService.saveUser(userToDelete);
			userService.saveUser(simpleUser);
			userService.saveUser(userADMIN);
		} catch(DataIntegrityViolationException ex) {
			System.err.println("Users were already in the DB.");
		}
	}

	@Test
	public void saveUser_shouldSaveUser_whenNewUserIsValid() throws Exception {

		UserRequest userInfo = new UserRequest();
		userInfo.setUsername("johnD");
		userInfo.setPassword("1234567");
		userInfo.setRoleName("ADMIN");

		assertTrue(userService.saveUser(userInfo));
	}
	
	@Test 
	void saveUser_shouldThrowException_whenUsernameExists() {
		
		UserRequest userInfo = new UserRequest("ambodji", "1234567", "ADMIN");
		
		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
			userService.saveUser(userInfo);
		});
		
		String expectedMessage = "Username already exists. Please, log in.";
		String actualMessage = exception.getMessage();
		
		assertEquals(actualMessage, expectedMessage);
	}
	
	@Test
	void saveUser_shouldThrowException_whenUserIsNull() {
		UserRequest userInfo=null;
		
		Exception exception = assertThrows(NullPointerException.class, () -> {
			userService.saveUser(userInfo);
		});

		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains("is null"));
		
	}
	
	@Test
	void saveUser_shouldThrowException_whenUserFieldsAreNull() {
		UserRequest userInfo=new UserRequest();
		
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			userService.saveUser(userInfo);
		});

		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains("cannot be null"));
	}
	
	@Test
	void loadUserByUsername_shouldReturnUser_whenUserExists() {
		String username = "ambodji";
		
		UserDetails user = userService.loadUserByUsername(username);
		
		assertNotNull(user);
	}
	
	@Test
	void loadUserByUsername_shouldThrowException_whenUserDoesNotExist() {
		String username = "NonExistentUser";
		
		Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
			UserDetails user = userService.loadUserByUsername(username);
		});
		
		//String expectedMessage = "Username: NonExistendUser is not found.";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains("is not found."));
		
		//assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	void deleteUser_shouldDeleteZeroUser_whenWhenUsernameDoesNotExist() {
		int expectedNumber=0;
		int actualNumber = userService.deleteUser("notusername");
		
		assertEquals(expectedNumber, actualNumber);
	}
	
	@Test
	public void deleteUser_shouldDeleteOneUser_whenUserExist() {
		int actualCount = userService.deleteUser("usertodelete");
		int expectedCount = 1;
		
		assertEquals(expectedCount, actualCount);
	}


}
