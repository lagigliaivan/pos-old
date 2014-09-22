package ar.com.terminal.db.dto;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.terminal.db.dto.Product;

public class ProductTest {

	Logger logger = LoggerFactory.getLogger(ProductTest.class);
	
	@Test
	public void productCanBeCreatedWhenParametersAreOk(){
		String id = "1";
		Float price = 0.0F;
		String desc = "producto1";
		
		Product product = new Product(id, price, desc);
		
		Assert.assertEquals(id, product.getId());
		Assert.assertEquals(price, product.getPrice());
		Assert.assertEquals(desc, product.getDescription());
				
		product.setDescription("first product");
		
	}
	
	@Test
	public void productCanNotBeCreatedWithNegativeOrNullPrice(){
		String id = "1";
		String desc = "producto1";
			
		try{
			Product product = new Product(id, null, desc);
			product = new Product(id, -1F, desc);
		}catch(InvalidParameterException ex){
			logger.info("Product could not be created with a negative or null price");
			return;
		}	
		
		Assert.fail();
		
	}
	
	@Test
	public void productCanNotBeCreatedWithEmptyOrNullName(){
		String id = "1";
		Float price = 1F;
		
		try{
			Product product = new Product(id, price, null);
			product = new Product(id, -1F, "");
		}catch(InvalidParameterException ex){
			logger.info("Product could not be created with a empty or null name");
			return;
		}	
		
		Assert.fail();
	}
	
	public void productCanNotBeCreatedWithEmptyOrNullId(){

		Float price = 1.0F;
		String desc = "producto1";
		
		try{
			Product product = new Product(null, price, desc);
			product = new Product("", price, desc);
		}catch(InvalidParameterException ex){
			logger.info("Product could not be created with a negative or null id");
			return;
		}	
		
		Assert.fail();
		
	}
	
}
