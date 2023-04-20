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

import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class PopulateAuthors
 */
@WebServlet("/PopulateAuthors")
public class PopulateAuthors extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> allAuthors = new ArrayList<>();
		
		Model model = new Model();
		
		
		allAuthors = model.fetchAllAuthors();	
		
         JSONArray jsonArray = new JSONArray();
		
		for(String author: allAuthors) {
			jsonArray.add(author);
		}
		
		
		response.getWriter().print(jsonArray);
	}


}
