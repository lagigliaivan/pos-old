package ar.com.pos.db

class SaleDetailKeyHbm {

  private var _idsale: Long  = 0
  private var _idproduct: String = _
  private var _amount:Integer = 1

  def getIdsale = _idsale
  def getIdproduct = _idproduct
  def getAmount = _amount

  def setIdsale (value: Long): Unit = _idsale = value
  def setIdproduct (value: String): Unit = _idproduct = value
  def setamount (value: Integer): Unit = _amount = value

}
