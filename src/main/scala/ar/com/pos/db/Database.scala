package ar.com.pos.db

import com.terminal.db.dto.Sale;

trait Database {
	def getProductById (id:String):Product;
	def addProduct(product: Product, amount: Integer);
	def getStock(id: String): Integer;
	def save(sale: Sale);
	def save(product: Product);
}