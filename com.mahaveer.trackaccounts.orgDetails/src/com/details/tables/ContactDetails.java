package com.details.tables;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Contacts")
public class ContactDetails implements DatabaseAccess{
	
	private String personName;
	@Id
	private long contactNumber;
	
	/**
	 * @return the personName
	 */
	public String getPersonName() {
		return personName;
	}
	/**
	 * @return the contactNumber
	 */
	public long getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param personName the personName to set
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	/**
	 * @param d the contactNumber to set
	 */
	public void setContactNumber(long d) {
		this.contactNumber = d;
	}
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return contactNumber;
	}
	
}
