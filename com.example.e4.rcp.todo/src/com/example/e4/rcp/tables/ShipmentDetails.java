package com.example.e4.rcp.tables;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name="Ship_Details")
public class ShipmentDetails implements DatabaseAccess{
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int shipId;
	private Date orderDate;
	private Date expectedShipDate;
	private Date expectedRecievedDate;
	private Date actualShipDate;
	private Date actualRecievedDate;
	private float shippedAmount;
	private float recievedAmount;
	private String RecieptNumber;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="orgId", nullable = false)
	private OrgDetails orgId;
	
	/**
	 * @return the orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the expectedShipDate
	 */
	public Date getExpectedShipDate() {
		return expectedShipDate;
	}

	/**
	 * @param expectedShipDate the expectedShipDate to set
	 */
	public void setExpectedShipDate(Date expectedShipDate) {
		this.expectedShipDate = expectedShipDate;
	}

	/**
	 * @return the actualShipDate
	 */
	public Date getActualShipDate() {
		return actualShipDate;
	}

	/**
	 * @param actualShipDate the actualShipDate to set
	 */
	public void setActualShipDate(Date actualShipDate) {
		this.actualShipDate = actualShipDate;
	}

	/**
	 * @return the actualRecievedDate
	 */
	public Date getActualRecievedDate() {
		return actualRecievedDate;
	}

	/**
	 * @param actualRecievedDate the actualRecievedDate to set
	 */
	public void setActualRecievedDate(Date actualRecievedDate) {
		this.actualRecievedDate = actualRecievedDate;
	}

	/**
	 * @return the shippedAmount
	 */
	public float getShippedAmount() {
		return shippedAmount;
	}

	/**
	 * @param shippedAmount the shippedAmount to set
	 */
	public void setShippedAmount(float shippedAmount) {
		this.shippedAmount = shippedAmount;
	}

	/**
	 * @return the recievedAmount
	 */
	public float getRecievedAmount() {
		return recievedAmount;
	}

	/**
	 * @param recievedAmount the recievedAmount to set
	 */
	public void setRecievedAmount(float recievedAmount) {
		this.recievedAmount = recievedAmount;
	}

	/**
	 * @return the recieptNumber
	 */
	public String getRecieptNumber() {
		return RecieptNumber;
	}

	/**
	 * @param recieptNumber the recieptNumber to set
	 */
	public void setRecieptNumber(String recieptNumber) {
		RecieptNumber = recieptNumber;
	}

	/**
	 * @return the orgId
	 */
	public OrgDetails getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(OrgDetails orgId) {
		this.orgId = orgId;
	}

	public Date getExpectedRecievedDate() {
		return expectedRecievedDate;
	}

	public void setExpectedRecievedDate(Date expectedRecievedDate) {
		this.expectedRecievedDate = expectedRecievedDate;
	}
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return shipId;
	}
}
