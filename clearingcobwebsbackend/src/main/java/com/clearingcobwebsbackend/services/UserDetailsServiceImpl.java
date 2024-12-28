package com.clearingcobwebsbackend.services;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity user = userRepository.findByEmail(email).get();
    if (user == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return new UserEntityDetails(user);
  }
}
