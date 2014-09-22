package ar.com.pos

import ar.com.terminal.db.Database;
import ar.com.terminal.db.dto.Product;
import ar.com.terminal.db.dto.Sale;

import java.util.Date;
import java.util.Map;

class Catalog(database: Database) {
	
	def getProduct(id: String) = database.getProductById(id);
	def addProduct(product: Product, amount: Int) : Unit = database.addProduct(product, amount);
	def addProduct(product: Product) : Unit = addProduct(product, 1);
	def getStock(id: String) = database.getStock(id);
	def sell(date: Date, products: Map[Product, Integer], totalPrice: Float) = database.save(new Sale(date, products, totalPrice));
	def save(product: Product) = {};
	
}