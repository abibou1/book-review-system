package dev.abibou.bookreview.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abibou.bookreview.entity.Role;
import dev.abibou.bookreview.entity.UserEntity;
import dev.abibou.bookreview.models.UserInfo;
import dev.abibou.bookreview.repository.RoleRepository;
import dev.abibou.bookreview.repository.UserRepository;
import dev.abibou.bookreview.services.UserDetailsImpl;
import dev.abibou.bookreview.utils.JwtUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping("/api/auth")
@OpenAPIDefinition(info = @Info(title = "USER API Definition", version = "2.0", description = "Book Review Application"))
public class UserController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepo;
	@Autowired
	RoleRepository roleRepo;
	
	 @Autowired
	 PasswordEncoder encoder;

	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserInfo userInfo){
		
		// return new ResponseEntity<>("sign up success", HttpStatus.CREATED);
		
		String username = userInfo.getUsername();
		String password = userInfo.getPassword();

		if(userRepo.existsByUsername(username)) {
			
			return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);
		}
		
		UserEntity userEntity = new UserEntity(username, encoder.encode(password));
		
		Role role;
		
		if(userInfo.getRole().toUpperCase() == "USER" ) {
			role = new Role("USER");
		}
		else {
			role = new Role("ADMIN");
		}
		
		
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		userEntity.setRoles(roles);
		roleRepo.save(role);
		userRepo.save(userEntity);
		
		return new ResponseEntity<UserEntity>(userEntity, HttpStatus.CREATED);
		
		//return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserInfo userInfo){

		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				userInfo.getUsername(),
				userInfo.getPassword()
			)
		);

		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtUtil.generateJwtToken(authentication);
		
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		return new ResponseEntity<>(jwt, HttpStatus.OK);
		
	}

}
