package com.nata.empresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nata.empresa.data.dto.AuthRequestDTO;
import com.nata.empresa.data.dto.AuthResponseDTO;
import com.nata.empresa.data.dto.RegisterRequestDTO;
import com.nata.empresa.security.jwt.JwtUtil;
import com.nata.empresa.services.CustomUserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;


    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO requestDTO){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDTO.getUsername(), requestDTO.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(requestDTO.getUsername());
        return new AuthResponseDTO(jwtUtil.generateToken(userDetails));
    }
    
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO requestDTO){
        return userDetailsService.registerUser(requestDTO);
    }
    

}
