package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.e4.rcp.tables.UserDetails;

public class sampleData {

	public static List<UserDetails> sampleSet(){
		List<UserDetails> set = new ArrayList<UserDetails>();
		UserDetails u1 = new UserDetails();
		u1.setUserId(12);
		u1.setUserName("Sample");
		u1.setGender("Male");
		set.add(u1);
		u1 = new UserDetails();
		u1.setUserId(11);
		u1.setUserName("Sample2");
		u1.setGender("FeMale");
		set.add(u1);
		return set;
	}
	
	
}
