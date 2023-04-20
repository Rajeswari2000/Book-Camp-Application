package com.rajeswari2000.kumaranbookworld.dto;

import java.util.List;

public class Book {

	int bookId;
	String title;
	String author;
	String publisher;
	String bookDescription;
	String genre;
	String image;
	int noOfBooks;
	List<BookReview> bookReviewList;

	public Book(int bookId, String title, String author, String publisher, String bookDescription, String genre,
			String image, int noOfBooks, List<BookReview> bookReviewList) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.bookDescription = bookDescription;
		this.genre = genre;
		this.image = image;
		this.noOfBooks = noOfBooks;
		this.bookReviewList = bookReviewList;
	}

	public int getBookId() {
		return bookId;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public String getGenre() {
		return genre;
	}

	public String getImage() {
		return image;
	}

	public int getNoOfBooks() {
		return noOfBooks;
	}

	public List<BookReview> getBookReviewList() {
		return bookReviewList;
	}

}
