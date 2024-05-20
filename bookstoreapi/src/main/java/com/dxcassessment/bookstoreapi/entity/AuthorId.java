package com.dxcassessment.bookstoreapi.entity;

import java.sql.Date;

public class AuthorId {
	
    private String name;
    
    private Date birthday;

	public AuthorId() {
		super();
	}
    
	public AuthorId(String name, Date birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
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
    
    
}
