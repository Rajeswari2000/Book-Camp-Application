package com.rajeswari2000.kumaranbookworld.servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.rajeswari2000.kumaranbookworld.dto.Schedule;
import com.rajeswari2000.kumaranbookworld.model.Model;

/**
 * Servlet implementation class FetchSchedule
 */
@WebServlet("/FetchSchedule")
public class FetchSchedule extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Model model = new Model();

		JSONArray jsonArray = new JSONArray();
		List<Schedule> allSchedule = new ArrayList();
		allSchedule = model.fetchSchedule();

		for (Schedule schedule : allSchedule) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("scheduleId", schedule.getScheduleId());
			jsonObject.put("date", "" + schedule.getDate());
			jsonObject.put("address", schedule.getAddress());
			jsonObject.put("city", schedule.getCity());

			jsonObject.put("timings", getTimings(schedule.getStarTime(), schedule.getEndTime()));
			jsonArray.add(jsonObject);
		}

		response.getWriter().print(jsonArray);
	}

	public String getTimings(Time start, Time end) {

		int startHour = start.toLocalTime().getHour();
		int endHour = end.toLocalTime().getHour();
		String startStamp = "am";
		String endStamp = "am";
		int sH = startHour % 12;
		int eH = endHour % 12;

		if (sH == 0) {
			sH = 12;
			startStamp = "pm";
		}
		if (eH == 0) {
			eH = 12;
			endStamp = "pm";
		}
		if (startHour > 12) {

			startStamp = startHour == 24 ? "am" : "pm";
		}

		if (endHour > 12) {
			endStamp = endHour == 24 ? "am" : "pm";
		}

		return "" + sH + startStamp + " - " + eH + endStamp;

	}

}
