package ar.com.pos.ui

import java.util.ArrayList

import ar.com.pos.db.dto.Product
import ar.com.terminal.AddProductWindow
import ar.com.pos.db.DBConnection
import javax.swing.event.ListSelectionEvent


class AddProductWindowEventManager(addProductWindow: AddProductWindow) {

  def executeWhenLookingForProductByDescription () = {

    val tableModel = addProductWindow.getTableModel
    val productId = addProductWindow.getProductId


    if( !productId.isEmpty() && (tableModel.getRowCount() > 0)){

      val products  = DBConnection.getProductsbyDescription(addProductWindow.getProductDescription)
      val view = new View

      view.addProductsToTheFollowingTable(tableModel, products)
      addProductWindow.enableAddingProduct(true)
    }
  }

  def executeWhenLookingForProductById () = {

    val productId = addProductWindow.getProductId

    if(productId.matches("[a-zA-z0-9]*")){

      val product = DBConnection.getProductById(productId)
      val products = new ArrayList[Product]()

      products.add(product)

      addProductWindow.getTableModel().clear();

      val view = new View
      view.addProductsToTheFollowingTable(addProductWindow.getTableModel(), products)
      addProductWindow.enableAddingProduct(true)

    }
  }

  def executeWhenChangingSelectionInProductWindow(event: ListSelectionEvent){

    val tableModel = addProductWindow.getTableModel
    val row = new Array[AnyRef](tableModel.getColumnCount)

    val productListTable = addProductWindow.getTableProductList

    if ((event.getSource eq productListTable.getSelectionModel) && productListTable.getRowSelectionAllowed) {

      val viewRow = productListTable.getSelectedRow

      if (viewRow >= 0) {
      {
          var i  = 0
          while (i < tableModel.getColumnCount()) {
						row.update(i, tableModel.getValueAt(viewRow, i))
            i += 1
					}
      }

      addProductWindow.writeInFieldProductId(row(0).asInstanceOf[String]);
      addProductWindow.writeInFieldProductDescription(row(1).asInstanceOf[String])
      addProductWindow.writeInFieldProductStock(row(2).asInstanceOf[Float].toString)

      }
    }
  }
}
