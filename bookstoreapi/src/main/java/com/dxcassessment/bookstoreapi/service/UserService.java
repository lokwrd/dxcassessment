package com.dxcassessment.bookstoreapi.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

	List<String> getUserRoles(String userId);
	
	UserDetailsService userDetailsService();
}
