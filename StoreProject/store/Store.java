package store;

import java.util.ArrayList;

class Item{
	public String name;
	public int stock, sale;
	public double price;
}

public class Store{
	private ArrayList<Item>	inventory;
	public String name, address, phoneNumber, postalCode;
	public int inventorySize;
	public double totalRevenue;
}
