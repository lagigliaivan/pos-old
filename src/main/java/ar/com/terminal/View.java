package ar.com.terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ar.com.pos.db.dto.Product;

public class View {

	protected List <Vector <Object>> getProductsInRows(List<Product> products){
		
		List <Vector <Object>> result = new ArrayList<Vector<Object>>();
		
		for ( Product product : (List<Product>) products ) {
				Vector <Object> row = new Vector <Object>();
			    row.add(product.id());
			    row.add(product.description());
			    row.add(product.price());
			    result.add(row);
		}
		
		return result;
	}
	
	public void addProductsToTheFollowingTable(DefaultTableModel table, List <Product> products){
		
		for (Vector <Object> row : getProductsInRows(products)){
			table.addRow(row);
		}
	}
	
	public void addProductToTheFollowinTable(DefaultTableModel table, Product product){
		
		List <Product> products = new ArrayList<Product>();
		products.add(product);
		
		addProductsToTheFollowingTable(table, products);
	}
}
