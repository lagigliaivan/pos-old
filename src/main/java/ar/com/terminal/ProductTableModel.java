package ar.com.terminal;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.terminal.db.dto.Product;


public class ProductTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public void clear(){
		
		if(this != null){
			for(int i=this.getRowCount();i>0;--i){
				this.removeRow(i-1);      
			}
		}
	}
	
	private int containsProduct(String prodId){
		
		int rowIndex = -1;
		
		for(int i=0; i < getRowCount(); i++){
			if (prodId == getValueAt(i, 0)) {
				rowIndex = i;
			}
		}
		return rowIndex;
	}
	
	public boolean removeProduct(Product ExistingProduct, int count){
		
		int rowIndex = containsProduct(ExistingProduct.getId());
		boolean ret = false;
		Integer amount = 0; 
		
		if(rowIndex >= 0){
			 amount = (Integer)getValueAt(rowIndex, 2);
			 if(amount >= count){
				 amount = amount - count;
				 setValueAt(amount, rowIndex, 2);
				 ret = true;
			 }
		}
		return ret;
	}
	
	public void addProduct(Product prod, int count){
		
		int rowIndex = containsProduct(prod.getId());
		Integer amount = 0; 
		
		if(rowIndex >= 0){
		
			 amount = (Integer)getValueAt(rowIndex, 2);
			 amount = amount + count;
			 setValueAt(amount, rowIndex, 2);
		
		}else{
			Vector <Object> row = new Vector <Object>();
			row.add(prod.getId());
			row.add(prod.getDescription());
			row.add(count);
			row.add(prod.getPrice());
			addRow(row);
		}
		
	}
}