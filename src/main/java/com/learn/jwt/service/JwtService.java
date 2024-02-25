package com.learn.jwt.service;

import com.learn.jwt.helper.JwtUtil;
import com.learn.jwt.model.JwtRequest;
import com.learn.jwt.model.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private JwtUtil jwtUtil;
    private CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;

    public JwtService(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    public JwtResponse authenticate(JwtRequest jwtRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("User not found with username: " + jwtRequest.getUsername());
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new BadCredentialsException("Bad Credentials for username: " + jwtRequest.getUsername());
        }
        
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);
        System.out.println("JWT Token: "+ jwtToken);
        return JwtResponse.builder().token(jwtToken).build();
    }
}
