package com.lsd.logement;

import com.lsd.logement.entity.personnel.User;
import com.lsd.logement.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.time.ZonedDateTime;

@SpringBootApplication
public class LogementApplication implements CommandLineRunner {
	private final UserService userService;

	private final Logger logger = LogManager.getLogger(LogementApplication.class																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																								);

	public LogementApplication(UserService userService) {
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(LogementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!userService.checkAdmin()){
			User defaultUser = new User();
			defaultUser.setFirstname("John");
			defaultUser.setLastname("Doe");
			defaultUser.setUsername("admin");
			defaultUser.setPassword("admin");
			defaultUser.setEmail("admin@admin.com");
			defaultUser.setPhone("690000000");
			defaultUser.setRoles("ROLE_ADMIN");
			defaultUser.setCreatedAt(ZonedDateTime.now());
			defaultUser.setLastUpdatedAt(ZonedDateTime.now());
			defaultUser.setEnabled(true);
			try {
				if(!userService.checkAdmin()){
					userService.save(defaultUser);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
		}
	}

	@Bean
	public CorsFilter corsFilter(){
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfiguration = new CorsConfiguration();

		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("PATCH");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("OPTIONS");
		source.registerCorsConfiguration("/**", corsConfiguration);

		return new CorsFilter(source);
	}
}
