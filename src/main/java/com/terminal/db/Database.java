package com.terminal.db;

import com.terminal.db.dto.Product;
import com.terminal.db.dto.Sale;

public interface Database {
	
	public Product getProductById (String id);
	public void addProduct(Product product, Integer amount);
	public Integer getStock(String id);

	public void save(Sale sale);
	public void save(Product product);
	

}
