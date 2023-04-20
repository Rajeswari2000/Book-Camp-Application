package com.rajeswari2000.kumaranbookworld.dto;

import java.sql.Date;
import java.sql.Time;

public class Schedule {

	int scheduleId;
	Date date;
	String address;
	String city;
	Time starTime;
	Time endTime;

	public Schedule(int scheduleId, Date date, String address, String city, Time starTime, Time endTime) {
		super();
		this.scheduleId = scheduleId;
		this.date = date;
		this.address = address;
		this.city = city;
		this.starTime = starTime;
		this.endTime = endTime;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public Date getDate() {
		return date;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public Time getStarTime() {
		return starTime;
	}

	public Time getEndTime() {
		return endTime;
	}

}
