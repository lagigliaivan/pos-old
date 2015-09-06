package ar.com.terminal;

import ar.com.terminal.model.Product;

/**
 * Created by ivan on 30/08/15.
 */
public class NullProduct extends Product {

    public static final String name = "Product not found";
    public static final Float price = 0F;
    public static final String desc = "Product not available";


    public NullProduct() {
        super(NullProduct.name, NullProduct.price, NullProduct.desc);
    }
}
