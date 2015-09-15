package ar.com.terminal;


import ar.com.terminal.db.DBMemory;
import ar.com.terminal.db.Database;
import ar.com.terminal.model.Catalog;
import ar.com.terminal.model.NullProduct;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.ProfitPolicy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class CatalogTest {

    private Database database;
    private Catalog catalog;
    private String id;

    @Before
    public void setup(){
        database = new DBMemory();
        catalog = new Catalog(database);
    }

    @Test
    public void add_a_product_to_the_catalog(){

        Product product = new Product("id",0F,"item for testing");
        catalog.addProduct(product);

        Product productInCatalog = catalog.getProduct(product.getId());

        assertThat(productInCatalog, is(notNullValue()));
        assertThat(productInCatalog, is(product));
    }

    @Test
    public void remove_a_product_from_the_catalog(){

        Product product = new Product("id",0F,"item for testing");
        catalog.addProduct(product);

        Product productInCatalog = catalog.getProduct(product.getId());

        assertThat(productInCatalog, is(notNullValue()));
        assertThat(productInCatalog.getId(), is(product.getId()));

        catalog.remove(product.getId());

        productInCatalog = catalog.getProduct(product.getId());

        assertThat(productInCatalog, is(notNullValue()));
        assertThat(productInCatalog, is(new NullProduct()));

    }

    @Test
    public void return_a_product_by_its_id(){

        Product product = new Product("id123",0F,"item for testing");
        catalog.addProduct(product);

        Product productInCatalog = catalog.getProduct("id123");

        assertThat(productInCatalog, is(notNullValue()));
        assertThat(productInCatalog.getId(), is(product.getId()));
    }

    @Test
    public void return_a_empty_product_when_it_does_not_exist(){

        Product product = catalog.getProduct("item01");

        assertThat(product, is(notNullValue()));
        assertThat(product.getId(), is(NullProduct.name));
        assertThat(product.getPrice(), is(NullProduct.price));
        assertThat(product.getDescription(), is(NullProduct.desc));
    }

    @Test
    public void return_the_stock_of_a_product(){

        Product product = new Product("1",0.0F, "product1");
        catalog.addProduct(product);
        catalog.incrementStock(product.getId(), 3);
        Integer quantity = catalog.getStock(product.getId());

        assertThat(quantity, is(3));
    }

    @Test
    public void return_all_the_policies(){
       ProfitPolicy policy = new ProfitPolicy("Default policy 5%", 5f);
       ProfitPolicy policy1 = new ProfitPolicy("Policy 10%", 10f);
       ProfitPolicy policy2 = new ProfitPolicy("Policy 21%", 21f);

       catalog.addProfitPolicy(policy);
       catalog.addProfitPolicy(policy1);
       catalog.addProfitPolicy(policy2);

       assertThat(catalog.getProfitPolicies(), is(notNullValue()));
       assertThat(catalog.getProfitPolicies().isEmpty(), is(false));
       assertThat(catalog.getProfitPolicies().size(), is(3));

       assertThat(catalog.getProfitPolicies().contains(policy), is(true));
       assertThat(catalog.getProfitPolicies().contains(policy1), is(true));
       assertThat(catalog.getProfitPolicies().contains(policy2), is(true));
    }

    @Test
    public void return_the_suggested_price_of_a_product(){

        id = "7798130150018";

        Product product = new Product(id,85.50F,"Dominicano de Barrancas Malbec");
        ProfitPolicy policy = new ProfitPolicy("Default policy", 5f);
        product.setSuggestedPrice( ((product.getPrice()*policy.getPercentage())/100) + product.getPrice() );

        catalog.addProduct(product);
        catalog.addProfitPolicy(policy);

        List<String> products = new ArrayList<>(1);
        products.add(product.getId());

        catalog.addProductToPolicy(policy.getId(), products);

        Product productAndPrice = catalog.getProduct(product.getId());

        assertThat(productAndPrice, is(notNullValue()));
        assertThat(productAndPrice.getPrice(), is(product.getPrice()));
        assertThat(productAndPrice.getSuggestedPrice(), is(product.getSuggestedPrice()));

    }
}
