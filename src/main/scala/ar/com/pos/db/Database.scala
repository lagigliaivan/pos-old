package ar.com.pos.db

import ar.com.pos.db.dto.Sale


trait Database {
	def getProductById (id:String):ar.com.pos.db.dto.Product
	def addProduct(product: ar.com.pos.db.dto.Product, amount: Integer)
	def getStock(id: String): Integer
	def save(sale: Sale)
	def save(product: ar.com.pos.db.dto.Product)
}