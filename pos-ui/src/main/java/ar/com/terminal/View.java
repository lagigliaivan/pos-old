package ar.com.terminal;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import ar.com.terminal.db.dto.Item;

public class View {

	protected List <Vector <Object>> getProductsInRows(List<Item> products){
		
		List <Vector <Object>> result = new ArrayList<Vector<Object>>();
		
		for ( Item product : (List<Item>) products ) {
				Vector <Object> row = new Vector <Object>();
			    row.add(product.getId());
			    row.add(product.getDescription());
			    row.add(product.getPrice());
			    result.add(row);
		}
		
		return result;
	}
	
	public void addProductsToTheFollowingTable(DefaultTableModel table, List <Item> products){
		
		for (Vector <Object> row : getProductsInRows(products)){
			table.addRow(row);
		}
	}
	
	public void addProductToTheFollowinTable(DefaultTableModel table, Item product){
		
		List <Item> products = new ArrayList<Item>();
		products.add(product);
		
		addProductsToTheFollowingTable(table, products);
	}
}
