import org.junit.Assert;
import org.junit.Test;

import com.terminal.Catalog;
import com.terminal.db.Database;
import com.terminal.db.dto.Product;
import static org.mockito.Mockito.*;

public class StockTest {

	@Test
	public void returnAmountOfProductCorrecltyWhenAddingOneByOne(){
		
		Database database = mock(Database.class);
		Catalog stock = new Catalog(database);
		Product product = new Product("1",0.0F, "product1");
		stock.addProduct(product);
		stock.addProduct(product);
		stock.addProduct(product);
		
		Integer amount = stock.getStock(product.getId());
		
		//when(database.);
		
		Assert.assertEquals(amount, new Integer(3));
		
	}
	
	@Test
	public void returnAmountOfProductCorrectlyWhenAddingAll(){
		
		Database database = mock(Database.class);
		Catalog catalog = new Catalog(database);
		Product product = new Product("1",0.0F, "product1");
		catalog.addProduct(product, 3);
		Integer amount = catalog.getStock(product.getId());
		
		Assert.assertEquals(amount, new Integer(3));
	}
	
	@Test
	public void returnAmountOfProductCorrectlyWhenAddingThemMultipleTimes(){
		
		Database database = mock(Database.class);
		Catalog catalog = new Catalog(database);
		Product product = new Product("1",0.0F, "product1");
		catalog.addProduct(product, 3);
		
		catalog.addProduct(product);
		
		Integer amount = catalog.getStock(product.getId());
		
		Assert.assertEquals(amount, new Integer(4));
	}
	
}
