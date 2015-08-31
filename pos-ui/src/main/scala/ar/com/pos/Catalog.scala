package ar.com.pos

import java.util.Date

import ar.com.pos.db.Database
import ar.com.pos.db.dto.{Product, Sale}

class Catalog(database: Database) {
	
	def getProduct(id: String) = database.getProductById(id)
	def addProduct(product: Product, amount: Int) : Unit = database.addProduct(product, amount)
	def addProduct(product: Product) : Unit = addProduct(product, 1)
	def getStock(id: String) = database.getStock(id)

  def sell(date: Date, products: java.util.Map[Product, Integer], totalPrice: Float) = {

    require(!products.isEmpty, "Sale cannot be empty")
    require(date != null, "Sale date cannot be null")

    database.save(new Sale(date, products, totalPrice))
  }

  def save(product: Product) = {
    require(product != null, "Product cannot be null")
    database.save(product)
  }
	
}