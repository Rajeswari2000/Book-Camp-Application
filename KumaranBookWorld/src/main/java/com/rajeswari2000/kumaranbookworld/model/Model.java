package com.rajeswari2000.kumaranbookworld.model;

import java.util.List;

import com.rajeswari2000.kumaranbookworld.dto.Book;
import com.rajeswari2000.kumaranbookworld.dto.MyProfile;
import com.rajeswari2000.kumaranbookworld.dto.Schedule;
import com.rajeswari2000.kumaranbookworld.repository.*;

public class Model {

	public boolean loginValidation(String userName, String password) {
		return Repository.getInstance().loginValidation(userName, password);
	}

	public boolean userExistCheck(String email) {
		return Repository.getInstance().signUpVerify(email);
	}

	public boolean signUpUser(String name, String email, String password) {
		return Repository.getInstance().signUpUser(name, email, password);
	}

	public List<Book> fetchBooks() {
		return Repository.getInstance().fetchBooks();
	}

	public List<Schedule> fetchSchedule() {
		return Repository.getInstance().fetchSchedule();

	}

	public int getNoOfRegistrations(int scheduleId) {
		return Repository.getInstance().getNoOfRegistrations(scheduleId);
	}

	public boolean checkAlreadyRegistered(int scheduleId, Object email) {
		return Repository.getInstance().checkAlreadyRegistered(scheduleId, email);
	}

	public boolean registerUser(int scheduleId, String email) {
		return Repository.getInstance().registerUser(scheduleId, email);
	}

	public List<String> fetchAllAuthors() {
		return Repository.getInstance().getAllAuthors();
	}

	public List<String> fetchAllGenres() {
		return Repository.getInstance().getAllGenres();
	}

	public List<Book> fetchFilteredBooks(String authorFilter, String genreFilter) {
		return Repository.getInstance().fetchFilteredBooks(authorFilter, genreFilter);
	}

	public Book getSearchedBook(String searchValue) {
		return Repository.getInstance().getSearchedBook(searchValue);
	}

	public boolean addBookReview(String userReview, int bookId, String email) {
		return Repository.getInstance().addBookReview(userReview, bookId, email);

	}

	public MyProfile fetchMyProfile(String email) {
		return Repository.getInstance().fetchMyProfile(email);

	}

}
