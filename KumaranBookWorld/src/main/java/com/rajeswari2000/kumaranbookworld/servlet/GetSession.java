package com.rajeswari2000.kumaranbookworld.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetSession
 */
@WebServlet("/GetSession")
public class GetSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession();
		
		if(httpSession.getAttribute("username")!=null && httpSession.getAttribute("username").equals("admin")){
			 response.getWriter().append("adminLogin");			
		}
		else if(httpSession.getAttribute("username")!=null) {
			   response.getWriter().append("true");
			}
		else {
			response.getWriter().append("false");
		}
	}

}
