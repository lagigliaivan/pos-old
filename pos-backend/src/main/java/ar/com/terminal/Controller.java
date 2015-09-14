package ar.com.terminal;

import ar.com.terminal.db.DBConnection;
import ar.com.terminal.db.Database;
import ar.com.terminal.dto.ProductDescriptionDto;
import ar.com.terminal.dto.ProductDto;
import ar.com.terminal.dto.ProfitPolicyDto;
import ar.com.terminal.model.Catalog;
import ar.com.terminal.model.FullProductDescription;
import ar.com.terminal.model.Product;
import ar.com.terminal.model.ProfitPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ivan on 05/09/15.
 */
public class Controller {

    Catalog catalog;

    public Controller(){
        Database dbConnection = DBConnection.getInstance();
        catalog = new Catalog(dbConnection);
    }

    public Controller(Catalog catalog){
        this.catalog = catalog;
    }

    public ProductDto getProduct(String id) {

        Product product = catalog.getProduct(id);

        ProductDto productDtoDto = getProductDto(product);

        return productDtoDto;
    }

    public void addProduct(ProductDto productDto) {

        Product newProduct = new Product(productDto.getId(), productDto.getPrice(), productDto.getDescription());
        ProductDescriptionDto productDescriptionDto = productDto.getFullDescription();

        if(productDescriptionDto != null){
            FullProductDescription fullProductDescription = new FullProductDescription();
            fullProductDescription.setFullDescription(productDescriptionDto.getFullDescription());
            fullProductDescription.setPictureURL(productDescriptionDto.getPictureURL());

            newProduct.setFullDescription(fullProductDescription);
        }

        catalog.addProduct(newProduct);
    }

    public ProductDto getProductWithFullInformation(String productId){

        ProductDto productDto = getProduct(productId);
        ProductDescriptionDto fullProductDescriptionDto = getFullProductDescription(productId);
        productDto.setFullDescription(fullProductDescriptionDto);

        return productDto;
    }

    public List<ProductDto> getAll(Integer page) {
        List<Product> products = catalog.getProducts();

        List<ProductDto> productsDto = products.stream().map(this::getProductDto).collect(Collectors.toList());

        return productsDto;
    }

    public void addProfitPolicy(ProfitPolicyDto profitPolicyDto) {
        ProfitPolicy policy = getProfitPolicy(profitPolicyDto);
        catalog.addProfitPolicy(policy);
    }

    public List<ProfitPolicyDto> getProfitPolicies() {

        List<ProfitPolicyDto> profitPoliciesDto = new ArrayList<>();
        List<ProfitPolicy> policies = catalog.getProfitPolicies();


        for(ProfitPolicy policy : policies){
            profitPoliciesDto.add(getProfitPolicyDto(policy));

        }

        return profitPoliciesDto;
    }

    public void addProductToPolicy(String policyId, List<String> productsId) {
        catalog.addProductToPolicy(policyId, productsId);
    }

    private ProfitPolicyDto getProfitPolicyDto(ProfitPolicy policy) {

        ProfitPolicyDto policyDto = new ProfitPolicyDto();

        policyDto.setPercentage(policy.getPercentage());
        policyDto.setId(policy.getId());

        return policyDto;

    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDtoDto = new ProductDto();
        productDtoDto.setId(product.getId());
        productDtoDto.setPrice(product.getPrice());
        productDtoDto.setDescription(product.getDescription());
        return productDtoDto;
    }

    private ProfitPolicy getProfitPolicy(ProfitPolicyDto profitPolicyDto) {
        ProfitPolicy policy = new ProfitPolicy(profitPolicyDto.getId());
        policy.setPercentage(profitPolicyDto.getPercentage());
        return policy;
    }

    private ProductDescriptionDto getFullProductDescription(String productId) {

        FullProductDescription fullProductDescription = catalog.getFullDescription(productId);
        ProductDescriptionDto productDescriptionDtoDto = new ProductDescriptionDto();

        productDescriptionDtoDto.setFullDescription(fullProductDescription.getFullDescription());
        productDescriptionDtoDto.setPictureURL(fullProductDescription.getPictureURL());

        return productDescriptionDtoDto;
    }

    public ProfitPolicyDto getProfitPolicyByProduct(String productId) {
        ProfitPolicy policy = catalog.getProfitPolicyByProduct(productId);
        return getProfitPolicyDto(policy);
    }
}
