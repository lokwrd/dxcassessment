package com.dxcassessment.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxcassessment.bookstoreapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
}
