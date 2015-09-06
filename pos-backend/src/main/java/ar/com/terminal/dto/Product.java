package ar.com.terminal.dto;

/**
 * Created by ivan on 05/09/15.
 */
public class Product {

    private String id;
    private String description;
    private Float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Product(){

    }
}
