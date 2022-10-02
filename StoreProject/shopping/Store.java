package shopping;

import java.util.ArrayList;
import java.util.Random;

public class Store{
	public ArrayList<Item> inventory; // List of items sold at the store
	public String name, address, phoneNumber, postalCode; // Basic information

	// Simple store constructor to initialize class
	public Store(String name, String address, String postalCode, String phoneNumber){
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.postalCode = postalCode;
		this.inventory = new ArrayList<Item>();
	}

	// Method to display all the products sold at the store
	public void displayInventory(){
		System.out.println("--------------------------------------------------");

		for (int i = 0; i < inventory.size(); ++i){
			System.out.println("|");
			System.out.print("| Name: " + inventory.get(i).name);
			System.out.print("| Stock: " + inventory.get(i).stock);
			System.out.print("| Price: " + inventory.get(i).price);
			System.out.println("|");
		}

		System.out.println("--------------------------------------------------");
	}

	// Method to add items to the store
	public void addItem(String name, double price){
		Random rand = new Random();
		inventory.add(new Item(name, rand.nextInt(50) + 1, price)); // Add random stock to item between 1-50
	}
}
