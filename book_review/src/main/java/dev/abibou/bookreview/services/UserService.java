package dev.abibou.bookreview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import dev.abibou.bookreview.entity.Book;
import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.models.UserInfo;
import dev.abibou.bookreview.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	public void saveUser(UserInfo userInfo) {
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUsername(userInfo.getUsername());
		userEntity.setPassword(pwdEncoder.encode(userInfo.getPassword()));
		
		userRepository.save(userEntity);
	}
	
	public UserEntity getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
