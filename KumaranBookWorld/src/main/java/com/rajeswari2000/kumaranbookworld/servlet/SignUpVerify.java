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
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUpVerify")
public class SignUpVerify extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Model model = new Model();
		response.getWriter().append(""+model.userExistCheck(email));
	}

}
