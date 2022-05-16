package com.miprimerejemplo.springboot.web.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableAsync
@SpringBootApplication
public class SpringBootBackendApirestApplication implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}
	
	/*
	 * Para generar contraseñas cifradas
	 */
	@Override
	public void run(String... args) throws Exception {
////		String password = "contrasena";
////		
////		for(int i = 0 ;i<4;i++) {
////			String passwordBcrypt = passwordEncoder.encode(password);
////			System.out.println(passwordBcrypt);
////		}
		
		
	}
	
	//Para habilitar el CORS con angular forma2;
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:4200").allowedMethods("*")
//						.allowedHeaders("*");
//			}
//		};
//	}
}
