package com.rajeswari2000.kumaranbookworld.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.rajeswari2000.kumaranbookworld.dto.Book;
import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class SearchBooks
 */
@WebServlet("/SearchBooks")
public class SearchBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         String searchValue = request.getParameter("searchValue");
         System.out.println(searchValue);
         JSONObject jsonObject = new JSONObject();
         Model model = new Model();
         
         Book book = model.getSearchedBook(searchValue);
		
		if(book==null) {
			response.getWriter().append("null");
		}
		else {
			jsonObject.put("bookID", book.getBookId());
			jsonObject.put("title", book.getTitle());
			jsonObject.put("author", book.getAuthor());
			jsonObject.put("publisher", book.getPublisher());
			jsonObject.put("bookDescription", book.getBookDescription());
			jsonObject.put("genre", book.getGenre());
			jsonObject.put("image", book.getImage());
			jsonObject.put("noOfBooks", book.getNoOfBooks());	
			
			
			
			
			response.getWriter().print(jsonObject);
		}
}



}
