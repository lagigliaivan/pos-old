package ar.com.terminal;

import ar.com.terminal.db.Database;
import ar.com.terminal.model.FullProductDescription;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ivan on 06/09/15.
 */
public class DBMock implements Database {
    private List<Product> items = new ArrayList<Product>();

    @Override
    public Product getProductById(String id) {

        final Product[] product = new Product[1];

        items.forEach((Product it) -> {if(it.getId().equals(id)) {
            product[0] = it;} });

        return product[0];
    }

    @Override
    public void save(Sale sale) {

    }

    @Override
    public void save(Product product) {
        items.add(product);
    }

    @Override
    public void remove(String productId) {
        Product product = getProductById(productId);
        items.remove(product);
    }

    @Override
    public FullProductDescription getFullDescription(String productId) {

        return getProductById(productId).getFullDescription();
    }
}
