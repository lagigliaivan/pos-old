package ar.com.terminal;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import ar.com.pos.db.DBConnection$;
import ar.com.pos.db.dto.Product;


public class SalesWindow {

	private JPanel jPanelMain = new JPanel();
	private JTextField productIdTextField = null;
	private JLabel jLabelProductDesc = null;
	private JScrollPane jScrollPaneProducts = null;
	private JTable jTableProducts = null;
	private ProductTableModel tableModel = new ProductTableModel();
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jTextFieldProductDesc = null;
	private JTextField jTextFieldPrice = null;
	private JTextField jTextFieldSubTotal = new JTextField();
	private JLabel jLabelSpace = null;
	private Float subtotal = 0.0F;
	private JButton jButtonSale = null;
	private List <String> columsName = new ArrayList <String>();
	private Map<Product, Integer> productsToBeSold = new HashMap<Product, Integer>();  //  @jve:decl-index=0:
    private ar.com.pos.Catalog catalog = new ar.com.pos.Catalog(ar.com.pos.db.DBConnection$.MODULE$);

	public SalesWindow(){
		columsName.add("Codigo");
		columsName.add("Descripcion");
		columsName.add("Precio Unitario");
	}
	
	/**
	 * This method initializes jPanelMain
	 * 
	 * @return javax.swing.JPanel
	 */
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel getJPanelMain() {

        GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
        gridBagConstraints12.gridx = 2;
        gridBagConstraints12.fill = GridBagConstraints.NONE;
        gridBagConstraints12.gridy = 6;
        GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
        gridBagConstraints8.gridx = 1;
        gridBagConstraints8.gridy = 2;
        jLabelSpace = new JLabel();
        jLabelSpace.setText("  ");
        GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
        gridBagConstraints3.fill = GridBagConstraints.BOTH;
        gridBagConstraints3.gridy = 1;
        gridBagConstraints3.weightx = 1.0;
        gridBagConstraints3.gridx = 2;
        GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.fill = GridBagConstraints.BOTH;
        gridBagConstraints2.gridy = 1;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.gridx = 1;
        GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
        gridBagConstraints11.fill = GridBagConstraints.BOTH;
        gridBagConstraints11.gridy = 1;
        gridBagConstraints11.weightx = 1.0;
        gridBagConstraints11.gridx = 0;
        GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
        gridBagConstraints1.gridx = 2;
        gridBagConstraints1.gridy = 0;
        jLabel1 = new JLabel();
        jLabel1.setText("Sub Total");
        jLabel1.setFont(new Font("Dialog", Font.BOLD, 18));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jLabel = new JLabel();
        jLabel.setText("Precio Unitario");
        jLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
        gridBagConstraints121.anchor = GridBagConstraints.CENTER;
        gridBagConstraints121.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints121.gridheight = 1;
        gridBagConstraints121.gridwidth = 3;
        gridBagConstraints121.gridx = 0;
        gridBagConstraints121.gridy = 4;
        gridBagConstraints121.weightx = 1.0;
        gridBagConstraints121.weighty = 1.0;
        gridBagConstraints121.ipadx = 0;
        gridBagConstraints121.fill = GridBagConstraints.BOTH;
        GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
        gridBagConstraints111.gridx = 0;
        gridBagConstraints111.gridy = 0;
        jLabelProductDesc = new JLabel();
        jLabelProductDesc.setText("Total de Items");
        jLabelProductDesc.setFont(new Font("Dialog", Font.BOLD, 18));
        GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
        gridBagConstraints13.anchor = GridBagConstraints.CENTER;
        gridBagConstraints13.insets = new Insets(11, 35, 12, 35);
        gridBagConstraints13.gridwidth = 1;
        gridBagConstraints13.gridx = 1;
        gridBagConstraints13.gridy = 6;
        gridBagConstraints13.weightx = 1.0;
        gridBagConstraints13.fill = GridBagConstraints.BOTH;

        jPanelMain.setLayout(new GridBagLayout());
        jPanelMain.setSize(new Dimension(1282, 1036));
        jPanelMain.add(getProductId(), gridBagConstraints13);
        jPanelMain.add(jLabelProductDesc, gridBagConstraints111);
        jPanelMain.add(getJScrollPaneProducts(), gridBagConstraints121);
        jPanelMain.add(jLabel, gridBagConstraints);
        jPanelMain.add(jLabel1, gridBagConstraints1);
        jPanelMain.add(getJTextFieldProductDesc(), gridBagConstraints11);
        jPanelMain.add(getJTextFieldPrice(), gridBagConstraints2);
        jPanelMain.add(getJTextFieldSubTotal(), gridBagConstraints3);
        jPanelMain.add(jLabelSpace, gridBagConstraints8);
        jPanelMain.add(getJButtonSale(), gridBagConstraints12);

		return jPanelMain;
	}

	/**
	 * This method initializes productId
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getProductId() {
		
		if (productIdTextField == null) {
			productIdTextField = new JTextField();
			productIdTextField.setFont(new Font("Dialog", Font.BOLD, 24));
			productIdTextField.setHorizontalAlignment(JTextField.CENTER);
			productIdTextField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent evt) {
					
					if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					
						ar.com.pos.db.dto.Product product = catalog.getProduct(productIdTextField.getText());
						
						jTextFieldProductDesc.setText(product.description());
						jTextFieldPrice.setText(Float.toString(product.price()));
						subtotal += product.price();
						
						List<Product> products = new ArrayList<Product>();
						products.add(product);
						
						ar.com.pos.ui.View view = new ar.com.pos.ui.View();
						view.addProductsToTheFollowingTable(getTableModel(), products);
												
						jTableProducts.setModel(getTableModel());
						jTextFieldSubTotal.setText(Float.toString(subtotal));
						productIdTextField.setText(null);
						
						Integer amountAlreadySold = 0;
						if(productsToBeSold.containsKey(product)){
							amountAlreadySold = productsToBeSold.get(product);
						}
						productsToBeSold.put(product, amountAlreadySold + new Integer(1));
					}
				}
			});
		}
		return productIdTextField;
	}

	/**
	 * This method initializes jScrollPaneProducts
	 * 
	 * @return javax.swing.JScrollPane
	 */
	public JScrollPane getJScrollPaneProducts() {
		if (jScrollPaneProducts == null) {
			jScrollPaneProducts = new JScrollPane();
			jScrollPaneProducts
					.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jScrollPaneProducts.setBorder(null);
			jScrollPaneProducts
					.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			jScrollPaneProducts.setViewportView(getJTableProducts());
			jScrollPaneProducts.setPreferredSize(new Dimension(300, 33));
		}
		return jScrollPaneProducts;
	}

	/**
	 * This method initializes tableModel
	 * 
	 * @return javax.swing.table.DefaultTableModel
	 */
	private ProductTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * This method initializes jTableProducts
	 * 
	 * @return javax.swing.JTable
	 */
	public JTable getJTableProducts() {

		if (jTableProducts == null) {
			jTableProducts = new JTable(getTableModel());
			jTableProducts.setPreferredSize(new Dimension(900, 900));
			jTableProducts.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			jTableProducts.setRowHeight(35);
			jTableProducts.setShowGrid(true);
			jTableProducts
					.setComponentOrientation(ComponentOrientation.UNKNOWN);
			jTableProducts.setFont(new Font("Dialog", Font.BOLD, 14));

			tableModel = new ProductTableModel();

			// Adding columns.
			for(String colum : columsName){
				tableModel.addColumn(colum);
			}
		}

		jTableProducts.setModel(tableModel);
		return jTableProducts;
	}

	/**
	 * This method initializes jTextFieldProductDesc
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldProductDesc() {
		if (jTextFieldProductDesc == null) {
			jTextFieldProductDesc = new JTextField();
			jTextFieldProductDesc.setFont(new Font("Dialog", Font.PLAIN, 18));
		}
		return jTextFieldProductDesc;
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
		}
		return jTextFieldPrice;
	}

	/**
	 * This method initializes jTextFieldSubTotal
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJTextFieldSubTotal() {

		jTextFieldSubTotal.setFont(new Font("Dialog", Font.PLAIN, 18));
		return jTextFieldSubTotal;
	}
	
	/**
	 * This method initializes jButtonSale	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButtonSale() {
		if (jButtonSale == null) {
			jButtonSale = new JButton();
			jButtonSale.setText("Vender");
			jButtonSale.setFont(new Font("Dialog", Font.BOLD, 18));
			jButtonSale.addActionListener(new ActionListener() {
				
				
				public void actionPerformed(ActionEvent e) {

					if(!jTextFieldSubTotal.getText().isEmpty()){
						Float totalAmount = new Float(jTextFieldSubTotal.getText());
						catalog.sell(new Date(), productsToBeSold, totalAmount);
						clearPreviousSaleData();
					}
				}
			});
		}
		return jButtonSale;
	}
	
	private void clearPreviousSaleData(){
		subtotal = 0.0F;
		getTableModel().clear();
		jTextFieldProductDesc.setText("");
		jTextFieldPrice.setText(Double.toString(0D));
		jTextFieldSubTotal.setText(Float.toString(subtotal));
		productsToBeSold.clear();
	}
}