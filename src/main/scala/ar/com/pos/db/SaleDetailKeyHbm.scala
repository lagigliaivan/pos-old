package ar.com.pos.db

class SaleDetailKeyHbm extends Serializable{

  private var _idsale: Long  = 0
  private var _idproduct: String = _

  def getIdsale = _idsale
  def getIdproduct = _idproduct

  def setIdsale (value: Long): Unit = _idsale = value
  def setIdproduct (value: String): Unit = _idproduct = value

}
