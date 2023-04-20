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

import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class PopulateGenres
 */
@WebServlet("/PopulateGenres")
public class PopulateGenres extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
       List<String> allGenres = new ArrayList<>();
		
		Model model = new Model();
		
		String str = request.getParameter("");
		allGenres = model.fetchAllGenres();
		JSONArray jsonArray = new JSONArray();
		
		for(String genre: allGenres) {
			jsonArray.add(genre);
		}
		
		
		response.getWriter().print(jsonArray);
	}


}
