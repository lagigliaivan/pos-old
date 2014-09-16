package com.terminal.db.dto;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.Map;

public class Sale {

	private Date date;
	private Map<Product, Integer> products;
	private Float totalAmount;
	
	public Sale(Date date, Map<Product, Integer> products, Float totalPrice){
		this.date = date;
		this.products = products;
		this.totalAmount = totalPrice;
		
		if( date == null ){
			throw new InvalidParameterException("Date cannot be null.");
		}
		
		if (products == null || products.isEmpty()){
			throw new InvalidParameterException("Products cannot be empty or null.");
		}
		
		if ( totalAmount == null || totalAmount < 0){
			throw new InvalidParameterException(" Total amount cannot be null or less than 0.");
		}
	}
	
	public Map<Product, Integer> getProducts(){
		return this.products;
	}

	public Date getDate() {
		return date;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}
	
}
