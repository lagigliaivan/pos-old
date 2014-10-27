package ar.com.pos.ui

import java.util.{List, ArrayList}
import javax.swing.JOptionPane

import ar.com.pos.Catalog
import ar.com.pos.db.dto.Product
import ar.com.terminal.AddProductWindow
import ar.com.pos.db.DBConnection
import javax.swing.event.ListSelectionEvent
import ar.com.terminal.exception.TerminalException
import org.hsqldb.lib.ArrayListIdentity
import org.slf4j.LoggerFactory


class AddProductWindowEventManager(addProductWindow: AddProductWindow) {

  private val Log = LoggerFactory.getLogger (classOf[AddProductWindow] )

  def executeWhenLookingForProductByDescription () = {

    val tableModel = addProductWindow.getTableModel
    val productDescription = addProductWindow.getProductDescription

    var products: List[Product] = null

    if( !productDescription.isEmpty()){
      products  = DBConnection.getProductsbyDescription(addProductWindow.getProductDescription)
    }else{
      products = DBConnection.getAllProducts()
    }

    val view = new View
    tableModel.clear()
    view.addProductsToTheFollowingTable(tableModel, products)
    addProductWindow.enableAddingProduct(true)
  }

  def executeWhenLookingForProductById () = {

    val productId = addProductWindow.getProductId
    var products: List[Product] = null

    if(productId.matches("[a-zA-Z0-9]+")) {
      products = DBConnection.getProductsById(productId)
    }else{
      products = DBConnection.getAllProducts()
    }

    addProductWindow.getTableModel().clear();
    val view = new View
    view.addProductsToTheFollowingTable(addProductWindow.getTableModel(), products)
    addProductWindow.enableAddingProduct(true)
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

  def executeWhenSavingAProduct() = {

    val n: Int = JOptionPane.showOptionDialog(addProductWindow.getJPanelAddProduct, "Esta seguro que quiere agregar este articulo? ", null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null)

    if (n == JOptionPane.YES_OPTION) {
      val product: Product = new Product(addProductWindow.getProductId, new java.lang.Float(addProductWindow.getProductPrice), addProductWindow.getProductDescription)
      product.setStock(Integer.valueOf(addProductWindow.getProductStock))

      try {

        val catalog = new Catalog(ar.com.pos.db.DBConnection)
        catalog.save(product)
        addProductWindow.getTableModel.clear()
        executeWhenLookingForProductById()

      }
      catch {
        case ex: TerminalException => {
          Log.error(ex.getMessage)
        }
      }
    }
  }
}
