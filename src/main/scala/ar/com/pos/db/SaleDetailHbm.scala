package ar.com.pos.db

class SaleDetailHbm {

  private var _idSale: Long = _
  private var _idProduct: String = _
  private var _amount: Integer = 0

  def getIdsale = _idSale
  def getIdProduct = _idProduct
  def getAmount = _amount

  def setIdsale (value:Long):Unit = _idSale = value
  def setIdproduct (value:String):Unit = _idProduct = value
  def setAmount (value:Integer):Unit = _amount = value

}
