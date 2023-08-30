package dev.abibou.bookreview.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


	@SuppressWarnings({ "deprecation", "removal" })
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

		httpSecurity
			.authorizeHttpRequests((request) -> request
				.requestMatchers("/signup", "/login").permitAll()
				.anyRequest().authenticated()
			);
		httpSecurity.csrf(csrf -> csrf.disable());
		httpSecurity.httpBasic(Customizer.withDefaults());
		
		return httpSecurity.build();
	}
}
