package ar.com.terminal.model;

import ar.com.terminal.db.Database;
import ar.com.terminal.dto.ProfitPolicyDto;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
	
	private Database database;

    public Catalog(Database database){
		this.database = database;
	}

	public Product getProduct(String id){

        Product product = database.getProductById(id);
        if(product == null){
            product = new NullProduct();
        }
        ProfitPolicy policy = database.getProfitPolicyByProduct(product.getId());

        if(policy != null) {
            Float suggestedPrice = calculateSuggestedPrice(policy.getPercentage(), product.getPrice());
            product.setSuggestedPrice(suggestedPrice);
        }

        return product;
	}

    private Float calculateSuggestedPrice(final Float percentage, final Float price) {
        Float increment = ((percentage * price)/100f) + price;
        return increment;
    }

    public void addProduct(final Product product){
        database.save(product);
    }
	
	public void incrementStock(String productId, Integer amount){

        Product product = database.getProductById(productId);

        if(product != null){
            Integer stock = product.getStock();
            stock = stock + amount;
            product.setStock(stock);
        }

        database.save(product);

	}

	public Integer getStock(String productId){
        Product product = database.getProductById(productId);
		return product.getStock();
	}

    public void remove(String productId) {
        database.remove(productId);
    }

    public FullProductDescription getFullDescription(String productId) {
        return database.getFullDescription(productId);
    }

    public void addProfitPolicy(ProfitPolicy profitPolicy) {
        database.save(profitPolicy);
    }

    public List<ProfitPolicy> getProfitPolicies() {
         List<ProfitPolicy> profitPolicies = database.getProfitPolicies();

        return profitPolicies;
    }

    public List<Product> getProducts() {
        return database.getProducts();
    }

    public void addProductToPolicy(String policyId, List<String> productsId) {
        productsId.forEach((productId) -> {database.save(policyId, productId);});

    }

    public ProfitPolicy getProfitPolicyByProduct(String productId) {
        return database.getProfitPolicyByProduct(productId);
    }
}

