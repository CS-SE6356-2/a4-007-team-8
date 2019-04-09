import org.junit.Test;

import jdk.jfr.Timestamp;

import static org.junit.Assert.*;

public class ShoppingCartTests {
    @Test
    // When created, the cart has 0 items
    public void testZeroItemsUponCreation() {
        ShoppingCart cart;
        assertEquals(cart.getItemCount(), 0);
    }

    @Test
    // When empty, the cart has 0 items
    public void testZeroItemsWhenEmpty() {
        ShoppingCart cart;
        Product thing = new Product("test", 5);
        cart.addItem(thing);
        cart.empty();
        assertEquals(cart.getItemCount(), 0);
    }

    @Test
    // When a new item is added, the number of items must be incremented
    public void testIncrementItemsUponAdd() {
        ShoppingCart cart;
        int itemCount = cart.getItemCount();
        Product thing = new Product("test", 5);
        cart.addItem(thing);
        assertEquals(cart.getItemCount(), ++itemCount);
    }

    @Test
    // When a new product is added, the new balance must be the sum of the previous balance plus the cost of the new product
    public void testAddingValueOfNewProduct() {
        ShoppingCart cart;
        Product thing = new Product("test", 5);
        cart.addItem(thing);
        double balance = cart.getBalance();
        cart.addItem(thing);
        assertEquals(cart.getBalance(), 10);
    }

    @Test
    // When an item is removed, the number of items must be decreased
    public void testDecrementItemCount() {
        ShoppingCart cart;
        Product thing = new Product("test", 5);
        cart.addItem(thing);
        cart.addItem(thing);
        int itemCount = cart.getItemCount();
        cart.removeItem(thing);
        assertEquals(cart.getItemCount(), --itemCount);
    }

    @Test
    // When a product not in the cart is removed, a ProductNotFoundException must be thrown
    public void testRemovingInvalidItem() {
        ShoppingCart cart;
        Product thing = new Product("test", 5);
        try {
            cart.removeItem(thing);
            fail();
        } catch(ProductNotFoundException e) {}
    }
}