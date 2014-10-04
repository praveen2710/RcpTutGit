package com.example.e4.helperClasses;

public enum Gender implements PersistentEnum{
	MALE(0),
	FEMALE(1);
	
	private final int id;
	
	Gender(int id){
		this.id = id;
	}
	
	@Override
	public int getId() {
		return id;
	}

}
