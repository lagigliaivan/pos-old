package ar.com.pos.db

import java.util.Date
import java.util.HashSet

import com.terminal.db.ProductHbm

class SaleHbm {
	
  var _idsale: Long = 0;
  var _idproduct: Long = 0;
  var _description: String = _;
  var _date: Date = _; 
  var _products = new HashSet[ProductHbm]();
  var _totalAmount: Float = 0;

  def idsale = _idsale; 
  def idsale_= (value:Int):Unit = {_idsale = value}
  
  def idproduct = _description; 
  def idproduct_= (value:Int):Unit = {_idproduct = value}
  
  def date = _date; 
  def date_= (value:Date):Unit = {_date = value} 

  def products = _products; 
  def products_= (value:HashSet[ProductHbm]):Unit = {_products = value} 

  def totalAmount = _totalAmount; 
  def totalAmount_= (value:Int):Unit = {_totalAmount = value} 
}