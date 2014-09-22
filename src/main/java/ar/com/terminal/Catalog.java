package ar.com.terminal;

import java.util.Date;
import java.util.Map;

import com.terminal.db.Database;
import com.terminal.db.dto.Product;
import com.terminal.db.dto.Sale;

public class Catalog {
	
	private Database database;
	
	public Catalog(Database database){
		this.database = database;
	}

	public Product getProduct(String id){
		Product product = database.getProductById(id);
		return product;
	}

	public void addProduct(Product product){
		addProduct(product, 1);
	}
	
	public void addProduct(Product product, Integer amount){
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
}

