package com.rajeswari2000.kumaranbookworld.servlet;

import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rajeswari2000.kumaranbookworld.dto.BookReview;
import com.rajeswari2000.kumaranbookworld.dto.MyProfile;
import com.rajeswari2000.kumaranbookworld.dto.Schedule;
import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class FetchMyProfile
 */
@WebServlet("/FetchMyProfile")
public class FetchMyProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Model model = new Model();
		HttpSession httpSession = request.getSession();
        String email = (String)httpSession.getAttribute("username");
		
        JSONArray jsonArray = new JSONArray();
		
        JSONArray jsonArray1 = new JSONArray();
        
        JSONArray jsonArray2 = new JSONArray();
		
		
        MyProfile myProfile = model.fetchMyProfile(email);
		
		
		if(myProfile.getHashMap().isEmpty()) {
		   response.getWriter().append("noReview");
		}
		if(myProfile.getUserScheduleList().isEmpty()) {
		   response.getWriter().append("noScheduleList");	
		}
		else {
			List<Schedule> scheduleList = myProfile.getUserScheduleList();
			HashMap<String, BookReview> hashMap = myProfile.getHashMap();
			
			
			for(Schedule schedule: scheduleList) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("scheduleId", schedule.getScheduleId());
				jsonObject.put("date", ""+schedule.getDate());
				jsonObject.put("address", schedule.getAddress());
				jsonObject.put("city", schedule.getCity());
				
				jsonObject.put("timings", getTimings(schedule.getStarTime(),schedule.getEndTime()));
				jsonArray1.add(jsonObject);
			}

			for(Map.Entry<String,BookReview> mapEntry: hashMap.entrySet()) {
				JSONObject jsonReviewObject = new JSONObject();
				jsonReviewObject.put("bookName",mapEntry.getKey());
				jsonReviewObject.put("name",mapEntry.getValue().getName());
				jsonReviewObject.put("emailId", mapEntry.getValue().getEmail());
				jsonReviewObject.put("bookReview", mapEntry.getValue().getBookReview());
				jsonArray2.add(jsonReviewObject);
			}
			
			jsonArray.add(jsonArray1);
			jsonArray.add(jsonArray2);
			
			response.getWriter().print(jsonArray);	
			
		}
		
		
		
		
		
		
	}
	
public String getTimings(Time start, Time end) {
		

		int startHour = start.toLocalTime().getHour();
		int endHour = end.toLocalTime().getHour();
		String startStamp ="am" ;
		String endStamp ="am";
		int sH = startHour%12; 
		int eH = endHour%12;
		
		if(sH==0) {
			sH=12;
			startStamp="pm";
		}
		if(eH==0){
			eH=12;
			endStamp="pm";
		}
		if(startHour>12) {
			
			startStamp = startHour==24 ? "am":"pm";		
		}
		
		if(endHour>12) {
			endStamp = endHour==24 ? "am":"pm";
		}
		
		return  ""+sH +startStamp+ " - "+ eH+endStamp;
		
	}


}
