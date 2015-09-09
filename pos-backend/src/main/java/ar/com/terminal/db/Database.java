package ar.com.terminal.db;

import ar.com.terminal.model.FullProductDescription;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.Sale;

public interface Database {

    void save(Sale sale);
    void save(Product product);

    Product getProductById (String id);

    void remove(String productId);

    FullProductDescription getFullDescription(String productId);
}
