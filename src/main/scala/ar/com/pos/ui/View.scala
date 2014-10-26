package ar.com.pos.ui

import java.util.ArrayList
import java.util.List
import java.util.Vector

import javax.swing.table.DefaultTableModel

import scala.collection.JavaConverters._

import ar.com.pos.db.dto.Product
import ar.com.pos.db.dto.Sale


class View {

	def getProductsInRows(products: List[Product]):List [Vector [Object]] = {
		
		val result = new ArrayList[Vector[Object]]()
		
		for ( product <- products.asScala ) {
				val row = new Vector [Object]()
			    row.add(product.id)
			    row.add(product.description)
			    row.add(Float.box(product.price))
			    result.add(row)
		}
		
		result
	}

  def getSalesInRows(sales: List[Sale]):List [Vector [Object]] = {

    val result = new ArrayList[Vector[Object]]()

    for ( sale <- sales.asScala ) {
      val row = new Vector [Object]()
      row.add(long2Long(sale.id))
      row.add(sale.date)
      row.add(double2Double(sale.totalPrice))
      row.add(int2Integer(sale.productsTotalAmount))
      result.add(row)
    }

    result
  }

	def addProductsToTheFollowingTable(table: DefaultTableModel, products: List [Product]): DefaultTableModel = {
		
		for (row <- getProductsInRows(products).asScala){
			table.addRow(row)
		}
		
		table
	}
	
	def addProductToTheFollowingTable(table: DefaultTableModel, product: Product): DefaultTableModel = {
		
		val products = new ArrayList[Product]()
		products.add(product)
		
		addProductsToTheFollowingTable(table, products)
		
		table
	}

  def addSalesToTheFollowingTable(table: DefaultTableModel, sales: List [Sale]) :DefaultTableModel = {
    val rows : List [Vector [Object]] = getSalesInRows(sales)
    for (row <- rows.asScala){
      table.addRow(row)
    }
    table
  }
}
