import java.util.*;

public class ShoppingCart {
	
	private ArrayList<Product> items;
	
	private static Scanner input;
	
	public ShoppingCart() {
		items = new ArrayList<Product>();
		input = new Scanner(System.in);
	}
	
	public double getBalance() {
		double balance = 0.00;
		for (Iterator i = items.iterator(); i.hasNext();){
			Product item = (Product)i.next();
			balance += item.getPrice();
		}
		return balance;
	}
	
	public void addItem(Product item) {
		items.add(item);
	}
	
	public void removeItem(Product item)
			throws ProductNotFoundException {
		if (!items.remove(item)) {
			throw new ProductNotFoundException();}
	}
	
	public int getItemCount() {
		return items.size();
	}
	
	public void empty() {
		items.clear();
	}
	
	public String toString()		// displays the item name and Price 
	{
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < items.size(); i++)
		{
			result.append("Name: " + items.get(i).getTitle() + "\tPrice: " + items.get(i).getPrice() + "\n");
		}
		return result.toString();
	}
	
	public double priceValidation()		// validates the price of the item, will check if it is either negative and/or incorrect value type
	{
		double d = 0.00;
		boolean done = false;
		while(!done)					// loops until finished
		{
			try
			{
				d = input.nextDouble();
				done = true;
				if(d < 0.00)				// checks if the price is negative
				{
					System.out.println("The price cannot be negative");
					done = false;
				}
			}
			catch(Exception e)		// catches invalid input type
			{
				System.out.println("Error, Try Again");
				input.next();
			}
		
		}
		
		return d;				// returns validated price of the item
	}
	
	public boolean addItemValidation()		// validates if a customer wants to add an item to the cart
	{										// checks for incorrect value type and only accepts 'yes', 'y', 'no', and 'n'
		boolean done = false;
		String choice;
		boolean addItem = false;
		
		while(!done)
		{
		try
		{
			System.out.println("Would you like to add an item to the cart? (yes, y, no, n)");
			choice = input.next();
			choice = choice.toLowerCase();											//changes input to lowercase to avoid case sensitivity
			if(choice.equals("yes") || choice.equals("y"))
			{
				addItem = true;														// if addItem is true then program will move on to asking customer for the item info
				done = true;
			}
			else if(choice.equals("no") || choice.equals("n"))
			{
				addItem = false;													// if false, the program will move on to next question
				done = true;
			}
			else
			{
				System.out.println("Try again");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error, Try again");
			input.next();
		}
		}
		
		return addItem;									// returns boolean value, true if customer wants to add an item
	}
	
	public static void main(String[] args)
	{
		ShoppingCart cart = new ShoppingCart();			// creates an object of Shopping Cart
		
		String choice;
		boolean addItem = false;
		boolean done = false;
		
		System.out.println("The cart is currently empty.");
		boolean repeat = false;
		do 												// will repeat the process if user enter yes
		{
			
			addItem = cart.addItemValidation();
		
		if(addItem == true)								// if addItem is true, proceeds to ask for item name and price
		{
			String itemName;
			double itemPrice;
			System.out.println("Enter the name:");
			itemName = input.next();
			
			System.out.println("Enter the price of the product:");
			itemPrice = cart.priceValidation();						//validates price of the item in priceValidation()
			
			cart.addItem(new Product(itemName, itemPrice));			//once everything is validated, item is added to the cart
			
			System.out.println("Current items in the cart\n");		// displays current items, number of items and current balance of the cart
			System.out.println(cart);
			System.out.println("Number of items in the cart:\t" + cart.getItemCount());
			System.out.println("Current balance of the cart:\t" + cart.getBalance());
		}
		else														// else no item was added and program ends
		{
			System.out.println("No item was added to the cart");
			return;
		}
		
		
		while(!done)												// input validation if user wants to add another item to the cart
		{
		try
		{
			System.out.println("Would you like to add an item?");
			choice = input.next();
			choice = choice.toLowerCase();
			if(choice.equals("yes") || choice.equals("y"))
			{
				repeat = true;
				done = true;
			}
			else if(choice.equals("no") || choice.equals("n"))
			{
				repeat = false;
				done = true;
			}
			else
			{
				System.out.println("Try again");
			}
		}
		catch(Exception e)
		{
			System.out.println("Error, Try Again");
			input.next();
		}
		}
		
		
		}while(repeat  == true);
																// displays final information of the cart
		System.out.println("Final items in the cart\n");
		System.out.println(cart);
		System.out.println("Final number of items in the cart:\t" + cart.getItemCount());
		System.out.println("Final balance of the cart:\t" + cart.getBalance());
		
	}
}

