package ar.com.pos

import java.util.Date
import java.util.Map

import ar.com.pos.db.Database
import ar.com.pos.db.dto.Product
import ar.com.pos.db.dto.Sale

class Catalog(database: Database) {
	
	def getProduct(id: String) = database.getProductById(id);
	def addProduct(product: Product, amount: Int) : Unit = database.addProduct(product, amount);
	def addProduct(product: Product) : Unit = addProduct(product, 1);
	def getStock(id: String) = database.getStock(id);
	def sell(date: Date, products: java.util.Map[Product, Integer], totalPrice: Float) = database.save(new Sale(date, products, totalPrice));
	def save(product: Product) = {};
	
}