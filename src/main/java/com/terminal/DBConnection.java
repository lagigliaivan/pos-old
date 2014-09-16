package com.terminal;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terminal.db.Database;
import com.terminal.db.ProductHbm;
import com.terminal.db.SaleHbm;
import com.terminal.db.SessionFactoryUtil;
import com.terminal.db.dto.Product;
import com.terminal.db.dto.Sale;
import com.terminal.exception.TerminalException;



public class DBConnection implements Database {
	
	private static DBConnection instance = new DBConnection();
	private Logger log = LoggerFactory.getLogger(DBConnection.class);
		
	private DBConnection(){}
	
	public static DBConnection getInstance(){
		return instance;
	}
	

	public List <Product> getAllProducts() {
		
		List <Product> products = new ArrayList<Product>();
		
		try {
			products.addAll(getItemsUsingTheFollowingQuery("from Product"));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e.getMessage());
		} catch (Exception e) {
			log.error("Error when getting products from DB", e.getMessage());
		}
		
		return products;
	}

	
	public List<Product> getProductsbyId(String id) {
		
		if ( (id == null) || (id.isEmpty())){
			throw new InvalidParameterException("Product ID cannot be null or empty");
		}

		List <Product> products = new ArrayList<Product>(); 
		
		try{
			products.addAll(getItemsUsingTheFollowingQuery("from Product u where u.id like " + id + "%" ));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e);
		} catch (Exception e) {
			log.error("Error when getting products from DB", e);
		}

		return products;
		
	}

	public List <Product> getProductsbyDescription(String productDesc) {
		
		if ( (productDesc == null) || (productDesc.isEmpty())){
			throw new InvalidParameterException("Product description cannot be null or empty");
		}

		List <Product> products = new ArrayList<Product>(); 
		
		try{
			products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.description like %" + productDesc + "%" ));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e);
		} catch (Exception e) {
			log.error("Error when getting products from DB", e);
		}

		return products;
	}
	
	public Product getProductById (String id) {

		if ( (id == null) || (id.isEmpty())){
			throw new InvalidParameterException("Product ID cannot be null or empty");
		}

		List <Product> products = new ArrayList<Product>(); 
		
		try{
			products.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.idproduct = " + id));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Error when getting products from DB", e.getMessage());
			e.printStackTrace();
		}
		if (products.isEmpty()){
			throw new NoResultException("Product " + id + " was not found.");
		}
		
		return products.get(0);
	}
	
	public void save(Sale sale){
		Session session = null;
		
		try {
			session = SessionFactoryUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
		    Set <ProductHbm> products = new HashSet<ProductHbm>();
	        
		    for(Entry<Product, Integer> productAndStock : sale.getProducts().entrySet()){
		    	
		    	Product product = productAndStock.getKey();
		    	ProductHbm productHbm = getProductHbm(product);
		    	productHbm.setstock(productAndStock.getValue());
	        	products.add(productHbm);
	       }

		   SaleHbm saleHbm = new SaleHbm();
	       saleHbm.setDate(sale.getDate());
	       saleHbm.setProducts(products);
	       saleHbm.setTotalAmount(sale.getTotalAmount());
	        
	       session.save(saleHbm);
	       session.flush();
	       transaction.commit();
	       session.close();
					
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected ProductHbm getProductHbm(Product product){

		ProductHbm productHbm = new ProductHbm();
    	productHbm.setidproduct(product.getId());
    	productHbm.setdescription(product.getDescription());
    	productHbm.setprice(product.getPrice());
    	
    	return productHbm;
	}
	
	@Override
	public void save(Product product) throws TerminalException{
		
		Session session = null;
		
		try {
			session = SessionFactoryUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			session.save(product);
			session.flush();
			transaction.commit();
			session.close();
					
		} catch (HibernateException e) {
			throw new TerminalException(TerminalException.Errors.PRODUCT_SAVING.getMessage());
		} catch (Exception e) {
			throw new TerminalException(e.getMessage());
		}
		
	}
	
	protected List <Product> getItemsUsingTheFollowingQuery(String query) throws HibernateException, Exception{
		
		List <Product> existingProducts = new ArrayList<Product>();
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query hqlQuery = session.createQuery(query);
					
		List<ProductHbm> products = hqlQuery.list();

		for( ProductHbm product : products){	
			Product existingProduct = new Product(product.getidproduct(), product.getprice(), product.getdescription());
			existingProducts.add(existingProduct);
		}
		
		session.getTransaction().commit();
		session.close();

		return existingProducts;
	}

	@Override
	public void addProduct(Product product, Integer amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getStock(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}