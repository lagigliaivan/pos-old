package com.pos.db.dto

class Product (val id: String, val price: Float, val description: String){


	private var _stock: Int = 1;
	
	def stock = _stock; 
	def stock_= (value:Int):Unit = {_stock = value} 
	
}