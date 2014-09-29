package ar.com.pos.db

class ProductSaleHbm {

  private var _idproduct: Long = 0;
  private var _idsale: Long = 0;
  private var _productamount: Integer = 1;
  private var _productSaleKey: ProductSaleKeyHbm = _;

  def getIdproduct = _idproduct;
  def getIdsale = _idsale;
  def getProductamount = _productamount;
  def getProductSaleKey = _productSaleKey;
  
  def setIdproduct (value: Long) = _idproduct = value;
  def setIdsale (value: Long) = _idsale = value;
  def setProductamount (value: Integer) = _productamount = value;
  def setProductSaleKey (value: ProductSaleKeyHbm) = _productSaleKey = value;

}