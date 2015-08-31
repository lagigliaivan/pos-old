package ar.com.pos.db

class ProductSaleKeyHbm extends Serializable{
	
	private var _idproduct: String = _
	private var _productamount:Integer = 1
	private var _idsale: Long  = 0

	def getIdproduct = _idproduct
	def getProductamount = _productamount
	def getIdsale = _idsale
	
	def setIdproduct (value: String): Unit = _idproduct = value
	def setProductamount (value: Integer): Unit = _productamount = value
	def setIdsale (value: Long): Unit = _idsale = value
	
}