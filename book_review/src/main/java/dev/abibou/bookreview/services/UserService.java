package dev.abibou.bookreview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.models.UserInfo;
import dev.abibou.bookreview.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	public boolean saveUser(UserInfo userInfo) {
		String username = userInfo.getUsername();
		
		if(userRepository.findByUsername(username) != null) {
			
			return false;
		}
		UserEntity userEntity = new UserEntity();
		
		userEntity.setUsername(username);
		userEntity.setPassword(pwdEncoder.encode(userInfo.getPassword()));
		
		userRepository.save(userEntity);
		
		return true;
	}
	
	public UserEntity getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
