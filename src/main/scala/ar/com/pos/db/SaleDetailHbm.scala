package ar.com.pos.db

class SaleDetailHbm extends Serializable{

  private var _saleDetailKey: SaleDetailKeyHbm = _
  private var _amount: Integer = 0
  private var _sale: SaleHbm = _

  def getAmount = _amount
  def getSale = _sale
  def getSaleDetailKey = _saleDetailKey

  def setIdsale (value:Long):Unit = value
  def setIdproduct (value:String):Unit = value
  def setSaleDetailKey (value: SaleDetailKeyHbm) : Unit = _saleDetailKey = value
  def setAmount (value:Integer):Unit = _amount = value
  def setSale (value: SaleHbm):Unit = _sale = value

}
