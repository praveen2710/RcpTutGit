package com.example.e4.rcp.tables;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="User_Details")
public class UserDetails /*implements PropertyChangeListener */{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	private String userName;
	private String gender;
	private int age;
//	private boolean married;
//	private Address address;
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public void person(){
	}
	
	public void addPropertyChangeListener(String propertyName,PropertyChangeListener listener){
		propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public int getAge() {
		return age;
	}

	public void setUserName(String userName) {
		propertyChangeSupport.firePropertyChange("userName",this.userName,this.userName = userName);
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		propertyChangeSupport.firePropertyChange("gender",this.gender,this.gender = gender);
	}
	
	public void setAge(int age) {
		propertyChangeSupport.firePropertyChange("age",this.age,this.age = age);
	}
	
//	/**
//	 * @return the married
//	 */
//	public boolean isMarried() {
//		return married;
//	}
//
//	/**
//	 * @param married the married to set
//	 */
//	public void setMarried(boolean martialStatus) {
//		propertyChangeSupport.firePropertyChange("Mstatus",this.married,this.married = martialStatus);
//	}

	
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		address.addPropertyChangeListener("country", this);
//		propertyChangeSupport.firePropertyChange("address", this.address,this.address = address);
//		this.address = address;
//	}
//
//	@Override
//	public void propertyChange(PropertyChangeEvent event) {
//		propertyChangeSupport.firePropertyChange("address",null,address);
//		
//	}
	
	@Override
	public String toString(){
		return userName;
	}
}

