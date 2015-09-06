package ar.com.terminal;


import ar.com.terminal.db.Database;
import ar.com.terminal.model.Item;
import ar.com.terminal.model.Sale;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CatalogTest {

    private Database database;
    private Catalog catalog;

    @Before
    public void setup(){

        database = new Database() {
            private Map<Item, Integer> items = new HashMap<>();

            @Override
            public Item getProductById(String id) {
                final Item[] item = new Item[1];

                items.forEach((Item it, Integer quantity) -> {if(it.getId().equals(id)) {
                    item[0] = it;} });

                return item[0];
            }

            @Override
            public void addProduct(Item item, Integer amount) {
                items.put(item,amount);
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

            @Override
            public void remove(Item item) {
                items.remove(item);
            }
        };

        catalog = new Catalog(database);

    }

    @Test
    public void add_an_item_to_the_catalog(){

        Item item = new Item("id",0F,"item for testing");
        catalog.addItem(item);

        Item itemInCatalog = catalog.getItem(item.getId());

        assertThat(itemInCatalog, is(notNullValue()));
        assertThat(itemInCatalog.getId(), is(item.getId()));
    }

    @Test
    public void remove_an_item_from_the_catalog(){

        Item item = new Item("id",0F,"item for testing");
        catalog.addItem(item);

        Item itemInCatalog = catalog.getItem(item.getId());

        assertThat(itemInCatalog, is(notNullValue()));
        assertThat(itemInCatalog.getId(), is(item.getId()));

        catalog.remove(item);

        itemInCatalog = catalog.getItem(item.getId());

        assertThat(itemInCatalog, is(notNullValue()));
        assertThat(itemInCatalog, is(new NullItem()));

    }

    @Test
    public void return_an_item_by_its_id(){

        Item item = new Item("id123",0F,"item for testing");
        database.addProduct(item,1);

        Item itemInCatalog = catalog.getItem("id123");

        assertThat(itemInCatalog, is(notNullValue()));
        assertThat(itemInCatalog.getId(), is(item.getId()));
    }

    @Test
    public void return_an_empty_item_when_it_does_not_exist(){

        Item item = catalog.getItem("item01");

        assertThat(item, is(notNullValue()));
        assertThat(item.getId(), is("Item not found"));
    }


}
