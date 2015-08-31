package ar.com.terminal.db.dto;

import java.security.InvalidParameterException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemTest {

	Logger logger = LoggerFactory.getLogger(ItemTest.class);
	
	@Test
	public void productCanBeCreatedWhenParametersAreOk(){
		String id = "1";
		Float price = 0.0F;
		String desc = "producto1";
		
		Item item = new Item(id, price, desc);
		
		Assert.assertEquals(id, item.getId());
		Assert.assertEquals(price, item.getPrice());
		Assert.assertEquals(desc, item.getDescription());
				
		item.setDescription("first product");
		
	}
	
	@Test
	public void productCanNotBeCreatedWithNegativeOrNullPrice(){
		String id = "1";
		String desc = "producto1";
			
		try{
			Item item = new Item(id, null, desc);
			item = new Item(id, -1F, desc);
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
			Item item = new Item(id, price, null);
			item = new Item(id, -1F, "");
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
			Item item = new Item(null, price, desc);
			item = new Item("", price, desc);
		}catch(InvalidParameterException ex){
			logger.info("Product could not be created with a negative or null id");
			return;
		}	
		
		Assert.fail();
		
	}
	
}
