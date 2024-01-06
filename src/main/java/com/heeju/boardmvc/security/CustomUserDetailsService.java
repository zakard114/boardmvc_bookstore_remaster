package com.heeju.boardmvc.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder; // Added to briefly test the PasswordEncoder object.

    // Here, BCryptPasswordEncoder is created to temporarily operate the PasswordEncoder object.
    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // Spring Security operates internally by using a UserDetails type object to
    // check passwords and check user permissions.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername: "+username);

        // Spring Security's API provides a class called User that implements
        // the UserDetails interface. Through this, you can log in.
        UserDetails userDetails = User.builder().username("user1")
                .password(passwordEncoder.encode("1111")) // Password encoding required
                .authorities("ROLE_USER")
                .build();

        return userDetails;
    }





}
