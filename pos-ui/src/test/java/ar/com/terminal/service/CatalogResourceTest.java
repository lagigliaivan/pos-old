package ar.com.terminal.service;


import ar.com.terminal.db.dto.Item;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CatalogResourceTest {

    @Test
    public void xxx(){
        CatalogResource catalog = new CatalogResource();
        Item item = catalog.getItem("id123");
        assertThat(item, is(notNullValue()));
        assertThat(item.getId(), is("id123"));
    }
}
