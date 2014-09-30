package ar.com.pos.db

import java.util

class ProductHbm {
	
	private var _idproduct: String = _
	private var _description: String = _
	private var _stock: Integer = 0
	private var _price: Float = 0
	private var _sales = new util.HashSet[SaleHbm]()

	def getIdproduct = _idproduct
	def getDescription = _description
	def getStock = _stock
	def getPrice = _price
	def getSales = _sales
	
	def setIdproduct (value:String):Unit = _idproduct = value
	def setDescription (value:String):Unit = _description = value
	def setStock (value:Integer):Unit = _stock = value
	def setPrice (value:Float):Unit = _price = value
	def setSales (value: util.HashSet[SaleHbm]):Unit = _sales = value
}