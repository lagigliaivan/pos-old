package ar.com.terminal.db.dto;

import ar.com.terminal.model.Product;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.InvalidParameterException;

public class ProductDtoTest {

	Logger logger = LoggerFactory.getLogger(ProductDtoTest.class);
	
	@Test
	public void product_can_be_created_when_parameters_are_ok(){

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
	public void product_cannot_be_created_with_negative_or_null_price(){

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
	public void product_cannot_be_created_with_empty_or_null_name(){
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

    @Test
	public void product_cannot_be_created_with_empty_or_null_id(){

		Float price = 1.0F;
		String desc = "product_1";
		
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
