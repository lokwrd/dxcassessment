package com.dxcassessment.bookstoreapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxcassessment.bookstoreapi.entity.QBook;
import com.dxcassessment.bookstoreapi.exception.BadRequestException;
import com.dxcassessment.bookstoreapi.exception.EntityNotFoundException;
import com.dxcassessment.bookstoreapi.exception.InvalidArgumentException;
import com.dxcassessment.bookstoreapi.entity.Author;
import com.dxcassessment.bookstoreapi.entity.Book;
import com.dxcassessment.bookstoreapi.repository.AuthorRepository;
import com.dxcassessment.bookstoreapi.repository.BookRepository;
import com.dxcassessment.bookstoreapi.service.BookService;
import com.querydsl.core.types.dsl.BooleanExpression;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Book addBook(Book book) throws BadRequestException {
		Optional<Book> bookRecord = bookRepository.findById(book.getIsbn());
		if(bookRecord.isEmpty()) {
			createAuthors(book.getAuthors());
			return bookRepository.save(book);
		}
		else
			throw new BadRequestException("Book " + book.getIsbn() + " already exists.");
	}

	@Override
	public Book updateBook(Book book) throws EntityNotFoundException {
		Optional<Book> bookRecord = bookRepository.findById(book.getIsbn());
		if (bookRecord.isPresent()) {
			createAuthors(book.getAuthors());
			return bookRepository.save(book);
		}
		else {
			throw new EntityNotFoundException("Book not found with ISBN: " + book.getIsbn());
		}
	}

	@Override
	public List<Book> retrieveBooks(String title, String author) throws InvalidArgumentException {
		if (title == null && author == null) {
			throw new InvalidArgumentException("No input parameters provided");
		}
		else {
			QBook qBook = QBook.book;
			BooleanExpression predicate = qBook.isNotNull();
			
	        if (title != null) {
	            predicate = predicate.and(qBook.title.eq(title));
	        }

	        if (author != null) {
	            predicate = predicate.and(qBook.authors.any().name.eq(author));
	        }
	        
	        return (List<Book>) bookRepository.findAll(predicate);
		}
	}

	@Override
	public String deleteBook(String isbn) throws EntityNotFoundException{
		Optional<Book> bookRecord = bookRepository.findById(isbn);
		if (bookRecord.isPresent()) {
			try {
				bookRepository.deleteById(isbn);
				return "Successfully deleted Book " + isbn;
			}catch (Exception e){
				return "Error encountered";
			}
		}
		throw new EntityNotFoundException("Book not found with ISBN: " + isbn);
	}
	
	private void createAuthors(Set<Author> authors) {
		for(Author authorRequest : authors)
		{
			Author authorRecord = authorRepository.findAuthorByNameAndBirthday(authorRequest.getName(), authorRequest.getBirthday());
			if(authorRecord == null) {
				authorRepository.saveAndFlush(authorRequest);
			}
		}
	}
	
	
}
