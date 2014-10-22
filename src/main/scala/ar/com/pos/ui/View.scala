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
      row.add(sale.date)
      row.add(sale.description)
      row.add(Float.box(sale.totalPrice))
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

    for (row <- getSalesInRows(sales).asScala){
      table.addRow(row)
    }

    table
  }
}
