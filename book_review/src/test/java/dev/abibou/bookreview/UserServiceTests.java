//package dev.abibou.bookreview;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.dao.DataIntegrityViolationException;
//
//import dev.abibou.bookreview.entity.UserEntity;
//import dev.abibou.bookreview.models.UserInfo;
//import dev.abibou.bookreview.services.UserService;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS) // avoid JUnitException
//public class UserServiceTests {
//	@Autowired
//	private UserService userService;
//	
////	@BeforeAll
////	public void delele_rong_username() {
////		String username = "rong";
////		
////		userService.deleteUser(username);
////	}
//
//	@Test
//	public void saveUser_shouldSaveUser_whenNewUserIsValid() throws Exception {
//
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUsername("rong");
//		userInfo.setPassword("12345");
//
//		assertTrue(userService.saveUser(userInfo));
//	}
//	
//	@Test 
//	void saveUser_shouldThrowException_whenUsernameExists() {
//		
//		UserInfo userInfo = new UserInfo("ambodji", "12345");
//		
//		Exception exception = assertThrows(DataIntegrityViolationException.class, () -> {
//			userService.saveUser(userInfo);
//		});
//		
//		String expectedMessage = "Username already exists. Please, log in.";
//		String actualMessage = exception.getMessage();
//		
//		assertEquals(actualMessage, expectedMessage);
//	}
//	
//	@Test
//	void saveUser_shouldThrowException_whenUserIsNull() {
//		UserInfo userInfo=null;
//		
//		Exception exception = assertThrows(NullPointerException.class, () -> {
//			userService.saveUser(userInfo);
//		});
//
//		String actualMessage = exception.getMessage();
//		
//		assertTrue(actualMessage.contains("is null"));
//		
//	}// IllegalArgumentException: rawPassword cannot be null
//	
//	@Test
//	void saveUser_shouldThrowException_whenUserFieldsAreNull() {
//		UserInfo userInfo=new UserInfo();
//		
//		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//			userService.saveUser(userInfo);
//		});
//
//		String actualMessage = exception.getMessage();
//		
//		assertTrue(actualMessage.contains("cannot be null"));
//	}
//	
//	@Test
//	void getUserByUsername_shouldReturnUser_whenUserExists() {
//		String username = "ambodji";
//		
//		UserEntity user = userService.getUserByUsername(username);
//		
//		assertNotNull(user);
//	}
//	
//	@Test
//	void getUserByUsername_shouldReturnNull_whenUserDoesNotExist() {
//		String username = "NonExistentUser";
//		
//		UserEntity user = userService.getUserByUsername(username);
//		
//		assertNull(user);
//	}
//	
//	@Test
//	void deleteUser_shouldDeleteZeroUser_whenWhenUsernameDoesNotExist() {
//		int expectedNumber=0;
//		int actualNumber = userService.deleteUser("notusername");
//		
//		assertEquals(expectedNumber, actualNumber);
//	}
//	
//	@AfterAll
//	public void deleteUser_shouldDeleteOneUser_whenUserExist() {
//		userService.deleteUser("rong");
//	}
//
//
//}
