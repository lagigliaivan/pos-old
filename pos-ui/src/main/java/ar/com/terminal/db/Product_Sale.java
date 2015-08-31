package ar.com.terminal.db;

public class Product_Sale {
 	private Long idproduct = null;
   	private Long idsale = null; 
    private Integer productamount = 1;
	private ProductSaleKey productSaleKey;
	
	public ProductSaleKey getProductSaleKey() {
		return productSaleKey;
	}
	public void setProductSaleKey(ProductSaleKey productSaleKey) {
		this.productSaleKey = productSaleKey;
	}
	public Long getidproduct() {
		return idproduct;
	}
	public void setidproduct(Long idproduct) {
		this.idproduct = idproduct;
	}
	public Long getidsale() {
		return idsale;
	}
	public void setidsale(Long idsale) {
		this.idsale = idsale;
	}
	public Integer getproductamount() {
		return productamount;
	}
	public void setproductamount(Integer productamount) {
		this.productamount = productamount;
	}
}
