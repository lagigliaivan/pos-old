package ar.com.terminal;

import java.util.Date;
import java.util.Map;

import ar.com.terminal.db.Database;
import ar.com.terminal.db.dto.Item;
import ar.com.terminal.db.dto.Sale;

public class Catalog {
	
	private Database database;
	
	public Catalog(Database database){
		this.database = database;
	}

	public Item getProduct(String id){
		Item item = database.getProductById(id);
		return item;
	}

	public void addProduct(Item item){
		addProduct(item, 1);
	}
	
	public void addProduct(Item item, Integer amount){
		database.addProduct(item, amount);
	}

	public Integer getStock(String id){
		return database.getStock(id);
	}
	public void sell(Date date, Map<Item, Integer> products, Float totalPrice){
		database.save(new Sale(date, products, totalPrice));
	}

	public void save(Item item) {
		// TODO Auto-generated method stub
		
	}
}

