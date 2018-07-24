package com.winnieazhang.github;

import com.winnieazhang.github.config.CustomUserDetails;
import com.winnieazhang.github.entity.Role;
import com.winnieazhang.github.entity.User;
import com.winnieazhang.github.repositories.UserRepository;
import com.winnieazhang.github.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.Arrays;



//@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class DemoApplication {

//	@Qualifier("userDetailsService")
//	UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository repository, UserService userService) throws Exception{
		if(repository.count()==0)
			userService.save(new User("user","user", Arrays.asList(new Role("USER"),new Role("ACTUATOR"),new Role("ADMIN"))));
//		builder.userDetailsService(s -> new CustomUserDetails(repo.findByUsername(s)));
		builder.userDetailsService(userDetailsService(repository)).passwordEncoder(passwordEncoder);
	}

	private UserDetailsService userDetailsService(final UserRepository repository) {
		return username -> new CustomUserDetails(repository.findByUsername(username));
	}
}
