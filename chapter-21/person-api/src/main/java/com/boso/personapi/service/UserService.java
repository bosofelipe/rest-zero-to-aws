package com.boso.personapi.service;

import com.boso.personapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
    final var user = userRepository.findByUserName(userName);
    if (user != null) {
      return user;
    }
    throw new UsernameNotFoundException("Usernname " + userName + " not found");
  }
}
