package ar.com.terminal.db;

import ar.com.terminal.db.Database;
import ar.com.terminal.model.FullProductDescription;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.ProfitPolicy;
import ar.com.terminal.model.Sale;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ivan on 06/09/15.
 */
public class DBMemory implements Database {
    private List<Product> products = new ArrayList<Product>();
    private List<ProfitPolicy> profitPolicies = new ArrayList<ProfitPolicy>();

    private static final DBMemory instance = new DBMemory();
    public static Database getInstance(){
        return instance;
    }

    @Override
    public Product getProductById(String id) {

        final Product[] product = new Product[1];

        products.forEach((Product it) -> {
            if (it.getId().equals(id)) {
                product[0] = it;
            }
        });

        return product[0];
    }

    @Override
    public void save(Sale sale) {

    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void remove(String productId) {
        Product product = getProductById(productId);
        products.remove(product);
    }

    @Override
    public FullProductDescription getFullDescription(String productId) {

        return getProductById(productId).getFullDescription();
    }

    @Override
    public void save(ProfitPolicy profitPolicy) {
        profitPolicies.add(profitPolicy);
    }

    @Override
    public List<ProfitPolicy> getProfitPolicies() {
        return profitPolicies;
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
