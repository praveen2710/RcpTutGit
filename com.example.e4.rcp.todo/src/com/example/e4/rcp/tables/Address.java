package com.example.e4.rcp.tables;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Address {

	private String street;
	private String number;
	private String postalCode; //required
	private String city; //required
	private String country; //required
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public Address(){	
	}
	
	public Address(String postalCode,String city,String country){
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;
	}
	
	public void addPropertyChangeListener(String propertyName,PropertyChangeListener listener){
		propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		propertyChangeSupport.firePropertyChange("street",this.street,this.street = street);
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		propertyChangeSupport.firePropertyChange("number",this.number ,this.number = number);
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		propertyChangeSupport.firePropertyChange("Postal Code",this.postalCode,this.postalCode = postalCode);
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		propertyChangeSupport.firePropertyChange("City",this.city,this.city = city);
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		propertyChangeSupport.firePropertyChange("Country",this.country,this.country = country);
	}

	@Override
	public String toString(){
		String s="";
		s+= street !=null?street+" ":"";
		s+= number !=null?number+" ":"";
		s+= postalCode !=null?postalCode+" ":"";
		s+= city !=null?city+" ":"";
		s+= country !=null?country+" ":"";
		
		return s;
	}
}
