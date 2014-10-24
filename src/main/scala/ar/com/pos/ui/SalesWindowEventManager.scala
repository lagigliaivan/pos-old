package ar.com.pos.ui

import javax.swing.{JTable, JTextField}
import javax.swing.table.DefaultTableModel

import ar.com.pos.Catalog
import ar.com.pos.db.DBConnection
import java.util.{Date, ArrayList}
import ar.com.terminal.SalesWindow

/**
 * Created by yo on 22/10/14.
 */
class SalesWindowEventManager (val salesWindow: SalesWindow) {

  def executeWhenPressingEnterForSellingAProduct() = {

    val catalog = new Catalog(DBConnection)
    val product = catalog.getProduct(salesWindow.getProductIdFromField())

    val products: java.util.List[ar.com.pos.db.dto.Product] = new ArrayList[ar.com.pos.db.dto.Product]()
    products.add(product)

    val view = new View
    view.addProductsToTheFollowingTable(salesWindow.getTableModel(), products)

    var subTotal = salesWindow.getSubTotal
    subTotal += product.price

    salesWindow.writeInFieldProductDesc(product.description)
    salesWindow.writeInFieldProductPrice(product.price.toString)

    salesWindow.writeInFieldSubTotal(subTotal.toString)
    salesWindow.clearFieldProductId()

    val productsToBeSold = salesWindow.addProductToCurrentSale(product)

    productsToBeSold
  }

  def executeWhenPressingButtonToSaveASale() = {

    val catalog = new Catalog(DBConnection)
    catalog.sell(new Date, salesWindow.getProductsToBeSold(), salesWindow.getSubTotal)
    salesWindow.clearPreviousSaleData
  }
}
