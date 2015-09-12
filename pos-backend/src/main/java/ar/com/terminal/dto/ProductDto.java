package ar.com.terminal.dto;

/**
 * Created by ivan on 05/09/15.
 */
public class ProductDto {

    private String id;
    private String description;
    private Float price;
    private ProductDescriptionDto productDescriptionDto;

    public ProductDto(){

    }

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

    public ProductDescriptionDto getFullDescription() {
        return productDescriptionDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDto productDto = (ProductDto) o;

        if (!description.equals(productDto.description)) return false;
        if (!id.equals(productDto.id)) return false;
        if (!price.equals(productDto.price)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    public void setFullDescription(ProductDescriptionDto fullProductDescriptionDto) {
        productDescriptionDto = fullProductDescriptionDto;
    }
}