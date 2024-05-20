package com.dxcassessment.bookstoreapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dxcassessment.bookstoreapi.entity.Book;
import com.dxcassessment.bookstoreapi.exception.EntityNotFoundException;
import com.dxcassessment.bookstoreapi.exception.InvalidArgumentException;
import com.dxcassessment.bookstoreapi.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping("/add")
	public ResponseEntity<Book> saveBook(@Valid @RequestBody Book book) throws Exception{
		Book bookresponse = bookService.addBook(book);
		return new ResponseEntity<>(bookresponse, HttpStatus.OK);
	}

	@PostMapping("/update")
	public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) throws EntityNotFoundException{
		Book bookresponse = bookService.updateBook(book);
		return new ResponseEntity<>(bookresponse, HttpStatus.OK);
	}
	
	@GetMapping("/retrieve")
	public ResponseEntity<List<Book>> retrieveBooks(@RequestParam(required = false) String title, @RequestParam(required = false) String author) throws InvalidArgumentException{
		List<Book> bookresponseList = bookService.retrieveBooks(title, author);
		return new ResponseEntity<>(bookresponseList, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/{isbn}/delete")
	public ResponseEntity<String> deleteBook(@PathVariable("isbn") String isbn) throws EntityNotFoundException {
		String response = bookService.deleteBook(isbn);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
