package com.terminal;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class MainWindow {

	private JFrame jFrameVenta = null;  //  @jve:decl-index=0:visual-constraint="88,16"
	private JMenuBar jJMenuBar = null;
	private JMenu jMenuFile = null;
	private JMenuItem jMenuItemAddProduct = null;
	private JMenuItem jMenuItemSale = null;
	private SalesWindow saleWindow = null;  //  @jve:decl-index=0:
	private AddProductWindow addProductWindow = null;  //  @jve:decl-index=0:
	/**
	 * This method initializes jFrameVenta	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	public JFrame getJFrameVenta() {
		if (jFrameVenta == null) {
			jFrameVenta = new JFrame();
			jFrameVenta.setSize(new Dimension(540, 326));
			jFrameVenta.setJMenuBar(getJJMenuBar());
			jFrameVenta.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
					System.out.println("windowClosing()"); // TODO Auto-generated Event stub windowClosing()
				}
			});
		}
		return jFrameVenta;
	}
	
	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getJMenuFile());
		}
		return jJMenuBar;
	}
	/**
	 * This method initializes jMenuFile	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenuFile() {
		if (jMenuFile == null) {
			jMenuFile = new JMenu();
			jMenuFile.setText("File");
			jMenuFile.add(getJMenuItemAddProduct());
			jMenuFile.add(getJMenuItemSale());
		}
		return jMenuFile;
	}
	/**
	 * This method initializes jMenuItemAddProduct	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemAddProduct() {
		if (jMenuItemAddProduct == null) {
			jMenuItemAddProduct = new JMenuItem();
			jMenuItemAddProduct.setText("Agregar/Consultar Producto");
			//addProductWindow = new AddProductWindow();
			
			jMenuItemAddProduct.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {
					getJFrameVenta().remove(saleWindow.getJPanelMain());
					getJFrameVenta().setTitle("Agregar/Consultar Producto");
					getJFrameVenta().add(addProductWindow.getJPanelAddProduct());
					addProductWindow.getJPanelAddProduct().setVisible(false);
					addProductWindow.getJPanelAddProduct().repaint();
					addProductWindow.getJPanelAddProduct().setVisible(true);
					getJFrameVenta().repaint();
				}
			});
			
		}
		return jMenuItemAddProduct;
	}
	/**
	 * This method initializes jMenuItemSale	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItemSale() {
		if (jMenuItemSale == null) {
			jMenuItemSale = new JMenuItem();
			jMenuItemSale.setText("Venta");
			
			//saleWindow = new SalesWindow();
			
			jMenuItemSale.addActionListener(new ActionListener() {
				
				
				 public void actionPerformed(ActionEvent e) {
					getJFrameVenta().remove(addProductWindow.getJPanelAddProduct());
					getJFrameVenta().setTitle("Venta");
					getJFrameVenta().add(saleWindow.getJPanelMain());
					saleWindow.getJPanelMain().setVisible(false);
					saleWindow.getJPanelMain().repaint();
					saleWindow.getJPanelMain().setVisible(true);
					getJFrameVenta().repaint();
				}
			});
		}
		return jMenuItemSale;
	}

	public SalesWindow getSaleWindow() {
		return saleWindow;
	}

	public void setSaleWindow(SalesWindow saleWindow) {
		this.saleWindow = saleWindow;
	}

	public AddProductWindow getAddProductWindow() {
		return addProductWindow;
	}

	public void setAddProductWindow(AddProductWindow addProductWindow) {
		this.addProductWindow = addProductWindow;
	}
	
}
