package ar.com.terminal.db;

import ar.com.terminal.db.dto.Item;
import ar.com.terminal.db.dto.Sale;

public interface Database {
	
	public Item getProductById (String id);
	public void addProduct(Item item, Integer amount);
	public Integer getStock(String id);

	public void save(Sale sale);
	public void save(Item item);
	

}
