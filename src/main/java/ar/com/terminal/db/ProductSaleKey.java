package ar.com.terminal.db;

import java.io.Serializable;

public class ProductSaleKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String idproduct = null;
	private Integer productamount = 1;
	private Long idsale = null;
	
	public Integer getProductamount() {
		return productamount;
	}

	public void setProductamount(Integer productamount) {
		this.productamount = productamount;
	}
 
	public String getidproduct() {
		return idproduct;
	}
	public void setidproduct(String idproduct) {
		this.idproduct = idproduct;
	}
	public Long getidsale() {
		return idsale;
	}
	public void setidsale(Long idsale) {
		this.idsale = idsale;
	}
}
