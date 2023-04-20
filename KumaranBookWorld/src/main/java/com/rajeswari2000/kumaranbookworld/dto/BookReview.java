package com.rajeswari2000.kumaranbookworld.dto;

public class BookReview {

	String name;
	String email;
	int bookIdReview;
	String bookReview;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getBookIdReview() {
		return bookIdReview;
	}

	public String getBookReview() {
		return bookReview;
	}

	public BookReview(String name, String email, int bookIdReview, String bookReview) {
		super();
		this.name = name;
		this.email = email;
		this.bookIdReview = bookIdReview;
		this.bookReview = bookReview;
	}

}
