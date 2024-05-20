package com.dxcassessment.bookstoreapi.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxcassessment.bookstoreapi.entity.Author;
import com.dxcassessment.bookstoreapi.entity.AuthorId;

@Repository
public interface AuthorRepository extends JpaRepository<Author, AuthorId> {
	
	 @Query(value = "SELECT * FROM author WHERE name = :name AND birthday = :birthday", nativeQuery = true)
	 Author findAuthorByNameAndBirthday(@Param("name") String name, @Param("birthday") Date birthday);
}
