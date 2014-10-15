package ar.com.pos.db

class SaleDetailHbm extends Serializable{

  private var _id : Long = _
  //private var _saleDetailKey: SaleDetailKeyHbm = _
  private var _idSale : Long = _
  private var _idProduct : String = _
  private var _amount: Integer = 0
  private var _sale: SaleHbm = _

  def getId = _id
  def getIdSale = _idSale
  def getIdProduct = _idProduct
  def getAmount = _amount
  def getSale = _sale
  //def getSaleDetailKey = _saleDetailKey

  def setId (value:Long):Unit = _id = value
  def setIdSale (value:Long):Unit = _idSale = value
  def setIdProduct (value:String):Unit = _idProduct = value
  //def setIdproduct (value:String):Unit = value
  //def setSaleDetailKey (value: SaleDetailKeyHbm) : Unit = _saleDetailKey = value
  def setAmount (value:Integer):Unit = _amount = value
  def setSale (value: SaleHbm):Unit = _sale = value

}
