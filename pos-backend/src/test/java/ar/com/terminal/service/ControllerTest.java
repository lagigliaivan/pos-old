package ar.com.terminal.service;

import ar.com.terminal.dto.ProductDto;
import ar.com.terminal.model.Catalog;
import ar.com.terminal.Controller;
import ar.com.terminal.db.DBMemory;
import ar.com.terminal.model.NullProduct;
import ar.com.terminal.db.Database;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ControllerTest {

    private Database database;
    private Controller controller;

    @Before
    public void setup(){
        database = new DBMemory();
        controller = new Controller(new Catalog(database));
    }


    @Test
    public void return_a_product_if_it_exits(){

        String id = "7798133540489";
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setPrice(10F);
        productDto.setDescription("AVE Malbec 750M ml");


        controller.addProduct(productDto);

        productDto = controller.getProduct(id);

        assertThat(productDto, is(notNullValue()));
        assertThat(productDto.getId(), is(id));

    }

    @Test
    public void return_an_empty_product_if_it_does_not_exits(){

        String id = "7798133540481";
        ProductDto productDto = controller.getProduct(id);

        assertThat(productDto.getId(), is(NullProduct.name));
        assertThat(productDto.getPrice(), is(NullProduct.price));
        assertThat(productDto.getDescription(), is(NullProduct.desc));
    }

    @Test
    public void return_a_list_of_products_when_id_is_not_passed(){

        Integer page = 1;
        List<ProductDto> productsDto = controller.getAll(page);
        assertThat(productsDto, is(notNullValue()));
    }
}
