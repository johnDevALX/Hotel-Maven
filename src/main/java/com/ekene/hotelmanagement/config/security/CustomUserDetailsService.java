package com.ekene.hotelmanagement.config.security;

import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
//@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmailIgnoreCase(username);
        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
    }
}
