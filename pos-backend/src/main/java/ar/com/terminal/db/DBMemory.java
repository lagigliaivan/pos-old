package ar.com.terminal.db;

import ar.com.terminal.db.Database;
import ar.com.terminal.model.FullProductDescription;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.ProfitPolicy;
import ar.com.terminal.model.Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ivan on 06/09/15.
 */
public class DBMemory implements Database {

    private Map<String, Product> products = new HashMap<>();
    private Map<String, ProfitPolicy> profitPolicies = new HashMap<>();
    private Map<String, String> productPolicyAssoc = new HashMap<>();

    private static final DBMemory instance = new DBMemory();
    public static Database getInstance(){
        return instance;
    }

    @Override
    public Product getProductById(String id) {

        return products.get(id);
    }

    private Product cloneProduct(Product product) {
        Product productToReturn = new Product(product.getId(), product.getPrice(), product.getDescription());
        productToReturn.setFullDescription(product.getFullDescription());
        productToReturn.setStock(product.getStock());
        productToReturn.setSuggestedPrice(product.getSuggestedPrice());
        return productToReturn;
    }

    @Override
    public void save(Sale sale) {

    }

    @Override
    public void save(final Product product) {
        if (product != null) {
            Product cloneProduct = cloneProduct(product);
            products.put(cloneProduct.getId(), cloneProduct);

        }
    }
    @Override
    public void remove(String productId) {
        products.remove(productId);
    }

    @Override
    public FullProductDescription getFullDescription(String productId) {

        return getProductById(productId).getFullDescription();
    }

    @Override
    public void save(ProfitPolicy profitPolicy) {
        profitPolicies.put(profitPolicy.getId(), profitPolicy);
    }

    @Override
    public List<ProfitPolicy> getProfitPolicies() {
        return new ArrayList<>(profitPolicies.values());
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(String policyId, String productId) {
        productPolicyAssoc.put(productId, policyId);
    }

    @Override
    public ProfitPolicy getProfitPolicyByProduct(String productId) {
        String profitPolicyId = productPolicyAssoc.get(productId);
        return profitPolicies.get(profitPolicyId);
    }
}
