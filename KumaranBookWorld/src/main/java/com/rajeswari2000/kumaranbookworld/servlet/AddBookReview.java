package com.rajeswari2000.kumaranbookworld.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class AddBookReview
 */
@WebServlet("/AddBookReview")
public class AddBookReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Model model = new Model();
		String userReview = request.getParameter("review");
		int bookId = Integer.
				parseInt(request.getParameter("bookId"));
		
		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("username")==null) {
			response.getWriter().append("notLoggedIn");
		}
		else if(model.addBookReview(userReview,bookId,(String)httpSession.getAttribute("username"))) {
			response.getWriter().append("true");	
		}
		else {
			response.getWriter().append("false");	
		}
	}
}
