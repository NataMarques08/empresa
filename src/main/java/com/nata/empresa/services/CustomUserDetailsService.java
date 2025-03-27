package com.nata.empresa.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nata.empresa.data.dto.RegisterRequestDTO;
import com.nata.empresa.model.user.User;
import com.nata.empresa.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

   

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();   
    }

    public String registerUser(RegisterRequestDTO request){
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return "User already exists";
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(request.getPassword());

        User user = new User(request.getUsername(), encryptedPassword); 
 
        userRepository.save(user);
        return "Success to save new user";

    }       

}
