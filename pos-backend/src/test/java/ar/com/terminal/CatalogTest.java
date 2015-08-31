package ar.com.terminal;


import ar.com.terminal.db.Database;
import ar.com.terminal.db.dto.Item;
import ar.com.terminal.db.dto.Sale;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CatalogTest {

    private Database database;
    private Catalog catalog;

    @Before
    public void setup(){

        database = new Database() {
            @Override
            public Item getProductById(String id) {
                return null;
            }

            @Override
            public void addProduct(Item item, Integer amount) {

            }

            @Override
            public Integer getStock(String id) {
                return null;
            }

            @Override
            public void save(Sale sale) {

            }

            @Override
            public void save(Item item) {

            }
        };

        catalog = new Catalog(database);

    }

    @Test
    public void return_an_item_by_its_id(){

        Item item = catalog.getItem("id123");
        assertThat(item, is(notNullValue()));
        assertThat(item.getId(), is("id123"));
    }

    @Test
    public void return_an_error_when_item_does_not_exist(){


        Item item = catalog.getItem("item01");

        assertThat(item, is(notNullValue()));
        assertThat(item.getId(), is("Item not found"));
    }

}
