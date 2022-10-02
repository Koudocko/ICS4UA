package shopping;

// Simple plain data structure to store item metadata
public class Item{
	public String name; // Name of item
	public int stock; // Stock of item
	public double price; // Price of item

	// Constructor to initialize item
	public Item(String name, int stock, double price){
		this.name = name;
		this.stock = stock;
		this.price = price;
	}

	// Copy constructor to clone an item's data
	public Item(Item clone){
		this.name = clone.name;
		this.stock = clone.stock;
		this.price = clone.price;
	}
}
