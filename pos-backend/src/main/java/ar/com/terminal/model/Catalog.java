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
		return product;
	}

	public void addProduct(Product product){
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
        return;
    }

    public FullProductDescription getFullDescription(String productId) {
        return database.getFullDescription(productId);
    }

    public Float getSuggestedPrice(String id) {
        return null;
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
}

