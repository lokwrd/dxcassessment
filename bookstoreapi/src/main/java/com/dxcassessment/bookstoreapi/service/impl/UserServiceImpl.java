package com.dxcassessment.bookstoreapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dxcassessment.bookstoreapi.entity.Role;
import com.dxcassessment.bookstoreapi.entity.User;
import com.dxcassessment.bookstoreapi.repository.UserRepository;
import com.dxcassessment.bookstoreapi.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    
    
    public List<String> getUserRoles(String userId) {
        Optional<User> user = userRepository.findById(userId);
        
        if(user.isPresent())
        	return user.get().getUserRoles().stream()
                               .map(Role::getRoleName)
                               .collect(Collectors.toList());
        return null;
    }
    
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String userId) {
                return userRepository.findById(userId)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}

