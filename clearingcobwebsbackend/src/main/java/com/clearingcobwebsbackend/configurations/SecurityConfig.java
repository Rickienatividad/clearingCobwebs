package com.clearingcobwebsbackend.configurations;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
        .authorizeHttpRequests((request) -> {
          request
              .requestMatchers(HttpMethod.POST, "/users").permitAll()
              .requestMatchers("/users/security-questions").permitAll()
              .requestMatchers(HttpMethod.GET, "/users").authenticated()
              .requestMatchers(HttpMethod.GET, "/email/**").authenticated();
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

}
