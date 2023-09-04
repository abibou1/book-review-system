package dev.abibou.bookreview.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " is not found."));
		
		return UserDetailsImpl.build(userEntity);
	}
}
