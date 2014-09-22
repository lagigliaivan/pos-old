package ar.com.pos.db

class ProductHbm {
	
	private var _idproduct: String = _;
	private var _description: String = _;
	private var _stock: Integer = 0;
	private var _price: Float = 0;
	private var _sales: Set[Long] = _;

	def idproduct = _idproduct;
	def description = _description;
	def stock = _stock;
	def price = _price;
	def sales = _sales;
	
	def idproduct_= (value:String):Unit = _idproduct = value;
	def description_= (value:String):Unit = _description = value;
	def stock_= (value:Integer):Unit = _stock = value;
	def price_= (value:Float):Unit = _price = value;
	def sales_= (value:Set[Long]):Unit = _sales = value;

}