package com.example.e4.rcp.tables;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Org_Details")
public class OrgDetails implements DatabaseAccess{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int orgId;
	private String orgName;
	@OneToOne
	private Address orgAddress;
	private String busType;
	private String tin;
	private String transactionType;
	
	@OneToMany
	private Collection<ContactDetails> contacts = new ArrayList<ContactDetails>();
	@Override
	public long getId() {
		
		return orgId;
	}
	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @return the orgAddress
	 */
	public Address getOrgAddress() {
		return orgAddress;
	}
	/**
	 * @return the busType
	 */
	public String getBusType() {
		return busType;
	}
	/**
	 * @return the tin
	 */
	public String getTin() {
		return tin;
	}
	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}
	/**
	 * @return the contacts
	 */
	public Collection<ContactDetails> getContacts() {
		return contacts;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(Collection<ContactDetails> contacts) {
		this.contacts = contacts;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @param orgAddress the orgAddress to set
	 */
	public void setOrgAddress(Address orgAddress) {
		this.orgAddress = orgAddress;
	}
	/**
	 * @param busType the busType to set
	 */
	public void setBusType(String busType) {
		this.busType = busType;
	}
	/**
	 * @param tin the tin to set
	 */
	public void setTin(String tin) {
		this.tin = tin;
	}
	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
}
