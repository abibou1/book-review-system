package dev.abibou.bookreview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.models.UserInfo;
import dev.abibou.bookreview.services.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserInfo userInfo){
		
		userService.saveUser(userInfo);
		
		String response = "Username=" + userInfo.getUsername() + " has successfully signed up.";
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserInfo userInfo){
		
		return new ResponseEntity<String>("JWT code", HttpStatus.OK);
	}
}
