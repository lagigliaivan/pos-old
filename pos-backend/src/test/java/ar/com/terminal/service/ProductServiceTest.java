package ar.com.terminal.service;

import ar.com.terminal.Controller;
import ar.com.terminal.db.DBMemory;
import ar.com.terminal.db.Database;
import ar.com.terminal.dto.ListHolder;
import ar.com.terminal.dto.ProductDto;
import ar.com.terminal.dto.ProductDescriptionDto;
import ar.com.terminal.dto.ProfitPolicyDto;
import ar.com.terminal.model.Catalog;
import ar.com.terminal.model.NullProduct;
import org.hamcrest.Matchers;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ivan on 06/09/15.
 */
public class ProductServiceTest {

    private String id;

    @Test
    public void return_a_list_of_products(){

        ProductDto product1 = getProduct("7798130150018", 100F, "Dominicano de barrancas");
        ProductDto product2 = getProduct("7798130234018", 95.44F, "AVE Malbec 2011");
        ProductDto product3 = getProduct("3123130152018", 135F, "Blend");

        ProductService service = getProductService(new DBMemory());
        service.addProduct(product1);
        service.addProduct(product2);
        service.addProduct(product3);


        List<ProductDto> productsDto = service.listProducts(0);

        assertThat(productsDto, is(Matchers.notNullValue()));
        assertThat(productsDto.isEmpty(), is(false));
        assertThat(productsDto.size(), is(3));

        assertThat(productsDto.contains(product1), is(true));
        assertThat(productsDto.contains(product2), is(true));
        assertThat(productsDto.contains(product3), is(true));

    }

    @Test
    public void return_a_product_when_id_is_passed_and_product_exists(){

        id = "78798133540489";


        ProductDto productDto = getProduct(id, 70F, "AVE Malbec 750Ml");

        ProductService service = getProductService(new DBMemory());
        service.addProduct(productDto);

        ProductDto existingProductDto = service.getProduct(id, false);

        assertThat(existingProductDto, is(notNullValue()));
        assertThat(existingProductDto, is(productDto));
    }

    @Test
    public void return_an_empty_product_when_id_is_passed_and_product_does_not_exist(){
        id = "78798133540489";

        ProductService service = getProductService(new DBMemory());

        ProductDto existingProductDto = service.getProduct(id, false);

        assertThat(existingProductDto, is(notNullValue()));
        assertThat(existingProductDto.getId(), is(NullProduct.name));
        assertThat(existingProductDto.getDescription(), is(NullProduct.desc));
        assertThat(existingProductDto.getPrice(), is(NullProduct.price));
    }

    @Test
    public void return_full_info_from_a_product() throws MalformedURLException {

        id = "78798133540489";


        String fullDescription = "10 meses en barricas de roble frances de primer uso y, al menos 8 meses en botella." +
                "Es el primer balbec con estilo, sabor y caracter italiano. Rojo intenso, sabor frutado";
        URL pictureURL = new URL("http://localhost:8080/pos/product/" + id + "/info/image.jpg");

        ProductDescriptionDto description = new ProductDescriptionDto();
        description.setFullDescription(fullDescription);
        description.setPictureURL(pictureURL);

        ProductDto productDto = getProduct(id, 70F, "AVE Malbec 750Ml");
        productDto.setFullDescription(description);

        ProductService service = getProductService(new DBMemory());
        service.addProduct(productDto);

        ProductDto fullProductDto = service.getProduct(id, true);

        assertThat(fullProductDto, is(notNullValue()));


        ProductDescriptionDto productDescriptionDto = fullProductDto.getFullDescription();

        assertThat(productDescriptionDto.getFullDescription(), is(fullDescription));
        assertThat(productDescriptionDto.getPictureURL(), is(pictureURL));
    }
    @Test
    public void associate_product_to_a_policy(){

        ProfitPolicyDto policyDto = getProfitPolicyDto("Default Policy 5%", 5F);
        ProductDto product = getProduct("12334345454", 55F, "Bonarda Sin Palabras");

        Database database = new DBMemory();
        PolicyService service = getPolicyService(database);
        service.addProfitPolicy(policyDto);

        ProductService productService = getProductService(database);
        productService.addProduct(product);

        List<String> products = new ArrayList<>();
        products.add(product.getId());

        ListHolder listHolder = new ListHolder();
        listHolder.setList(products);

        Response response = service.addProductToPolicy(policyDto.getId(),listHolder);
        assertThat(response.getStatus(), is(201));

        List<ProfitPolicyDto> policies = productService.getPoliciesByProduct(product.getId());
        assertThat(policies.isEmpty(), is(false));
    }
    @Test
    public void get_policies_that_apply_to_a_product(){

    }

    private ProductService getProductService(Database database) {
        Catalog catalog = new Catalog(database);
        Controller controller = new Controller(catalog);
        return new ProductService(controller);
    }

    private ProductDto getProduct(String id, Float price, String desc) {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setDescription(desc);
        productDto.setPrice(price);
        return productDto;
    }
    private ProfitPolicyDto getProfitPolicyDto(String id, Float percentage) {
        ProfitPolicyDto profitPolicyDto;
        profitPolicyDto = new ProfitPolicyDto();
        profitPolicyDto.setId(id);
        profitPolicyDto.setPercentage(21.5F);
        return profitPolicyDto;
    }
    private PolicyService getPolicyService(Database database) {
        Catalog catalog = new Catalog(database);
        Controller controller = new Controller(catalog);
        return new PolicyService(controller);
    }
}
