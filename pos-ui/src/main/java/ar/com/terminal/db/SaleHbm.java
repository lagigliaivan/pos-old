package ar.com.terminal.db;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SaleHbm {

	private Long idsale;
	private Long idproduct;
	private String description;
	private Date date;
	private Set<ProductHbm> products = new HashSet<ProductHbm>();
	private Float totalAmount;
	
	public Set<ProductHbm> getProducts() {
		return products;
	}
	public void setProducts(Set<ProductHbm> products) {
		this.products = products;
	}
	public Long getIdsale() {
		return idsale;
	}
	public void setIdsale(Long idsale) {
		this.idsale = idsale;
	}
	public long getIdproduct() {
		return idproduct;
	}
	public void setIdproduct(long idproduct) {
		this.idproduct = idproduct;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}
}
