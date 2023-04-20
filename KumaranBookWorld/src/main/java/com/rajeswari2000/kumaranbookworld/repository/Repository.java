package com.rajeswari2000.kumaranbookworld.repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.rajeswari2000.kumaranbookworld.dto.Book;
import com.rajeswari2000.kumaranbookworld.dto.BookReview;
import com.rajeswari2000.kumaranbookworld.dto.MyProfile;
import com.rajeswari2000.kumaranbookworld.dto.Schedule;

public class Repository {

	/***********************************
	 * Repository set up
	 ******************************/

	private Repository() {

	}

	public static Connection connection;
	private static Repository repository;

	public static Repository getInstance() {
		try {
			if (repository == null) {
				repository = new Repository();
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld", "root",
						"Rajesh@2000");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return repository;
	}

	Statement statement;

	/***********************************
	 * Login validation
	 ******************************/

	public boolean loginValidation(String userName, String password) {
		String loginValidation;
		boolean flag = false;

		try {
			statement = connection.createStatement();
			loginValidation = "select * from users where email='" + userName + "' && password ='" + password + "';";
			ResultSet resultSet = statement.executeQuery(loginValidation);
			if (resultSet.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside login validation");
		}
		return flag;

	}

	/***********************************
	 * Sign up verification
	 ******************************/

	public boolean signUpVerify(String email) {
		String signUpVerification;
		boolean flag = false;

		try {
			statement = connection.createStatement();
			signUpVerification = "select * from users where email='" + email + "';";
			ResultSet resultSet = statement.executeQuery(signUpVerification);
			if (resultSet.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside sign up verify");
		}
		return flag;

	}

	/***********************************
	 * Sign up add user
	 ******************************/

	public boolean signUpUser(String name, String email, String password) {
		String signUpUser;
		boolean flag = false;

		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO users (name, email, password)VALUES(?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside add user");
		}
		return false;
	}

	/***********************************
	 * Fetch all books
	 ******************************/

	public List<Book> fetchBooks() {
		List<Book> books = new ArrayList();

		String fetchBooks;
		try {
			Connection connection5 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld", "root",
					"Rajesh@2000");

			Statement statement1 = connection5.createStatement();
			fetchBooks = "select * from books";
			ResultSet resultSet = statement1.executeQuery(fetchBooks);

			while (resultSet.next()) {
				int bookId = resultSet.getInt("book_id");
				String bookName = resultSet.getString("title");
				String author = resultSet.getString("author");
				String publisher = resultSet.getString("publisher");
				String bookDescription = resultSet.getString("book_description");
				String genre = resultSet.getString("genre");
				String image = resultSet.getString("image");
				int noOfBooks = resultSet.getInt("no_of_books");

				Connection connection9 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld",
						"root", "Rajesh@2000");
				Statement statement9 = connection9.createStatement();
				String fetchReview = "select bookReview.email,bookReview.book_id,bookReview.reviewOfBook , users.name from bookReview join users on users.email=bookReview.email where bookReview.book_id='"
						+ bookId + "';";
				ResultSet resultSet2 = statement9.executeQuery(fetchReview);

				BookReview bookReviewObject = null;
				List<BookReview> bookReviewList = new ArrayList();
				while (resultSet2.next()) {
					String name = resultSet2.getString("name");
					String bookReview = resultSet2.getString("reviewOfBook");
					int bookIdReview = resultSet2.getInt("book_id");
					String email = resultSet2.getString("email");
					bookReviewObject = new BookReview(name, email, bookIdReview, bookReview);
					bookReviewList.add(bookReviewObject);
				}

				books.add(new Book(bookId, bookName, author, publisher, bookDescription, genre, image, noOfBooks,
						bookReviewList));
			}
			connection5.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside fetch books");
		}

		return books;

	}

	public List<Schedule> fetchSchedule() {
		List<Schedule> allSchedule = new ArrayList();

		String fetchSchedule;
		try {
			statement = connection.createStatement();
			fetchSchedule = "select *from schedule ";
			ResultSet resultSet = statement.executeQuery(fetchSchedule);
			while (resultSet.next()) {
				int scheduleId = resultSet.getInt("schedule_id");
				Date date = resultSet.getDate("dateOfCamp");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				Time starTime = resultSet.getTime("startTime");
				Time endTime = resultSet.getTime("endTime");

				allSchedule.add(new Schedule(scheduleId, date, address, city, starTime, endTime));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside fetch schedule");
		}

		return allSchedule;
	}

	public int getNoOfRegistrations(int scheduleId) {

		/*
		 * create table registration( resgistration_id int primary key auto_increment,
		 * email varchar(30), schedule_id int);
		 */
		ResultSet resultSet;
		int count = 0;
		String getNoOfRegistrations;
		try {
			statement = connection.createStatement();
			getNoOfRegistrations = "select count(schedule_id) as noOfRegistrations from registration where schedule_id= '"
					+ scheduleId + "';";
			resultSet = statement.executeQuery(getNoOfRegistrations);
			if (resultSet.next()) {
				count = resultSet.getInt("noOfRegistrations");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside get no of registations");
		}
		return count;
	}

	public boolean checkAlreadyRegistered(int scheduleId, Object email) {

		String checkAlreadyRegistered;

		try {
			statement = connection.createStatement();
			checkAlreadyRegistered = "select * from registration where schedule_id='" + scheduleId + "'&&email='"
					+ email + "';";
			ResultSet resultSet = statement.executeQuery(checkAlreadyRegistered);
			if (resultSet.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside check already registered");
		}

		return false;
	}

	public boolean registerUser(int scheduleId, String email) {
		String registerUser;

		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO registration (email, schedule_id)VALUES(?,?)");
			ps.setString(1, email);
			ps.setInt(2, scheduleId);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<String> getAllAuthors() {

		List<String> allAuthors = new ArrayList<>();
		String fetchAllAuthors;
		try {
			Connection connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld", "root",
					"Rajesh@2000");

			Statement statement3 = connection3.createStatement();

			fetchAllAuthors = "select distinct(author) from books";
			ResultSet resultSet = statement3.executeQuery(fetchAllAuthors);

			while (resultSet.next()) {
				allAuthors.add(resultSet.getString("author"));
			}
			connection3.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside fetch all author");
		}

		return allAuthors;
	}

	public List<String> getAllGenres() {

		List<String> allGenres = new ArrayList<>();
		String fetchAllGenres;
		Connection connection2 = null;
		try {

			connection2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld", "root",
					"Rajesh@2000");

			Statement statement4 = connection2.createStatement();

			fetchAllGenres = "select distinct(genre) from books";
			ResultSet resultSet = statement4.executeQuery(fetchAllGenres);

			while (resultSet.next()) {
				allGenres.add(resultSet.getString("genre"));

			}

			connection2.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside fetch all genre");
		}

		return allGenres;

	}

	public List<Book> fetchFilteredBooks(String authorFilter, String genreFilter) {
		List<Book> filteredBooks = new ArrayList();

		if (authorFilter.equals("none") && !genreFilter.equals("none")) {
			String fetchBooks;
			try {
				Connection connection3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld",
						"root", "Rajesh@2000");

				Statement statement5 = connection3.createStatement();
				fetchBooks = "select * from books where genre='" + genreFilter + "';";
				ResultSet resultSet = statement5.executeQuery(fetchBooks);

				while (resultSet.next()) {
					int bookId = resultSet.getInt("book_id");
					String bookName = resultSet.getString("title");
					String author = resultSet.getString("author");
					String publisher = resultSet.getString("publisher");
					String bookDescription = resultSet.getString("book_description");
					String genre = resultSet.getString("genre");
					String image = resultSet.getString("image");
					int noOfBooks = resultSet.getInt("no_of_books");
					filteredBooks.add(new Book(bookId, bookName, author, publisher, bookDescription, genre, image,
							noOfBooks, null));
				}
				connection3.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("sql exception inside fetch filtered books1");
			}
		} else if (!authorFilter.equals("none") && genreFilter.equals("none")) {
			String fetchBooks;
			try {
				Connection connection4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld",
						"root", "Rajesh@2000");

				Statement statement6 = connection4.createStatement();
				fetchBooks = "select * from books where author='" + authorFilter + "';";
				ResultSet resultSet = statement6.executeQuery(fetchBooks);

				while (resultSet.next()) {
					int bookId = resultSet.getInt("book_id");
					String bookName = resultSet.getString("title");
					String author = resultSet.getString("author");
					String publisher = resultSet.getString("publisher");
					String bookDescription = resultSet.getString("book_description");
					String genre = resultSet.getString("genre");
					String image = resultSet.getString("image");
					int noOfBooks = resultSet.getInt("no_of_books");
					filteredBooks.add(new Book(bookId, bookName, author, publisher, bookDescription, genre, image,
							noOfBooks, null));

				}
				connection4.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("sql exception inside fetch filtered books2");
			}

		} else {
			String fetchBooks;
			try {
				Connection connection4 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld",
						"root", "Rajesh@2000");

				Statement statement7 = connection4.createStatement();
				fetchBooks = "select * from books where genre='" + genreFilter + "' && author='" + authorFilter + "';";
				ResultSet resultSet = statement7.executeQuery(fetchBooks);

				while (resultSet.next()) {
					int bookId = resultSet.getInt("book_id");
					String bookName = resultSet.getString("title");
					String author = resultSet.getString("author");
					String publisher = resultSet.getString("publisher");
					String bookDescription = resultSet.getString("book_description");
					String genre = resultSet.getString("genre");
					String image = resultSet.getString("image");
					int noOfBooks = resultSet.getInt("no_of_books");
					filteredBooks.add(new Book(bookId, bookName, author, publisher, bookDescription, genre, image,
							noOfBooks, null));
				}
				connection4.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("sql exception inside fetch filtered books3");
			}
		}

		return filteredBooks;

	}

	public Book getSearchedBook(String searchValue) {
		Book book = null;
		String fetchBook;
		System.out.println("search value" + searchValue);

		try {
			Connection connection7 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld", "root",
					"Rajesh@2000");

			Statement statement8 = connection7.createStatement();
			fetchBook = "select * from books where title like '%" + searchValue + "%'";
			ResultSet resultSet = statement8.executeQuery(fetchBook);

			if (resultSet.next()) {
				int bookId = resultSet.getInt("book_id");
				String bookName = resultSet.getString("title");
				String author = resultSet.getString("author");
				String publisher = resultSet.getString("publisher");
				String bookDescription = resultSet.getString("book_description");
				String genre = resultSet.getString("genre");
				String image = resultSet.getString("image");
				int noOfBooks = resultSet.getInt("no_of_books");
				book = new Book(bookId, bookName, author, publisher, bookDescription, genre, image, noOfBooks, null);

			}

			connection7.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside search books");
		}

		return book;

	}

	public boolean addBookReview(String userReview, int bookId, String email) {

		try {
			Connection connection10 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld",
					"root", "Rajesh@2000");

			Statement statement10 = connection10.createStatement();
			String addBookReview = String.format(
					"insert into  bookReview (email,book_id,reviewOfBook) values (\"%s\",%d,\"%s\")", email, bookId,
					userReview);
			statement10.execute(addBookReview);
			connection10.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside add book review");
		}

		return false;
	}

	public MyProfile fetchMyProfile(String email) {
		MyProfile myProfile = null;

		List<Schedule> userScheduleList = new ArrayList<>();
		HashMap<String, BookReview> hashMap = new HashMap<>();

		String fetchIndividualReview;

		try {
			Connection connection11 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld",
					"root", "Rajesh@2000");

			Statement statement11 = connection11.createStatement();
			fetchIndividualReview = "select bookReview.email,bookReview.book_id,bookReview.reviewOfBook , users.name from bookReview join users on users.email=bookReview.email where bookReview.email= '"
					+ email + "';";
			ResultSet resultSet = statement11.executeQuery(fetchIndividualReview);

			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String bookReview = resultSet.getString("reviewOfBook");
				int bookIdReview = resultSet.getInt("book_id");
				String emailId = resultSet.getString("email");
				BookReview bookReviewObject = new BookReview(name, emailId, bookIdReview, bookReview);

				Statement statement12 = connection11.createStatement();
				String getBookName = "select title from books where book_id='" + bookIdReview + "';";
				ResultSet resultSet2 = statement12.executeQuery(getBookName);
				resultSet2.next();
				String bookName = resultSet2.getString("title");

				hashMap.put(bookName, bookReviewObject);
			}

			connection11.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside fetch profile 1");
		}

		try {
			Connection connection13 = DriverManager.getConnection("jdbc:mysql://localhost:3306/kumaranbookworld",
					"root", "Rajesh@2000");

			Statement statement13 = connection13.createStatement();
			String fetchIndividualRegistrations = "select schedule.schedule_id, schedule.dateOfCamp, schedule.address, schedule.city, schedule.startTime, schedule.endTime from schedule join registration  on schedule.schedule_id = registration.schedule_id where email= '"
					+ email + "';";
			ResultSet resultSet = statement13.executeQuery(fetchIndividualRegistrations);

			while (resultSet.next()) {
				int scheduleId = resultSet.getInt("schedule_id");
				Date date = resultSet.getDate("dateOfCamp");
				String address = resultSet.getString("address");
				String city = resultSet.getString("city");
				Time starTime = resultSet.getTime("startTime");
				Time endTime = resultSet.getTime("endTime");
				userScheduleList.add(new Schedule(scheduleId, date, address, city, starTime, endTime));
			}

			connection13.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("sql exception inside fetch profile 2");
		}

		myProfile = new MyProfile(userScheduleList, hashMap);

		return myProfile;
	}

}
