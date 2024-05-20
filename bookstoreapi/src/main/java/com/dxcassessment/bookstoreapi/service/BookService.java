package com.dxcassessment.bookstoreapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dxcassessment.bookstoreapi.entity.Book;
import com.dxcassessment.bookstoreapi.exception.BadRequestException;
import com.dxcassessment.bookstoreapi.exception.EntityNotFoundException;
import com.dxcassessment.bookstoreapi.exception.InvalidArgumentException;

@Service
public interface BookService {

	Book addBook(Book book) throws BadRequestException;
	
	Book updateBook(Book book) throws EntityNotFoundException;
	
	List<Book> retrieveBooks(String title, String author) throws InvalidArgumentException;

	String deleteBook(String isbn) throws EntityNotFoundException;
}
