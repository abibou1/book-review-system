package dev.abibou.bookreview.services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.entity.Role;
import dev.abibou.bookreview.payload.request.UserRequest;
import dev.abibou.bookreview.repository.RoleRepository;
import dev.abibou.bookreview.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	public boolean saveUser(UserRequest userInfo) {
		UserEntity userEntity = new UserEntity();

		userEntity.setUsername(userInfo.getUsername());
		userEntity.setPassword(pwdEncoder.encode(userInfo.getPassword()));
		
		String roleName = userInfo.getRoleName();
		Role roles = roleRepo.findByName(roleName)
				.orElse(new Role(roleName));
		userEntity.setRoles(Collections.singletonList(roles));
//		Role role = new Role(userInfo.getRole());
//		userEntity.getRoles().add(role);

		try {
			userRepository.save(userEntity);
			// roleRepo.save();
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
		
//		UserDetails user = loadUserByUsername(username);
//		user.getAuthorities().remove(user);
//		
//		userRepository.
		return userRepository.deleteByUsername(username);
	}
	
}
