//package dev.abibou.bookreview;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import dev.abibou.bookreview.entity.UserEntity;
//import dev.abibou.bookreview.models.UserInfo;
//import dev.abibou.bookreview.services.UserService;
//
//public class UserServiceTests {
//
//	@Autowired
//	private UserService userService;
//
//	@Test
//	public void saveUser_shouldSaveUser_whenEntityIsValid() {
//
//		UserInfo userInfo = new UserInfo();
//		userInfo.setUsername("rong");
//		userInfo.setPassword("12345");
//
//		assertTrue(userService.saveUser(userInfo));
//	}
//
//}
