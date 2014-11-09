package com.example.r4.rcp.Organization.parts;

import java.util.Date;


	public class Person {
		private int id;
		private String name;
		private Date birthDay;

		public Person(int id, String name, Date birthDate) {
			this.id = id; 
			this.name = name;    
			this.birthDay = birthDate;
		}

		public int getId() {return id;}
		public String getName() {return name;}
		public Date getBirthDay() {return birthDay;}
	}

