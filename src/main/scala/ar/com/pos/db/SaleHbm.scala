package ar.com.pos.db

import java.util
import java.util.Date
import java.util.HashSet

class SaleHbm {
	
  var _idsale: Long = 0
  var _description: String = _
  var _date: Date = _
  var _detail: java.util.Set[SaleDetailHbm] = _
  var _totalAmount: Float = 0

  def getIdsale = _idsale 
  def setIdsale (value:Long):Unit = {_idsale = value}

  def getDescription = _description
  def setDescription (value:String):Unit = {_description = value}
  
  def getDate = _date 
  def setDate (value:Date):Unit = {_date = value} 

  def getDetail = _detail
  def setDetail (value: java.util.Set[SaleDetailHbm]):Unit = {_detail  = value}

  def getTotalAmount = _totalAmount 
  def setTotalAmount (value:Float):Unit = {_totalAmount = value} 
}