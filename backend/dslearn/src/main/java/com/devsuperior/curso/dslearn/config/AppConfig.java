package com.devsuperior.curso.dslearn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {
	
	@Value("${jwt.secret}")
	private String jwtSecret;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	  
    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
    	JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
    	tokenConverter.setSigningKey(jwtSecret);
    	return tokenConverter;
    }
    

    @Bean
    JwtTokenStore tokenStore() {
    	return new JwtTokenStore(accessTokenConverter());
    }

}
