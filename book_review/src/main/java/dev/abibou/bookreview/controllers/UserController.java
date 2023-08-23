package dev.abibou.bookreview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.abibou.bookreview.models.UserInfo;
import dev.abibou.bookreview.services.UserService;
import dev.abibou.bookreview.utils.JwtUtil;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserInfo userInfo){
		String response;
		HttpStatus status;
		
		boolean isSaved = userService.saveUser(userInfo);
		
		if(isSaved) {
			response = "Username=" + userInfo.getUsername() + " has successfully signed up.";
			status = HttpStatus.CREATED;
		}
		else {
			response = "Username=" + userInfo.getUsername() + " already exists. Please, log in.";
			status = HttpStatus.BAD_REQUEST;
		}

		return new ResponseEntity<String>(response, status);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserInfo userInfo){
		System.err.println("Before");
		
		String token = jwtUtil.generateToken("ambodji");
		// String response = "jwt code";
		
		System.out.println("Token:\n" + token);
		
		return new ResponseEntity<String>(token, HttpStatus.OK);
	}
}
