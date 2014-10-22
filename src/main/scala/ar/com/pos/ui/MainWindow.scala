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
import ar.com.terminal.SalesReportWindow
import ar.com.pos.db.DBConnection

class MainWindow(saleWindow: ar.com.terminal.SalesWindow, addProductWindow: ar.com.terminal.AddProductWindow, salesReportWindow: SalesReportWindow) {

  private var jFrameMainContainer : JFrame = _
  private var jMenuBar : JMenuBar = _

  private var jMenuFile : JMenu = _
  private var jMenuItemAddProduct : JMenuItem = _
  private var jMenuItemSale: JMenuItem = _
  private var jMenuItemSalesReport : JMenuItem = _
  private val log = LoggerFactory.getLogger("MainWindow")
	
  
  protected def getJFrameMainContainer: JFrame = {

    if(jFrameMainContainer == null) {
      jFrameMainContainer = new JFrame()
      jFrameMainContainer.setSize(new Dimension(540, 326))
      jFrameMainContainer.setJMenuBar(getJJMenuBar)
      jFrameMainContainer.addWindowListener(new java.awt.event.WindowAdapter() {
        override def windowClosing(e: java.awt.event.WindowEvent) {
          System.exit(0)
          log.info("windowClosing()")
        }
      })
    }
    jFrameMainContainer
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
      jMenuFile.add(getJMenuItemSalesReport)
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
          getJFrameMainContainer.remove(saleWindow.getJPanelMain)
          getJFrameMainContainer.remove(salesReportWindow.getJPanelSalesReport)

          getJFrameMainContainer.setTitle("Agregar/Consultar Producto")
          getJFrameMainContainer.add(addProductWindow.getJPanelAddProduct)
          addProductWindow.getJPanelAddProduct.setVisible(false)
          addProductWindow.getJPanelAddProduct.repaint()
          addProductWindow.getJPanelAddProduct.setVisible(true)
          getJFrameMainContainer.repaint()
        }
      })
    }

    jMenuItemAddProduct
  }

  protected def getJMenuItemSale: JMenuItem  = {

    if(jMenuItemSale == null) {
      jMenuItemSale = new JMenuItem()
      jMenuItemSale.setText("Venta")
      jMenuItemSale.addActionListener(new ActionListener() {

        def actionPerformed(e: ActionEvent) {
          getJFrameMainContainer.remove(addProductWindow.getJPanelAddProduct)
          getJFrameMainContainer.remove(salesReportWindow.getJPanelSalesReport)
          getJFrameMainContainer.setTitle("Venta")
          getJFrameMainContainer.add(saleWindow.getJPanelMain)
          saleWindow.getJPanelMain.setVisible(false)
          saleWindow.getJPanelMain.repaint()
          saleWindow.getJPanelMain.setVisible(true)
          getJFrameMainContainer.repaint()
        }
      })
      }
      jMenuItemSale
    }
    protected def getJMenuItemSalesReport: JMenuItem = {



      if(jMenuItemSalesReport == null) {
        jMenuItemSalesReport = new JMenuItem()
        jMenuItemSalesReport.setText("Total de Ventas")
        jMenuItemSalesReport.addActionListener(new ActionListener() {


          def actionPerformed(e: ActionEvent) {
           getJFrameMainContainer.remove(saleWindow.getJPanelMain)
           getJFrameMainContainer.remove(addProductWindow.getJPanelAddProduct)
           getJFrameMainContainer.setTitle("Total de Ventas")
           getJFrameMainContainer.add(salesReportWindow.getJPanelSalesReport)

           salesReportWindow.setData(DBConnection.getSales())

           salesReportWindow.getJPanelSalesReport.setVisible(false)
           salesReportWindow.getJPanelSalesReport.repaint()
           salesReportWindow.getJPanelSalesReport.setVisible(true)

           getJFrameMainContainer.repaint()
          }
        })
      }

    jMenuItemSalesReport
  }

  def run() = {

    getJFrameMainContainer.add(saleWindow.getJPanelMain)
    getJFrameMainContainer.setExtendedState(Frame.MAXIMIZED_BOTH)
    getJFrameMainContainer.setVisible(true)
    saleWindow.focusProductId()
  }
}
