package com.rajeswari2000.kumaranbookworld.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class GetNoOfRegistrations
 */
@WebServlet("/GetNoOfRegistrations")
public class GetNoOfRegistrations extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int scheduleId = Integer.parseInt(request.getParameter("scheduleId")) ;
		Model model  = new Model();
		int noOfRegistrations = model.getNoOfRegistrations(scheduleId);
		
		
		if(noOfRegistrations>40) {
			response.getWriter().append(""+false);
		}
		response.getWriter().append(""+true);
	}

}
