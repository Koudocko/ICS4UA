package shopping;

import java.util.ArrayList;
import java.util.Random;

public class Store{
	private ArrayList<Item>	inventory;
	public String name, address, phoneNumber, postalCode;

	public Store(String name, String address, String phoneNumber, String postalCode){
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.postalCode = postalCode;
		this.inventory = new ArrayList<Item>();
	}

	public void displayInventory(){
		System.out.println("--------------------------------------------------");

		for (int i = 0; i < inventory.size(); ++i){
			System.out.println("|");
			System.out.print("| Name: " + inventory.get(i).name);
			System.out.print("| Stock: " + inventory.get(i).stock);
			System.out.print("| Price: $" + inventory.get(i).price);
			System.out.println("|");
		}

		System.out.println("--------------------------------------------------");
	}

	public void addItem(String name, double price){
		Random rand = new Random();
		int salePercent = rand.nextBoolean() ? rand.nextInt(100) + 1 : 0;

		inventory.add(new Item(name, this.name, rand.nextInt(50) + 1, price, salePercent));
	}
}
