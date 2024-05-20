package com.dxcassessment.bookstoreapi.entity;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@IdClass(AuthorId.class)
@Table(name = "AUTHOR")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "books"})
public class Author {
	
	@Id
	@Column(name = "NAME", nullable = false)
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	@Id
	@Column(name = "BIRTHDAY", nullable = false)
	@NotNull(message = "Birthday is mandatory")
	private Date birthday;
	
	@ManyToMany(mappedBy = "authors")
	private Set<Book> books; 

	public Author() {
	}

	public Author(String name, Date birthday, Set<Book> books) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
}
