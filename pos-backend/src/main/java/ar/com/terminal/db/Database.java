package ar.com.terminal.db;

import ar.com.terminal.model.FullProductDescription;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.ProfitPolicy;
import ar.com.terminal.model.Sale;

import java.util.List;

public interface Database {

    void save(Sale sale);
    void save(Product product);

    Product getProductById (String id);

    void remove(String productId);

    FullProductDescription getFullDescription(String productId);

    void save(ProfitPolicy profitPolicy);

    List<ProfitPolicy> getProfitPolicies();

    List<Product> getProducts();
}
