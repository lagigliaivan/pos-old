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

  private def addProductToTheSaleList(catalog: Catalog, product: Product, amount: Integer): Float ={
    val view = new View
    val products: java.util.List[ar.com.pos.db.dto.Product] = new java.util.ArrayList[ar.com.pos.db.dto.Product]

    for( i <- 1 to amount){
      products.add(product)
    }

    view.addProductsToTheFollowingTable(salesWindow.getTableModel, products)
    var subTotal = salesWindow.getSubTotal

    subTotal += (product.price * amount)

    subTotal
  }

  private def addProductToTheSaleList(catalog: Catalog, product: Product):Float = {
    addProductToTheSaleList(catalog, product, 1);
  }

  private def removeProductFromSaleList(catalog: Catalog, product: Product, amount: Integer):Float = {
    val view = new View
    for(i <- 1 to amount) {
      view.removeProductFromTheFollowingTable(salesWindow.getTableModel, product)
    }
    var subTotal = salesWindow.getSubTotal
    subTotal -= product.price
    subTotal
  }

  def executeWhenPressingEnterForSellingAProduct() = {

    val catalog = new Catalog(DBConnection)
    var productIdAndAmount = salesWindow.getProductIdFromField
    var modifyProductList: (Catalog, Product, Integer) => Float = addProductToTheSaleList
    var modifyAlreadySoldProducts : (Product, Integer) => Unit = salesWindow.addProductToCurrentSale
    var productId = productIdAndAmount;
    var productAmount = 1;

    if(productIdAndAmount.startsWith("-")){

      productId = productIdAndAmount.substring(1)
      modifyProductList = removeProductFromSaleList
      modifyAlreadySoldProducts = salesWindow.removeProductFromCurrentSale

    }else if(productIdAndAmount.matches("^\\d+[xX]\\d+")){  //2x1212121, 6X12341212 this conditional gives the option of inserting multiple products in one time.

      productIdAndAmount = productIdAndAmount.toLowerCase
      val indexOfMultiplicationChar: Int = productIdAndAmount.indexOf("x")
      var productAmountAsString = productIdAndAmount.substring(0, indexOfMultiplicationChar)
      productId = productIdAndAmount.substring(indexOfMultiplicationChar + 1, productIdAndAmount.length)
      productAmount = Integer.valueOf(productAmountAsString)
    }

    val product = catalog.getProduct(productId)

    val subTotal = modifyProductList(catalog, product, productAmount)

    salesWindow.writeInFieldProductDesc(product.description)
    salesWindow.writeInFieldProductPrice(product.price.toString)

    salesWindow.writeInFieldSubTotal(subTotal.toString)
    salesWindow.clearFieldProductId()

    val productsToBeSold = modifyAlreadySoldProducts(product, productAmount)

    productsToBeSold
  }

  def executeWhenPressingButtonToSaveASale() = {

    val catalog = new Catalog(DBConnection)
    catalog.sell(new Date, salesWindow.getProductsToBeSold, salesWindow.getSubTotal)
    salesWindow.clearPreviousSaleData()
  }
}