package com.rajeswari2000.kumaranbookworld.dto;

import java.util.HashMap;
import java.util.List;

public class MyProfile {

	List<Schedule> userScheduleList;

	HashMap<String, BookReview> hashMap = new HashMap<>();

	public MyProfile(List<Schedule> userScheduleList, HashMap<String, BookReview> hashMap) {
		super();
		this.userScheduleList = userScheduleList;
		this.hashMap = hashMap;
	}

	public List<Schedule> getUserScheduleList() {
		return userScheduleList;
	}

	public HashMap<String, BookReview> getHashMap() {
		return hashMap;
	}

}