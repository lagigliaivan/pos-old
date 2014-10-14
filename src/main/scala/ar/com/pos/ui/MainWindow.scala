package ar.com.pos.ui

import java.awt.Dimension
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import org.slf4j.LoggerFactory


import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class MainWindow(saleWindow: ar.com.terminal.SalesWindow, addProductWindow: ar.com.terminal.AddProductWindow) {

  private var jFrameVenta : JFrame = _
  private var jMenuBar : JMenuBar = _

  private var jMenuFile : JMenu = _
  private var jMenuItemAddProduct : JMenuItem = _
  private var jMenuItemSale: JMenuItem = _
  private val log = LoggerFactory.getLogger("MainWindow")
	
  
  protected def getJFrameVenta: JFrame = {

    if(jFrameVenta == null) {
      jFrameVenta = new JFrame()
      jFrameVenta.setSize(new Dimension(540, 326))
      jFrameVenta.setJMenuBar(getJJMenuBar)
      jFrameVenta.addWindowListener(new java.awt.event.WindowAdapter() {
        override def windowClosing(e: java.awt.event.WindowEvent) {
          System.exit(0)
          log.info("windowClosing()")
        }
      })
    }
    jFrameVenta
  }

  /**
   * This method initializes jJMenuBar
   *
   * @return javax.swing.JMenuBar
   */
  protected def getJJMenuBar: JMenuBar = {

    if(jMenuBar == null) {
      jMenuBar = new JMenuBar()
      jMenuBar.add(getJMenuFile)
    }
    jMenuBar
  }

  /**
   * This method initializes jMenuFile
   *
   * @return javax.swing.JMenu
   */
  protected def getJMenuFile: JMenu = {

    if(jMenuFile == null) {
      jMenuFile = new JMenu()
      jMenuFile.setText("File")
      jMenuFile.add(getJMenuItemAddProduct)
      jMenuFile.add(getJMenuItemSale)
    }

    jMenuFile
  }

  /**
   * This method initializes jMenuItemAddProduct
   *
   * @return javax.swing.JMenuItem
   */
  protected def getJMenuItemAddProduct: JMenuItem = {

    if(jMenuItemAddProduct == null) {

      jMenuItemAddProduct = new JMenuItem()
      jMenuItemAddProduct.setText("Agregar/Consultar Producto")

      jMenuItemAddProduct.addActionListener(new ActionListener() {

        def actionPerformed(e: ActionEvent) {
          getJFrameVenta.remove(saleWindow.getJPanelMain)
          getJFrameVenta.setTitle("Agregar/Consultar Producto")
          getJFrameVenta.add(addProductWindow.getJPanelAddProduct)
          addProductWindow.getJPanelAddProduct.setVisible(false)
          addProductWindow.getJPanelAddProduct.repaint()
          addProductWindow.getJPanelAddProduct.setVisible(true)
          getJFrameVenta.repaint()
        }
      })
    }

    jMenuItemAddProduct
  }

  protected def getJMenuItemSale: JMenuItem = {

    if(jMenuItemSale == null) {
      jMenuItemSale = new JMenuItem()
      jMenuItemSale.setText("Venta")
      jMenuItemSale.addActionListener(new ActionListener() {

        def actionPerformed(e: ActionEvent) {
          getJFrameVenta.remove(addProductWindow.getJPanelAddProduct)
          getJFrameVenta.setTitle("Venta")
          getJFrameVenta.add(saleWindow.getJPanelMain)
          saleWindow.getJPanelMain.setVisible(false)
          saleWindow.getJPanelMain.repaint()
          saleWindow.getJPanelMain.setVisible(true)
          getJFrameVenta.repaint()
        }
      })
    }

    jMenuItemSale
  }

  def run() = {

    //getJFrameVenta.add(saleWindow.getJPanelMain)
    getJFrameVenta.add(saleWindow.getJPanelMain)
    getJFrameVenta.setExtendedState(Frame.MAXIMIZED_BOTH)
    getJFrameVenta.setVisible(true)
    saleWindow.focusProductId();
  }
}