package ar.com.terminal;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.NoResultException;

import ar.com.terminal.db.dto.Item;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.terminal.db.Database;
import ar.com.terminal.db.ProductHbm;
import ar.com.terminal.db.SaleHbm;
import ar.com.terminal.db.SessionFactoryUtil;
import ar.com.terminal.db.dto.Sale;
import ar.com.terminal.exception.TerminalException;



public class DBConnection implements Database {
	
	private static DBConnection instance = new DBConnection();
	private Logger log = LoggerFactory.getLogger(DBConnection.class);
		
	private DBConnection(){}
	
	public static DBConnection getInstance(){
		return instance;
	}
	

	public List <Item> getAllProducts() {
		
		List <Item> items = new ArrayList<Item>();
		
		try {
			items.addAll(getItemsUsingTheFollowingQuery("from Product"));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e.getMessage());
		} catch (Exception e) {
			log.error("Error when getting products from DB", e.getMessage());
		}
		
		return items;
	}

	
	public List<Item> getProductsbyId(String id) {
		
		if ( (id == null) || (id.isEmpty())){
			throw new InvalidParameterException("Product ID cannot be null or empty");
		}

		List <Item> items = new ArrayList<Item>();
		
		try{
			items.addAll(getItemsUsingTheFollowingQuery("from Product u where u.id like " + id + "%" ));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e);
		} catch (Exception e) {
			log.error("Error when getting products from DB", e);
		}

		return items;
		
	}

	public List <Item> getProductsbyDescription(String productDesc) {
		
		if ( (productDesc == null) || (productDesc.isEmpty())){
			throw new InvalidParameterException("Product description cannot be null or empty");
		}

		List <Item> items = new ArrayList<Item>();
		
		try{
			items.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.description like %" + productDesc + "%" ));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e);
		} catch (Exception e) {
			log.error("Error when getting products from DB", e);
		}

		return items;
	}
	
	public Item getProductById (String id) {

		if ( (id == null) || (id.isEmpty())){
			throw new InvalidParameterException("Product ID cannot be null or empty");
		}

		List <Item> items = new ArrayList<Item>();
		
		try{
			items.addAll(getItemsUsingTheFollowingQuery("from ProductHbm u where u.idproduct = " + id));
		} catch (HibernateException e) {
			log.error("Error when getting products from DB", e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.error("Error when getting products from DB", e.getMessage());
			e.printStackTrace();
		}
		if (items.isEmpty()){
			throw new NoResultException("Product " + id + " was not found.");
		}
		
		return items.get(0);
	}
	
	public void save(Sale sale){
		Session session = null;
		
		try {
			session = SessionFactoryUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
		    Set <ProductHbm> products = new HashSet<ProductHbm>();
	        
		    for(Entry<Item, Integer> productAndStock : sale.getProducts().entrySet()){
		    	
		    	Item item = productAndStock.getKey();
		    	ProductHbm productHbm = getProductHbm(item);
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
	
	protected ProductHbm getProductHbm(Item item){

		ProductHbm productHbm = new ProductHbm();
    	productHbm.setidproduct(item.getId());
    	productHbm.setdescription(item.getDescription());
    	productHbm.setprice(item.getPrice());
    	
    	return productHbm;
	}
	
	
	public void save(Item item) throws TerminalException{
		
		Session session = null;
		
		try {
			session = SessionFactoryUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			session.save(item);
			session.flush();
			transaction.commit();
			session.close();
					
		} catch (HibernateException e) {
			throw new TerminalException(TerminalException.Errors.PRODUCT_SAVING.getMessage());
		} catch (Exception e) {
			throw new TerminalException(e.getMessage());
		}
		
	}

    @Override
    public void remove(Item item) {

    }

    protected List <Item> getItemsUsingTheFollowingQuery(String query) throws HibernateException, Exception{
		
		List <Item> existingItems = new ArrayList<Item>();
		
		Session session = SessionFactoryUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query hqlQuery = session.createQuery(query);
					
		List<ProductHbm> products = hqlQuery.list();

		for( ProductHbm product : products){	
			Item existingItem = new Item(product.getidproduct(), product.getprice(), product.getdescription());
			existingItems.add(existingItem);
		}
		
		session.getTransaction().commit();
		session.close();

		return existingItems;
	}

	
	public void addProduct(Item item, Integer amount) {
		// TODO Auto-generated method stub
		
	}

	
	public Integer getStock(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}