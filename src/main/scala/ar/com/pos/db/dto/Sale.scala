package ar.com.pos.db.dto

import java.util.Date
import javax.management.remote.rmi._RMIConnection_Stub

class Sale (val date:Date , val products: java.util.Map[Product, Integer], val totalPrice: Double){

  private var _description: String = _
  private var _id : Long = 0

  def id = _id
  def id_= (value: Long): Unit = _id = value
  def description() : String = _description
  def description_= (value: String):Unit = _description = value

}