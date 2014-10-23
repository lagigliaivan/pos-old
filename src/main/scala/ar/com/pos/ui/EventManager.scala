package ar.com.pos.ui

import javax.swing.{JTable, JTextField}
import javax.swing.table.DefaultTableModel

import ar.com.pos.Catalog
import ar.com.pos.db.DBConnection
import java.util.ArrayList

/**
 * Created by yo on 22/10/14.
 */
class EventManager {

  def executeWhenPressingEnterToAddAProduct(productId: String, jTextFieldPrice: JTextField, jTextFieldProductDesc: JTextField, table: JTable) = {

    val catalog = new Catalog(DBConnection)
    val product = catalog.getProduct(productId)

    jTextFieldProductDesc.setText(product.description)
    jTextFieldPrice.setText(product.price.toString)

    val subtotal = 0 += product.price

    val products: java.util.List[ar.com.pos.db.dto.Product] = new ArrayList[ar.com.pos.db.dto.Product]()

    products.add(product)

    val view: View = new View
    view.addProductsToTheFollowingTable(table.getModel.asInstanceOf[DefaultTableModel], products)

   // jTableProducts.setModel(getTableModel)
    jTextFieldSubTotal.setText(Float.toString(subtotal))
    productIdTextField.setText(null)

    var amountAlreadySold  = 0

    if (productsToBeSold.containsKey(product)) {
      amountAlreadySold = productsToBeSold.get(product)
    }

    productsToBeSold.put(product, amountAlreadySold + new Integer(1))
  }

}
