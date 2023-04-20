package com.rajeswari2000.kumaranbookworld.servlet;

import com.rajeswari2000.kumaranbookworld.model.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession httpSession = request.getSession();

		if(userName.equals("admin") && password.equals("Admin@6666")) {
			httpSession.setAttribute("username", userName);
			response.getWriter().print("admin");
		}
		else{
			Model model = new Model();
			if (httpSession.getAttribute("username") != null && httpSession.getAttribute("username").equals(userName)) {
				response.getWriter().append("alreadyLoggedIn");
			}
			else{
				if(model.loginValidation(userName, password)) {
					httpSession.setAttribute("username", userName);
					response.getWriter().print("" + model.loginValidation(userName, password));

				}
				else{
					response.getWriter().print("" + model.loginValidation(userName, password));
				}
			}
		}

	}
}
