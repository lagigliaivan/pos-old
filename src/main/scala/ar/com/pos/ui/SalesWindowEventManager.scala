package ar.com.pos.ui

import java.util.Date

import ar.com.pos.Catalog
import ar.com.pos.db.DBConnection
import ar.com.pos.db.dto.Product
import ar.com.terminal.SalesWindow

/**
 * Created by yo on 22/10/14.
 */
class SalesWindowEventManager (val salesWindow: SalesWindow) {

  private def addProductToTheSaleList(catalog: Catalog, product: Product):Float = {
    val view = new View
    val products: java.util.List[ar.com.pos.db.dto.Product] = new java.util.ArrayList[ar.com.pos.db.dto.Product]
    products.add(product)
    view.addProductsToTheFollowingTable(salesWindow.getTableModel, products)
    var subTotal = salesWindow.getSubTotal
    subTotal += product.price
    subTotal
  }

  private def removeProductFromSaleList(catalog: Catalog, product: Product):Float = {
    val view = new View
    view.removeProductFromTheFollowingTable(salesWindow.getTableModel, product)
    var subTotal = salesWindow.getSubTotal
    subTotal -= product.price
    subTotal
  }

  def executeWhenPressingEnterForSellingAProduct() = {

    val catalog = new Catalog(DBConnection)
    var productId = salesWindow.getProductIdFromField
    var modifyProductList: (Catalog, Product) => Float = addProductToTheSaleList
    var modifyAlreadySoldProducts : (Product) => Unit = salesWindow.addProductToCurrentSale

    if(productId.startsWith("-")){
      productId = productId.substring(1)
      modifyProductList = removeProductFromSaleList
      modifyAlreadySoldProducts = salesWindow.removeProductFromCurrentSale
    }

    val product = catalog.getProduct(productId)

    val subTotal = modifyProductList(catalog, product)

    salesWindow.writeInFieldProductDesc(product.description)
    salesWindow.writeInFieldProductPrice(product.price.toString)

    salesWindow.writeInFieldSubTotal(subTotal.toString)
    salesWindow.clearFieldProductId()

    val productsToBeSold = modifyAlreadySoldProducts(product)

    productsToBeSold
  }

  def executeWhenPressingButtonToSaveASale() = {

    val catalog = new Catalog(DBConnection)
    catalog.sell(new Date, salesWindow.getProductsToBeSold, salesWindow.getSubTotal)
    salesWindow.clearPreviousSaleData()
  }
}
