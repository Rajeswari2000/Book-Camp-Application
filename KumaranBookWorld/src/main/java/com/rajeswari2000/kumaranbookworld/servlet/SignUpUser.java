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
 * Servlet implementation class SignUpUser
 */
@WebServlet("/SignUpUser")
public class SignUpUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
		
		Model model = new Model();
		HttpSession httpSession = request.getSession();

		if(model.userExistCheck(email)) {
			response.getWriter().append("userExist");
		}
		else {
		
		response.getWriter().append("" + model.signUpUser(name, email,password));
        
		}
		
	}

}
