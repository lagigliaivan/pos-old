package ar.com.pos.db.dto

class Product (private val prodId: String = "", private val prodPrice: Float, private val prodDescr: String){

	require(prodId != null && !prodId.isEmpty, "Product prodId cannot be empty/null")
	require(prodPrice != null && prodPrice >= 0F, "Product price cannot be negative")
	require(prodDescr != null && !prodDescr.isEmpty, "Product description cannot be empty/null")
	
	private var _stock: Int = 1

	def id = prodId
	def price = prodPrice
	def description = prodDescr
	
	def stock = _stock
	def stock_= (value:Int):Unit = {_stock = value} 
	
}