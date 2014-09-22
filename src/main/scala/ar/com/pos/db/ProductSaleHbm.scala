package ar.com.pos.db

import ar.com.terminal.db.ProductSaleKey;

class ProductSaleHbm {

  private var _idproduct: Long = 0;
  private var _idsale: Long = 0;
  private var _productamount: Integer = 1;
  private var _productSaleKey: ProductSaleKey = _;

  def idproduct = _idproduct;
  def idsale = _idsale;
  def productamount = _productamount;
  def productSaleKey = _productSaleKey;
  
  def idproduct_= (value: Long):Unit = _idproduct = value;
  def idsale_= (value: Long): Unit = _idsale = value;
  def productamount_= (value: Integer): Unit = _productamount = value;
  def productSaleKey_= (value: ProductSaleKey): Unit = _productSaleKey = value;

}