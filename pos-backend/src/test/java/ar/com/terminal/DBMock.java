package ar.com.terminal;

import ar.com.terminal.db.Database;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.Sale;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ivan on 06/09/15.
 */
public class DBMock implements Database {
    private Map<Product, Integer> items = new HashMap<>();

    @Override
    public Product getProductById(String id) {
        final Product[] product = new Product[1];

        items.forEach((Product it, Integer quantity) -> {if(it.getId().equals(id)) {
            product[0] = it;} });

        return product[0];
    }

    @Override
    public void addProduct(Product product, Integer amount) {
        items.put(product,amount);
    }

    @Override
    public Integer getStock(String id) {
        return null;
    }

    @Override
    public void save(Sale sale) {

    }

    @Override
    public void save(Product product) {

    }

    @Override
    public void remove(Product product) {
        items.remove(product);
    }
}
