package dev.abibou.bookreview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.payload.request.UserRequest;
import dev.abibou.bookreview.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	public boolean saveUser(UserRequest userInfo) {
		UserEntity userEntity = new UserEntity();

		userEntity.setUsername(userInfo.getUsername());
		userEntity.setPassword(pwdEncoder.encode(userInfo.getPassword()));

		try {
			userRepository.save(userEntity);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Username already exists. Please, log in.");
		}
		
		return true;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " is not found."));
		
		return UserDetailsImpl.build(userEntity);
	}
	
	@Transactional
	public int deleteUser(String username) {
		 return userRepository.deleteByUsername(username);
	}
	
}
