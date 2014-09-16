package com.terminal.db;

import java.util.Set;

public class ProductHbm {
	
	private String idproduct;
	private String description;
	private Integer stock;
	private Float price;
	private Set <Long> sales;
	
	public ProductHbm(){}
	
	public ProductHbm(String id){
		idproduct = id;
	}
	
	public Set<Long> getsales() {
		return sales;
	}
	public void setsales(Set<Long> sales) {
		this.sales = sales;
	}
	public String getidproduct() {
		return idproduct;
	}
	public void setidproduct(String idproduct) {
		this.idproduct = idproduct;
	}
	public String getdescription() {
		return description;
	}
	public void setdescription(String description) {
		this.description = description;
	}
	public Integer getstock() {
		return stock;
	}
	public void setstock(Integer stock) {
		this.stock = stock;
	}
	public Float getprice() {
		return price;
	}
	public void setprice(Float price) {
		this.price = price;
	}
	
	
}
