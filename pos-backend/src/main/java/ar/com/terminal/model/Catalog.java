package ar.com.terminal.model;

import java.util.Date;
import java.util.Map;

import ar.com.terminal.db.Database;

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

	public void addItem(Product product){
		addCatalog(product, 1);
	}
	
	public void addCatalog(Product product, Integer amount){
		database.addProduct(product, amount);
	}

	public Integer getStock(String id){
		return database.getStock(id);
	}
	public void sell(Date date, Map<Product, Integer> products, Float totalPrice){
		database.save(new Sale(date, products, totalPrice));
	}

	public void save(Product product) {
		// TODO Auto-generated method stub
		
	}

    public void remove(Product product) {
        database.remove(product);
        return;
    }
}

