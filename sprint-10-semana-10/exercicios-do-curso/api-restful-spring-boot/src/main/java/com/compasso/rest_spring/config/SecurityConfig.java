package com.compasso.rest_spring.config;

import java.util.HashMap;
import java.util.Map;

import com.compasso.rest_spring.security.jwt.JwtTokenFilter;
import com.compasso.rest_spring.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@EnableMethodSecurity
@EnableWebMvc
@Configuration
public class SecurityConfig {

	@Autowired
	private JwtTokenProvider tokenProvider;

	private static final String[] DOCUMENTATION_OPENAPI = {
			"/docs/index.html",
			"/docs-park.html", "/docs-park/**",
			"/v3/api-docs/**",
			"/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
			"/**.html", "/webjars/**", "/configuration/**", "/swagger-resources/**",
	};

	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();

		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder;
	}

	@Bean
	AuthenticationManager authenticationManagerBean(
			AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) {
		JwtTokenFilter customFilter = new JwtTokenFilter(tokenProvider);

		try {
			return http
					.csrf(AbstractHttpConfigurer::disable)
					.formLogin(AbstractHttpConfigurer::disable)
					.httpBasic(AbstractHttpConfigurer::disable)
					.authorizeHttpRequests(
							auth -> auth
									.requestMatchers(HttpMethod.POST, "auth/signin").permitAll()
									.requestMatchers(HttpMethod.POST, "auth/refresh/**").permitAll()
									.requestMatchers(DOCUMENTATION_OPENAPI).permitAll()
									.anyRequest().authenticated()
					).sessionManagement(
							session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					).cors(
							cors -> {}
					).addFilterBefore(
							customFilter, UsernamePasswordAuthenticationFilter.class
					).build();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
