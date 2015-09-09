package ar.com.terminal;


import ar.com.terminal.db.Database;
import ar.com.terminal.model.Catalog;
import ar.com.terminal.model.NullProduct;
import ar.com.terminal.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;

public class CatalogTest {

    private Database database;
    private Catalog catalog;

    @Before
    public void setup(){
        database = new DBMock();
        catalog = new Catalog(database);
    }

    @Test
    public void add_an_item_to_the_catalog(){

        Product product = new Product("id",0F,"item for testing");
        catalog.addProduct(product);

        Product productInCatalog = catalog.getProduct(product.getId());

        assertThat(productInCatalog, is(notNullValue()));
        assertThat(productInCatalog, is(product));
    }

    @Test
    public void remove_an_item_from_the_catalog(){

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
    public void return_an_item_by_its_id(){

        Product product = new Product("id123",0F,"item for testing");
        catalog.addProduct(product);

        Product productInCatalog = catalog.getProduct("id123");

        assertThat(productInCatalog, is(notNullValue()));
        assertThat(productInCatalog.getId(), is(product.getId()));
    }

    @Test
    public void return_an_empty_item_when_it_does_not_exist(){

        Product product = catalog.getProduct("item01");

        assertThat(product, is(notNullValue()));
        assertThat(product.getId(), is(NullProduct.name));
        assertThat(product.getPrice(), is(NullProduct.price));
        assertThat(product.getDescription(), is(NullProduct.desc));
    }

    @Test
    public void return_the_stock_of_a_product(){

        Database database = new DBMock();
        Catalog catalog = new Catalog(database);
        Product product = new Product("1",0.0F, "product1");
        catalog.addProduct(product);
        catalog.incrementStock(product.getId(), 3);
        Integer quantity = catalog.getStock(product.getId());

        assertThat(quantity, is(3));
    }
}
