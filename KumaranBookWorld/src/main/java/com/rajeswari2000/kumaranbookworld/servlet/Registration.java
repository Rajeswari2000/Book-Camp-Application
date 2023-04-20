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
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId")) ;
		Model model = new Model();
		
		// check if user is already logged in
		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("username")==null) {
			response.getWriter().append("notLoggedIn");
		}
		
		// check if user has already registered
		else if(model.checkAlreadyRegistered(scheduleId,httpSession.getAttribute("username"))){
		    response.getWriter().append("alreadyRegistered");
	    }
		
		//check if registration limit exceeded
		else if(model.getNoOfRegistrations(scheduleId)>40) {
			response.getWriter().append("registrationLimitExceeded");
		}
		//register user
		else if(model.registerUser(scheduleId,(String)httpSession.getAttribute("username"))) {
			response.getWriter().append("true");
		}
		
	
		
	}

}
