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
import javax.persistence.FetchType;
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
public class   OrgDetails implements DatabaseAccess{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long orgId;
	private String orgName;
	@OneToOne
	private Address orgAddress;
	@OneToMany(fetch = FetchType.EAGER)
	private  Collection<ProductsTable> busType = new ArrayList<ProductsTable>();;
	private String tin;
	private String transactionType;
	private String primaryPerson;
	private String primaryNumber;
	private float currentBalance;
	
	@OneToMany(fetch = FetchType.EAGER)
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
	public Collection<ProductsTable> getBusType() {
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
	 * @return the primaryPerson
	 */
	public String getPrimaryPerson() {
		return primaryPerson;
	}
	/**
	 * @return the primaryNumber
	 */
	public String getPrimaryNumber() {
		return primaryNumber;
	}
	/**
	 * @return the currentBalance
	 */
	public float getCurrentBalance() {
		return currentBalance;
	}
	/**
	 * @param currentBalance the currentBalance to set
	 */
	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}
	/**
	 * @param primaryNumber the primaryNumber to set
	 */
	public void setPrimaryNumber(String primaryNumber) {
		this.primaryNumber = primaryNumber;
	}
	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(Collection<ContactDetails> contacts) {
		this.contacts = contacts;
	}
	/**
	 * @param primaryPerson the primaryPerson to set
	 */
	public void setPrimaryPerson(String primaryPerson) {
		this.primaryPerson = primaryPerson;
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
	public void setBusType(Collection<ProductsTable> busType) {
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
