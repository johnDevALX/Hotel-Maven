package com.ekene.hotelmanagement.config.security;

import com.ekene.hotelmanagement.config.security.UserUserDetails;
import com.ekene.hotelmanagement.model.User;
import com.ekene.hotelmanagement.model.Users;
import com.ekene.hotelmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmailIgnoreCase(email);
        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("Username not found " + email));
    }
}
