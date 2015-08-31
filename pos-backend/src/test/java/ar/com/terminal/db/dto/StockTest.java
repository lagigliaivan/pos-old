package ar.com.terminal.db.dto;

import ar.com.terminal.Catalog;
import ar.com.terminal.db.Database;
import org.junit.Assert;
import org.junit.Ignore;

import static org.mockito.Mockito.mock;

public class StockTest {

	@Ignore
	public void returnAmountOfProductCorrecltyWhenAddingOneByOne() {

        Database database = mock(Database.class);
        Catalog stock = new Catalog(database);
        Item product = new Item("1", 0.0F, "product1");
        stock.addItem(product);
        stock.addItem(product);
        stock.addItem(product);

        Integer amount = stock.getStock(product.getId());

        //when(database.);

        Assert.assertEquals(amount, new Integer(3));

    }

    @Ignore
	public void returnAmountOfProductCorrectlyWhenAddingAll(){
		
		Database database = mock(Database.class);
		ar.com.terminal.Catalog catalog = new ar.com.terminal.Catalog(database);
        Item product = new Item("1",0.0F, "product1");
		catalog.addItem(product, 3);
		Integer amount = catalog.getStock(product.getId());
		
		Assert.assertEquals(amount, new Integer(3));
	}
	
	@Ignore
	public void returnAmountOfProductCorrectlyWhenAddingThemMultipleTimes(){
		
		Database database = mock(Database.class);
		ar.com.terminal.Catalog catalog = new ar.com.terminal.Catalog(database);
		ar.com.terminal.db.dto.Item product = new ar.com.terminal.db.dto.Item("1",0.0F, "product1");
		catalog.addItem(product, 3);
		
		catalog.addItem(product);
		
		Integer amount = catalog.getStock(product.getId());
		
		Assert.assertEquals(amount, new Integer(4));
	}
	
}
