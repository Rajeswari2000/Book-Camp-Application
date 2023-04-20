package com.rajeswari2000.kumaranbookworld.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rajeswari2000.kumaranbookworld.dto.Book;
import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class FetchFilteredBooks
 */
@WebServlet("/FetchFilteredBooks")
public class FetchFilteredBooks extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String authorFilter = request.getParameter("authorOptions");
		String genreFilter = request.getParameter("genreOptions");
		
		
		System.out.println(authorFilter);
		System.out.println(genreFilter);
		
        Model model = new Model();
		
		JSONArray jsonArray = new JSONArray();
		List<Book> filteredBooks = new ArrayList();
		filteredBooks = model.fetchFilteredBooks(authorFilter,genreFilter);
		
		
		
		if(filteredBooks.isEmpty())response.getWriter().append("null");
		else {
		for (Book book : filteredBooks) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("bookID", book.getBookId());
			jsonObject.put("title", book.getTitle());
			jsonObject.put("author", book.getAuthor());
			jsonObject.put("publisher", book.getPublisher());
			jsonObject.put("bookDescription", book.getBookDescription());
			jsonObject.put("genre", book.getGenre());
			jsonObject.put("image", book.getImage());
			jsonObject.put("noOfBooks", book.getNoOfBooks());
			
			jsonArray.add(jsonObject);
		}
		
		   response.getWriter().print(jsonArray);
		}
		
	}

	
	

}
