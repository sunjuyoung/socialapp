package com.example.socialApi;

import com.example.socialApi.dto.SignUpDTO;
import com.example.socialApi.service.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class SocialApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialApiApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Bean
	CommandLineRunner run(AuthService authService){
		return args -> {
			for(int i=1; i<4; i++){
				SignUpDTO signUpDTO = new SignUpDTO();
				signUpDTO.setPassword("1234");
				signUpDTO.setNickname("test"+i);
				signUpDTO.setEmail("test"+i);

				authService.signUp(signUpDTO);
			}

		};
	}
}
