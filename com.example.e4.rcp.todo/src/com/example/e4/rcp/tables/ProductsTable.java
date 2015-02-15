package com.example.e4.rcp.tables;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Product_List")
public class ProductsTable implements DatabaseAccess{
	
	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	private long productId;
	private String productName;
	private String productDescription;
	private String parentProduct;
	
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * 
	 * @return the product Name
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * 
	 * @param productName
	 * 			The name of the product that organization qualifies for
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * 
	 * @return the description about the products
	 */
	public String getProductDescription() {
		return productDescription;
	}
	
	/**
	 * 
	 * @param productDescription
	 * 			the description about the product
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getParentProduct() {
		return parentProduct;
	}

	public void setParentProduct(String parentProduct) {
		this.parentProduct = parentProduct;
	}

}
