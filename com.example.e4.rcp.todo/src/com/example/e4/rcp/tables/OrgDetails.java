package com.example.e4.rcp.tables;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Org_Details")
public class OrgDetails implements DatabaseAccess{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int orgId;
	private String orgName;
	private String orgAddress;
	private String orgCity;
	private String orgType;
	
	@Transient
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(String propertyName,PropertyChangeListener listener){
		propertyChangeSupport.addPropertyChangeListener(propertyName,listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		propertyChangeSupport.removePropertyChangeListener(listener);
	}
	
	public OrgDetails() {
		
	}
	
	public OrgDetails(String orgName,String orgAddress,String orgCity,String busType){
		this.orgName = orgName;
		this.orgAddress = orgAddress;
		this.orgCity = orgCity;
		this.orgType = busType;
	}
	
	@Override
	public int getId() {
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
	public String getOrgAddress() {
		return orgAddress;
	}

	/**
	 * @return the orgCity
	 */
	public String getOrgCity() {
		return orgCity;
	}

	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		System.out.println(orgName);
		propertyChangeSupport.firePropertyChange("orgName",this.orgName,this.orgName = orgName);
	}

	/**
	 * @param orgAddress the orgAddress to set
	 */
	public void setOrgAddress(String orgAddress) {
		System.out.println(orgAddress);
		propertyChangeSupport.firePropertyChange("address",this.orgAddress,this.orgAddress = orgAddress);
	}

	/**
	 * @param orgCity the orgCity to set
	 */
	public void setOrgCity(String orgCity) {
		propertyChangeSupport.firePropertyChange("city",this.orgCity,this.orgCity = orgCity);
	}

	/**
	 * @param orgType the orgType to set
	 */
	public void setOrgType(String orgType) {
		propertyChangeSupport.firePropertyChange("buisnessType",this.orgType,this.orgType = orgType);
	}
}
