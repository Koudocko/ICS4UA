package shopping;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import shopping.Item;

public class Customer{
	public ArrayList<Item> itemsBought;
	public String name;
	public double balance;
	public ArrayList<Integer> coupons;

	public Customer(String name){
		Random rand = new Random();

		this.name = name;
		this.balance = rand.nextInt(1000) + 1;
		this.itemsBought = new ArrayList<Item>();

		int numCoupons = rand.nextInt(6);
		this.coupons = new ArrayList<Integer>();
		for (int i = 0; i < numCoupons; ++i){
			this.coupons.add(rand.nextInt(50) + 1);
		}

		System.out.println("---==={ Welcome to DistroMall, " + this.name + " }===---\n");
		System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(this.balance));
		System.out.println("Coupons available: " + this.coupons.size());
		for (int i = 0; i < this.coupons.size(); ++i){
			System.out.println("\tCoupon " + (i + 1) + " - " + this.coupons.get(i) + "% off");
		}
	}

	public void buyAt(Store store){
		Scanner stdinHandle = new Scanner(System.in);

		System.out.println();
		System.out.println("---==={ Availble Items in " + store.name + " }===---");

		for (int i = 0; i < store.inventory.size(); ++i){
			Item currentStore = store.inventory.get(i);
			System.out.println("Name: " + currentStore.name);
			System.out.println("Stock: " + currentStore.stock);
			System.out.println("Price: " + currentStore.price);
			System.out.println("--------------------");
		}
		System.out.println();

		System.out.print("Which product would you like to buy: ");

		String input = stdinHandle.nextLine();

		int idx = -1;
		for (int i = 0; i < store.inventory.size(); ++i){
			if (input.toLowerCase().contains(store.inventory.get(i).name.toLowerCase())){
				idx = i;
				break;
			}
		}

		if (idx >= 0){
			Item cartItem = new Item(store.inventory.get(idx));

			System.out.print("How many would you like to buy: ");

			for (String word : stdinHandle.nextLine().split(" ")){
				try{
					cartItem.stock = Integer.parseInt(word);
					store.inventory.get(idx).stock -= cartItem.stock;
					break;
				}
				catch (NumberFormatException e){}
			}
			
			System.out.println("Added to cart:");
			System.out.println("=> Name: " + cartItem.name);
			System.out.println("=> Stock: " + cartItem.stock);
			System.out.println("=> Price: " + (cartItem.price * cartItem.stock));
		}
		else{
			System.out.println("The item you are looking for does not exist!");
		}

		try{ Thread.sleep(1500); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}

	public void printReceipt(){

	}
}
