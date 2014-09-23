package ar.com.pos.db.dto

class Product (private val prodId: String, private val prodPrice: Float, private val prodDescr: String){

	private var _stock: Int = 1;

	def id = prodId;
	def price = prodPrice;
	def description = prodDescr;
	
	def stock = _stock; 
	def stock_= (value:Int):Unit = {_stock = value} 
	
}