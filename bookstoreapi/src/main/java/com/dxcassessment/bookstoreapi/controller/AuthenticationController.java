package com.dxcassessment.bookstoreapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dxcassessment.bookstoreapi.entity.User;
import com.dxcassessment.bookstoreapi.repository.UserRepository;
import com.dxcassessment.bookstoreapi.security.jwt.JwtTokenProvider;

@RestController
public class AuthenticationController {

	@Autowired
    private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository userRepository;
	
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestHeader("userId") String userId) {

    	User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));
        String jwt = jwtTokenProvider.generateToken(user);

        return ResponseEntity.ok(jwt);
    }
}
