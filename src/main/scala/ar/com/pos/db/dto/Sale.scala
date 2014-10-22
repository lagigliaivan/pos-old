package ar.com.pos.db.dto

import java.util.Date
import javax.management.remote.rmi._RMIConnection_Stub

class Sale (val date:Date , val products: java.util.Map[Product, Integer], val totalPrice: Float){

  var _description: String = _

  def description() : String = _description
  def description_= (value: String):Unit = _description = value
}