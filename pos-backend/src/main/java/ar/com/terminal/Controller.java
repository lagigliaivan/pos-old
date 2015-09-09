package ar.com.terminal;

import ar.com.terminal.db.DBConnection;
import ar.com.terminal.db.Database;
import ar.com.terminal.dto.Product;
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

        catalog.addProduct(newProduct);
    }

    public FullProductDescription getFullProductInformation(String productId){

        catalog.getFullDescription(productId);
        return new FullProductDescription();
    }

    public List<Product> getAll(Integer page) {
        return new ArrayList<>();
    }
}
