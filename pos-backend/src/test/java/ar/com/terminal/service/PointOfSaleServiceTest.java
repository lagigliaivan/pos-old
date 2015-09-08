package ar.com.terminal.service;

import ar.com.terminal.dto.Product;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ivan on 06/09/15.
 */
public class PointOfSaleServiceTest {

    private String id;

    @Test
    public void return_a_product_when_id_is_passed(){
        id = "78798133540489";

        PointOfSaleService service = new PointOfSaleService();

        Product product = new Product();
        product.setId(id);
        product.setDescription("AVE Malbec 750Ml");
        product.setPrice(70F);

        service.addProduct(product);

        Product existingProduct = service.getProduct(id);

        assertThat(existingProduct, is(notNullValue()));
        assertThat(existingProduct, is(product));
    }
}
