MySQL Configuration Setup
-------------------------
1) DB Name = bookstore
2) user = root (application.properties -> spring.datasource.username)
3) password = password (application.properties -> spring.datasource.password)
4) Ensure that MySQL server is running on port 8080, if not please update the port accordingly for application.properties -> spring.datasource.url


DDL Scripts
-----------
create table BOOK (
	ISBN varchar(255) not null,
	TITLE varchar(255) not null,
	YEAR int(4) not null,
	PRICE double not null,
	GENRE varchar(255) not null,
	primary key(ISBN)
);

create table AUTHOR (
	NAME varchar(255) not null,
	BIRTHDAY date not null,
	PRIMARY KEY (NAME, BIRTHDAY)
);

create table BOOK_AUTHOR (
	BOOK_ISBN VARCHAR(255) NOT NULL,
    AUTHOR_NAME VARCHAR(255) NOT NULL,
    AUTHOR_BIRTHDAY DATE NOT NULL,
   	FOREIGN KEY (BOOK_ISBN) REFERENCES BOOK(ISBN),
    FOREIGN KEY (AUTHOR_NAME, AUTHOR_BIRTHDAY) REFERENCES AUTHOR(NAME, BIRTHDAY)
);

create table user(
	ID int(32) not null auto_increment,
	NAME varchar(255) not null,
	primary key(ID)
);

create table role(
	ID int(32) not null auto_increment,
	ROLE_NAME varchar(255) not null,
	primary key(ID)
);

create table user_role(
	USER_ID int(32) not null,
	ROLE_ID int(32) not null,
	foreign key (USER_ID) references USER(ID),
	foreign key (ROLE_ID) references ROLE(ID)
);


DML Scripts
------------
INSERT INTO bookstore.`user` (NAME) VALUES
	 ('User A'),
	 ('Admin B');

INSERT INTO bookstore.`role` (ROLE_NAME) VALUES
	 ('ROLE_USER'),
	 ('ROLE_ADMIN');

INSERT INTO bookstore.user_role (USER_ID,ROLE_ID) VALUES
	 (1,1),
	 (2,2);
