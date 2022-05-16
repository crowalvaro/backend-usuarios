package com.miprimerejemplo.springboot.web.apirest.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	
	@Override //30 minutos por poner authorizehttpRequest
	public void configure(HttpSecurity http) throws Exception { 
		// ** cualquier ruta en adelante
		//Primero lo mas especifico y al final lo más generico
		http.authorizeRequests().antMatchers("/api/usuarios","/api/usuarios/page/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/usuarios/{id}").hasAnyRole("USUARIO", "ADMIN")
		.antMatchers(HttpMethod.POST,"/api/usuarios/upload").permitAll()
		.antMatchers(HttpMethod.GET,"/api/usuarios/descargar/excel/**").permitAll()
		.antMatchers("/api/usuarios/**").hasRole("ADMIN")
		.antMatchers("/api/uploads/img/**").permitAll()
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
	}

	/**
	 * Configurar cors
	 */
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", config);
		
		return source;
	}
	
	//Filtro más alto en los beans
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		
	FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
	
			//Orden bajo mayor prioridad
		
	bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	return bean;
	}
}
