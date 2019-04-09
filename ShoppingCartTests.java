import org.junit.Test;
import org.junit.Before;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class ShoppingCartTests extends TestCase {
    private ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    // When created, the cart has 0 items
    public void testZeroItemsUponCreation() {
        assertEquals(cart.getItemCount(), 0);
    }

    @Test
    // When a new product is added, the new balance must be the sum of the previous balance plus the cost of the new product
    public void testAddingValueOfNewProduct() {
        Product thing = new Product("test", 5);
        cart.addItem(thing);
        double balance = cart.getBalance();
        cart.addItem(thing);
        assertEquals(cart.getBalance(), thing.getPrice() * 2);
    }

    @Test
    // When an item is removed, the number of items must be decreased
    public void testDecrementItemCount() throws ProductNotFoundException {
        Product thing = new Product("test", 5);
        cart.addItem(thing);
        cart.addItem(thing);
        int itemCount = cart.getItemCount();
        cart.removeItem(thing);
        assertEquals(cart.getItemCount(), itemCount - 1);
    }
    
    @Test
    // When a new item is added, the number of items must be incremented
    public void testIncrementItemsUponAdd() 
    {
    	int itemCount = cart.getItemCount();
    	Product thing = new Product("test", 5);
    	
    	cart.addItem(thing);
    	
    	assertEquals(cart.getItemCount(), itemCount+1);
    }

    @Test
    // When a product not in the cart is removed, a ProductNotFoundException must be thrown
    public void testRemovingInvalidItem() {
        Product thing = new Product("test", 5);
        try {
            cart.removeItem(thing);
            fail();
        } catch(Exception e) {}
    }
}
