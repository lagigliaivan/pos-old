package ar.com.pos.db

class ProductSaleKeyHbm {
	
	private var _idproduct: String = _;
	private var _productamount:Integer = 1;
	private var _idsale: Long  = 0;

	def idproduct = _idproduct;
	def productamount = _productamount;
	def idsale = _idsale;
	
	def idproduct_= (value: String): Unit = _idproduct = value;
	def productamount_= (value: Integer): Unit = _productamount = value;
	def idsale_= (value: Long): Unit = _idsale = value;
	
}