package com.dxcassessment.bookstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.dxcassessment.bookstoreapi.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String>, QuerydslPredicateExecutor<Book> {

	public Book findByIsbn(String isbn);
	
}
