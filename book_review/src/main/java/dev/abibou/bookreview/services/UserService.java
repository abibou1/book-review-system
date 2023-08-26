package dev.abibou.bookreview.services;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		UserEntity userEntity = new UserEntity();

		userEntity.setUsername(userInfo.getUsername());
		userEntity.setPassword(pwdEncoder.encode(userInfo.getPassword()));

		try {
			userRepository.save(userEntity);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Username already exists. Please, log in.");
		}
		return true;
		
//		String username = userInfo.getUsername();
//
//		if(userRepository.findByUsername(username) != null) {
//
//			return false;
//		}
//		UserEntity userEntity = new UserEntity();
//
//		userEntity.setUsername(userInfo.getUsername());
//		userEntity.setPassword(pwdEncoder.encode(userInfo.getPassword()));
//
//		userRepository.save(userEntity);
//
//		return true;
	}

	public UserEntity getUserByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Transactional
	public int deleteUser(String username) {
		 return userRepository.deleteByUsername(username);
	}

}
