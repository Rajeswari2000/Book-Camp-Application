package com.rajeswari2000.kumaranbookworld.servlet;

import com.rajeswari2000.kumaranbookworld.model.*;
import com.rajeswari2000.kumaranbookworld.dto.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

/**
 * Servlet implementation class FetchBooks
 */
@WebServlet("/FetchBooks")
public class FetchBooks extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Model model = new Model();

		JSONArray jsonArray = new JSONArray();

		List<Book> allBooks = new ArrayList();
		allBooks = model.fetchBooks();

		for (Book book : allBooks) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("bookId", book.getBookId());
			jsonObject.put("title", book.getTitle());
			jsonObject.put("author", book.getAuthor());
			jsonObject.put("publisher", book.getPublisher());
			jsonObject.put("bookDescription", book.getBookDescription());
			jsonObject.put("genre", book.getGenre());
			jsonObject.put("image", book.getImage());
			jsonObject.put("noOfBooks", book.getNoOfBooks());

			JSONArray jsonArrayOfReviews = new JSONArray();

			List<BookReview> bookReviewList = book.getBookReviewList();

			for (BookReview bookReview : bookReviewList) {
				JSONObject jsonReviewObject = new JSONObject();
				jsonReviewObject.put("name", bookReview.getName());
				jsonReviewObject.put("bookReview", bookReview.getBookReview());
				jsonArrayOfReviews.add(jsonReviewObject);
			}

			jsonObject.put("reviewArray", jsonArrayOfReviews);

			jsonArray.add(jsonObject);
		}

		response.getWriter().print(jsonArray);
	}

}
