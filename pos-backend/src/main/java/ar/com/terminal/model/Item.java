package ar.com.terminal.model;

import java.security.InvalidParameterException;

public class Item {
	
	private String id;
	private Float price;
	private Integer stock;
	private String description;
	
	public Item(String id, Float price, String description){

        this.id = id;
		this.price = price;
		this.description = description;
		
		if( id == null || id.isEmpty()){
			throw new InvalidParameterException("Product ID cannot be null or empty");
		}
		if( price == null || price < 0 ){
			throw new InvalidParameterException("Product price cannot be null or negative");
		}

		if( description == null || description.isEmpty() ){
			throw new InvalidParameterException("Product description cannot be null or empty"); 
		}
	}

	public String getId() {
		return id;
	}
	
	public Float getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getStock(){
		return this.stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
