package ar.com.terminal;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.com.pos.db.*;
import ar.com.pos.ui.*;
import ar.com.pos.ui.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.pos.db.dto.Product;
import ar.com.terminal.exception.TerminalException;


public class AddProductWindow {

	private JPanel jPanelAddProduct = null; // @jve:decl-index=0:visual-constraint="61,35"
	private JTextField jTextFieldDesc = null;
	private JTextField jTextFieldPrice = null;
	private JLabel jLabelDesc = null;
	private JLabel jLabelPrice = null;
	private JScrollPane jScrollPaneProductList = null;
	private JTable jTableProductList = null;
	private JLabel jLabelProductId = null;
	private ProductTableModel tableModel = null; // @jve:decl-index=0:

	private ArrayList<String> columsName = null;
	private JTextField jTextFieldStock = null;
	private JLabel jLabelStock = null;
	private JTextField jTextFieldProdId = null;
	
	private Logger Log = LoggerFactory.getLogger(AddProductWindow.class);  //  @jve:decl-index=0:

    AddProductWindowEventManager eventManager = new AddProductWindowEventManager(this);
				
	public AddProductWindow() {

		columsName = new ArrayList<String>(); // @jve:decl-index=0:
		columsName.add("Codigo");
		columsName.add("Descripcion");
		columsName.add("Stock");
		columsName.add("Precio");
	}

	/**
	 * This method initializes jPanelAddProduct
	 * 
	 * @return javax.swing.JPanel
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel getJPanelAddProduct() {
		if (jPanelAddProduct == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 2;
			gridBagConstraints31.gridy = 0;
			jLabelStock = new JLabel();
			jLabelStock.setText("Existencia");
			jLabelStock.setFont(new Font("Dialog", Font.BOLD, 18));
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.BOTH;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.gridx = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 0;
			jLabelProductId = new JLabel();
			jLabelProductId.setText("Codigo de Producto");
			jLabelProductId.setFont(new Font("Dialog", Font.BOLD, 18));
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.BOTH;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.weighty = 1.0;
			gridBagConstraints4.gridwidth = 4;
			gridBagConstraints4.ipadx = 0;
			gridBagConstraints4.gridheight = 1;
			gridBagConstraints4.ipady = 0;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.gridy = 0;
			jLabelPrice = new JLabel();
			jLabelPrice.setText("Precio Unitario");
			jLabelPrice.setFont(new Font("Dialog", Font.BOLD, 18));
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 0;
			jLabelDesc = new JLabel();
			jLabelDesc.setText("Descripcion");
			jLabelDesc.setFont(new Font("Dialog", Font.BOLD, 18));
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 1;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.gridx = 3;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 1;
			jPanelAddProduct = new JPanel();
			jPanelAddProduct.setLayout(new GridBagLayout());
			jPanelAddProduct.setSize(new Dimension(649, 201));
			jPanelAddProduct.add(getJTextFieldDesc(), gridBagConstraints);
			jPanelAddProduct.add(getJTextFieldPrice(), gridBagConstraints1);
			jPanelAddProduct.add(jLabelDesc, gridBagConstraints2);
			jPanelAddProduct.add(jLabelPrice, gridBagConstraints3);
			jPanelAddProduct.add(getJScrollPaneProductList(),
					gridBagConstraints4);
			jPanelAddProduct.add(jLabelProductId, gridBagConstraints6);
			jPanelAddProduct.add(getJTextFieldStock(), gridBagConstraints21);
			jPanelAddProduct.add(jLabelStock, gridBagConstraints31);
			jPanelAddProduct.add(getJTextFieldProdId(), gridBagConstraints11);
		}
		enableAddingProduct(false);
		return jPanelAddProduct;
	}

	/**
	 * This method initializes jTextFieldDesc
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldDesc() {
		
		if (jTextFieldDesc == null) {
			jTextFieldDesc = new JTextField();
			jTextFieldDesc.setFont(new Font("Dialog", Font.PLAIN, 18));
			
			jTextFieldDesc.addKeyListener(new KeyListener() {

				public void keyTyped(KeyEvent e) {}

				public void keyReleased(KeyEvent e) {
			        eventManager.executeWhenLookingForProductByDescription();
				}

				public void keyPressed(KeyEvent e) {

				}
			});

		}
		return jTextFieldDesc;
	}

	/**
	 * This method initializes jTextFieldPrice
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldPrice() {
		if (jTextFieldPrice == null) {
			jTextFieldPrice = new JTextField();
			jTextFieldPrice.setFont(new Font("Dialog", Font.PLAIN, 18));

			jTextFieldPrice.addKeyListener(new KeyListener() {

			
				public void keyTyped(KeyEvent e) {
					if(e.getKeyChar() == KeyEvent.VK_ENTER){
						int n = JOptionPane.showOptionDialog(getJPanelAddProduct(),
								"Esta seguro que quiere agregar este articulo? ",
								null,
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, null, null);
						
						if(n == JOptionPane.YES_OPTION){
							Product product = new Product(jTextFieldProdId.getText(),new Float(jTextFieldPrice.getText()), jTextFieldDesc.getText());
							try{
								ar.com.pos.Catalog catalog = new ar.com.pos.Catalog(ar.com.pos.db.DBConnection$.MODULE$);
								catalog.save(product);
							}catch(TerminalException ex){
								Log.error(ex.getMessage());
								return;
							}
						}
					}
				}

			
				public void keyReleased(KeyEvent e) {}

			
				public void keyPressed(KeyEvent e) {}
			});
		}

		return jTextFieldPrice;
	}

	/**
	 * This method initializes jScrollPaneProductList
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPaneProductList() {
		if (jScrollPaneProductList == null) {
			jScrollPaneProductList = new JScrollPane();
			jScrollPaneProductList.setViewportView(getJTableProductList());
		}
		return jScrollPaneProductList;
	}

	/**
	 * This method initializes jTableProductList
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJTableProductList() {

		if (jTableProductList == null) {
			jTableProductList = new JTable();
			jTableProductList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTableProductList.setRowHeight(40);
			jTableProductList.setFont(new Font("Dialog", Font.PLAIN, 18));
			tableModel = new ProductTableModel();

			// Adding columns.
			for (String colum : columsName) {
				tableModel.addColumn(colum);
			}

            List<Product> allProducts = ar.com.pos.db.DBConnection.getAllProducts();

            ar.com.pos.ui.View view = new View();
            view.addProductsToTheFollowingTable(tableModel, allProducts);


			ListSelectionListener listener = new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent e) {
                    eventManager.executeWhenChangingSelectionInProductWindow(e);
				}
			};
			jTableProductList.getSelectionModel().addListSelectionListener(listener);

		}

		jTableProductList.setModel(tableModel);
		return jTableProductList;
	}

	/**
	 * This method initializes jTextFieldStock
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldStock() {
		if (jTextFieldStock == null) {
			jTextFieldStock = new JTextField();
		}
		return jTextFieldStock;
	}

	/**
	 * This method initializes jTextFieldProdId	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextFieldProdId() {
		if (jTextFieldProdId == null) {
			jTextFieldProdId = new JTextField("123456");
			jTextFieldProdId.addKeyListener(new KeyListener() {

				public void keyTyped(KeyEvent e) {}

				public void keyReleased(KeyEvent e) {
                    eventManager.executeWhenLookingForProductById();
				}

				public void keyPressed(KeyEvent e) {}
			});
		}
		return jTextFieldProdId;
	}

	public void enableAddingProduct(boolean value){
		getJTextFieldStock().setEditable(value);
		getJTextFieldPrice().setEditable(value);
	}

    public String getProductDescription(){
        return jTextFieldDesc.getText();
    }

    public String getProductId(){
        return jTextFieldProdId.getText();
    }

    public ProductTableModel getTableModel(){
        return tableModel;
    }

    public JTable getTableProductList(){
        return jTableProductList;
    }

    public void writeInFieldProductId(String value){
        jTextFieldProdId.setText(value);
    }

    public void writeInFieldProductDescription(String value){
        jTextFieldDesc.setText(value);
    }
    public void writeInFieldProductStock(String value){
        jTextFieldStock.setText(value);
    }

}
