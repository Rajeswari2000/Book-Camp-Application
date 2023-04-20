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
 * Servlet implementation class CheckIfAlreadyRegistered
 */
@WebServlet("/CheckIfAlreadyRegistered")
public class CheckIfAlreadyRegistered extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId")) ;
		Model model  = new Model();
		HttpSession session = request.getSession();
		
		response.getWriter().append(""+ model.checkAlreadyRegistered(scheduleId,session.getAttribute("username")));
	}

}
