package com.clearingcobwebsbackend.configurations;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import com.clearingcobwebsbackend.security.TextEncoder;
import com.clearingcobwebsbackend.services.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity // Tells Spring to not use the out of the box Filter Chain
public class SecurityConfig {

  @Autowired
  private UserDetailsServiceImpl userDetailsServiceImpl;

  @Bean // HTTPSecurity is a Class which can return SecurityFilterChain type
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(customizer -> customizer.disable())
        .cors(cors -> cors.configurationSource(request -> {
          CorsConfiguration configuration = new CorsConfiguration();
          configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173"));
          configuration.setAllowedMethods(Arrays.asList("*"));
          configuration.setAllowedHeaders(Arrays.asList("*"));
          return configuration;
        }))
        .authorizeHttpRequests((request) -> {
          request
              .requestMatchers(HttpMethod.POST, "/users").permitAll()
              .requestMatchers(HttpMethod.POST, "/auth").permitAll()
              .requestMatchers(HttpMethod.GET, "/users/security-questions").permitAll()
              .requestMatchers(HttpMethod.GET, "/users").authenticated()
              .requestMatchers(HttpMethod.GET, "/email/**").authenticated()
              .anyRequest().permitAll();
        })
        .httpBasic(Customizer.withDefaults())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider customAuthProvider = new DaoAuthenticationProvider();
    customAuthProvider.setPasswordEncoder(Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
    customAuthProvider.setUserDetailsService(userDetailsServiceImpl);
    return customAuthProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

}
