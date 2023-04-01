package com.logiclabs.logicsprint.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.logiclabs.logicsprint.security.JwtTokenUtil;

@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                                     authenticationRequest.getPassword()));

        // Load user details from the database
        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(userDetails);

        // Return the token as response
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        if (Objects.isNull(user) || Objects.isNull(user.getUserName()) || Objects.isNull(user.getPass())) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }

        // Check if the user already exists in the database
        try {
            userDetailsService.loadUserByUsername(user.getUserName());
            return ResponseEntity.badRequest().body("User already exists");
        } catch (UsernameNotFoundException ex) {
            // User doesn't exist in the database, create a new user
            user.setPass(passwordEncoder.encode(user.getPass()));
            return ResponseEntity.ok(userRepository.save(user));
        }
    }
}
