package com.example.e4.helperClasses;

import java.util.ArrayList;
import java.util.List;

import com.example.e4.rcp.tables.UserDetails;

public enum ModelProvider {
	INSTANCE;
	
	private List<UserDetails> persons;
	
	private ModelProvider(){
		persons = new ArrayList<UserDetails>();
		
		persons.add(new UserDetails("a","male",14));
		persons.add(new UserDetails("b", "female", 15));		
	}
	
	public List<UserDetails> getPersons(){
		return persons;
	}
}
