package com.dxcassessment.bookstoreapi.entity;

import java.util.Set;

import com.dxcassessment.bookstoreapi.validator.ValidISBN;
import com.querydsl.core.annotations.QueryEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@QueryEntity
@Table(name="BOOK")
public class Book {

	@Id
	@Column(name = "ISBN", unique = true, nullable = false)
	@ValidISBN
	private String isbn;
	
	@Column(name = "TITLE", nullable = false)
	@NotBlank(message = "Title is mandatory")
	private String title;
	
	@ManyToMany
    @JoinTable(
            name = "BOOK_AUTHOR",
            joinColumns = {
                    @JoinColumn(name = "BOOK_ISBN")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "AUTHOR_NAME", referencedColumnName = "NAME"),
                    @JoinColumn(name = "AUTHOR_BIRTHDAY", referencedColumnName = "BIRTHDAY")
            }
    )
	@NotNull(message = "Author is mandatory")
	@Size(min=1)
	private Set<Author> authors; 
	
	@Column(name = "YEAR", nullable = false)
	@NotNull(message = "Year is mandatory")
	@Digits(integer = 4, fraction = 0)
	private int year;
	
	@Column(name = "PRICE", nullable = false, columnDefinition = "DECIMAL(10, 2)")
	@NotNull(message = "Price is mandatory")
	@PositiveOrZero(message = "Price value is invalid")
	private double price;
	
	@Column(name = "GENRE", nullable = false)
	@NotBlank(message = "Genre is mandatory")
	private String genre;
	
	public Book() {
	}
	
	public Book(String isbn, String title, Set<Author> authors, int year, double price, String genre) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.authors = authors;
		this.year = year;
		this.price = price;
		this.genre = genre;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	
}
