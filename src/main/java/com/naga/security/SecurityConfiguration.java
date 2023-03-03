package com.naga.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfiguration {
	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
    	http.authorizeHttpRequests()
    		.requestMatchers("/edit").hasRole("ADMIN")
    		.requestMatchers("/delete").hasRole("ADMIN")
    		.requestMatchers("/new").hasAnyRole("ADMIN")
    		.requestMatchers("/**").permitAll()
    		.and().formLogin();
        return http.build();
    }
	
  	@Bean
  	PasswordEncoder getPasswordEncoder() {
  	    return NoOpPasswordEncoder.getInstance();
  	}
}
