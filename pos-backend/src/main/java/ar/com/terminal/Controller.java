package ar.com.terminal;

import ar.com.terminal.db.DBConnection;
import ar.com.terminal.db.Database;
import ar.com.terminal.dto.Product;
import ar.com.terminal.dto.ProductDescription;
import ar.com.terminal.model.Catalog;
import ar.com.terminal.model.FullProductDescription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 05/09/15.
 */
public class Controller {

    Catalog catalog;

    public Controller(){
        Database dbConnection = DBConnection.getInstance();
        catalog = new Catalog(dbConnection);
    }

    public Controller(Catalog catalog){
        this.catalog = catalog;
    }

    public Product getProduct(String id) {

        ar.com.terminal.model.Product product = catalog.getProduct(id);

        Product productDto = new Product();
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());

        return productDto;
    }

    public void addProduct(Product product) {

        ar.com.terminal.model.Product newProduct = new ar.com.terminal.model.Product(product.getId(), product.getPrice(), product.getDescription());
        ProductDescription productDescription = product.getFullDescription();

        if(productDescription != null){
            FullProductDescription fullProductDescription = new FullProductDescription();
            fullProductDescription.setFullDescription(productDescription.getFullDescription());
            fullProductDescription.setPictureURL(productDescription.getPictureURL());

            newProduct.setFullDescription(fullProductDescription);
        }


        catalog.addProduct(newProduct);
    }

    public Product getProductWithFullInformation(String productId){

        Product product = getProduct(productId);
        ProductDescription fullProductDescription = getFullProductDescription(productId);
        product.setFullDescription(fullProductDescription);

        return product;
    }

    private ProductDescription getFullProductDescription(String productId) {

        FullProductDescription fullProductDescription = catalog.getFullDescription(productId);
        ProductDescription productDescriptionDto = new ProductDescription();

        productDescriptionDto.setFullDescription(fullProductDescription.getFullDescription());
        productDescriptionDto.setPictureURL(fullProductDescription.getPictureURL());

        return productDescriptionDto;
    }

    public List<Product> getAll(Integer page) {
        return new ArrayList<Product>();
    }
}
