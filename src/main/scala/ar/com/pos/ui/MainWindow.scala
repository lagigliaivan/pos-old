package ar.com.pos.ui

import java.awt.Dimension
import java.awt.Frame
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

import org.slf4j.LoggerFactory

import ar.com.terminal.AddProductWindow
import ar.com.terminal.SalesWindow
import javax.swing.JFrame
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class MainWindow(saleWindow: SalesWindow, addProductWindow: AddProductWindow) {

  private val jFrameVenta = new JFrame();
  private val jJMenuBar = new JMenuBar();

  private val jMenuFile = new JMenu();
  private val jMenuItemAddProduct = new JMenuItem();
  private val jMenuItemSale = new JMenuItem();
  private val log = LoggerFactory.getLogger("MainWindow");
	
  
  protected def getJFrameVenta(): JFrame = {

    jFrameVenta.setSize(new Dimension(540, 326));
    jFrameVenta.setJMenuBar(getJJMenuBar());
    jFrameVenta.addWindowListener(new java.awt.event.WindowAdapter() {
      override def windowClosing(e: java.awt.event.WindowEvent) {
        System.exit(0);
        log.info("windowClosing()");
      }
    });

    return jFrameVenta;
  }

  /**
   * This method initializes jJMenuBar
   *
   * @return javax.swing.JMenuBar
   */
  protected def getJJMenuBar(): JMenuBar = {

    jJMenuBar.add(getJMenuFile());
    return jJMenuBar;
  }

  /**
   * This method initializes jMenuFile
   *
   * @return javax.swing.JMenu
   */
  protected def getJMenuFile(): JMenu = {
    jMenuFile.setText("File");
    jMenuFile.add(getJMenuItemAddProduct());
    jMenuFile.add(getJMenuItemSale());
    return jMenuFile;
  }

  /**
   * This method initializes jMenuItemAddProduct
   *
   * @return javax.swing.JMenuItem
   */
  protected def getJMenuItemAddProduct(): JMenuItem = {
    jMenuItemAddProduct.setText("Agregar/Consultar Producto");

    jMenuItemAddProduct.addActionListener(new ActionListener() {

      def actionPerformed(e: ActionEvent) {
        getJFrameVenta().remove(saleWindow.getJPanelMain());
        getJFrameVenta().setTitle("Agregar/Consultar Producto");
        getJFrameVenta().add(addProductWindow.getJPanelAddProduct());
        addProductWindow.getJPanelAddProduct().setVisible(false);
        addProductWindow.getJPanelAddProduct().repaint();
        addProductWindow.getJPanelAddProduct().setVisible(true);
        getJFrameVenta().repaint();
      }
    });

    return jMenuItemAddProduct;
  }

  /**
   * This method initializes jMenuItemSale
   *
   * @return javax.swing.JMenuItem
   */
  protected def getJMenuItemSale(): JMenuItem = {

    jMenuItemSale.setText("Venta");
    jMenuItemSale.addActionListener(new ActionListener() {

      def actionPerformed(e: ActionEvent) {
        getJFrameVenta().remove(addProductWindow.getJPanelAddProduct());
        getJFrameVenta().setTitle("Venta");
        getJFrameVenta().add(saleWindow.getJPanelMain());
        saleWindow.getJPanelMain().setVisible(false);
        saleWindow.getJPanelMain().repaint();
        saleWindow.getJPanelMain().setVisible(true);
        getJFrameVenta().repaint();
      }
    });
    return jMenuItemSale;
  }

  def run() = {

    getJFrameVenta().add(saleWindow.getJPanelMain());
    getJFrameVenta().add(saleWindow.getJPanelMain());
    getJFrameVenta().setExtendedState(Frame.MAXIMIZED_BOTH);
    getJFrameVenta().setVisible(true);
  }
}