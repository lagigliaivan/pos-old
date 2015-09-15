package ar.com.terminal.model;

import java.security.InvalidParameterException;

public class Product {
	
	private String id;
	private Float price = 0.0F;
	private Integer stock = 0;
	private String description;
    private FullProductDescription fullDescription;
    private Float suggestedPrice = 0F;

    public FullProductDescription getFullDescription() {
        return fullDescription;
    }

    public Product(String id, Float price, String description){

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
	
	public void setStock(int stock) {
        if(stock < 0){
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        this.stock = stock;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!description.equals(product.description)) return false;
        if (!id.equals(product.id)) return false;
        if (!price.equals(product.price)) return false;
        if (!stock.equals(product.stock)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + stock.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    public void setFullDescription(FullProductDescription fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Float getSuggestedPrice() {
        return suggestedPrice;
    }

    public void setSuggestedPrice(Float suggestedPrice) {
        this.suggestedPrice = suggestedPrice;
    }
}
