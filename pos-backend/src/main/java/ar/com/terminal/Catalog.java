package ar.com.terminal;

import java.util.Date;
import java.util.Map;

import ar.com.terminal.db.Database;
import ar.com.terminal.model.Item;
import ar.com.terminal.model.Sale;

public class Catalog {
	
	private Database database;
	
	public Catalog(Database database){
		this.database = database;
	}

	public Item getItem(String id){
		Item item = database.getProductById(id);
        if(item == null){
            item = new NullItem();
        }
		return item;
	}

	public void addItem(Item item){
		addItem(item, 1);
	}
	
	public void addItem(Item item, Integer amount){
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

    public void remove(Item item) {
        database.remove(item);
        return;
    }
}

