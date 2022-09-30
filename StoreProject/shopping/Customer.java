package shopping;

import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import shopping.Item;

public class Customer{
	public ArrayList<Item> cart;
	public String name;
	public double balance;
	public ArrayList<Integer> coupons;

	public Customer(String name){
		Random rand = new Random();

		this.name = name;
		this.balance = rand.nextInt(1000) + 1;
		this.cart = new ArrayList<Item>();

		int numCoupons = rand.nextInt(6);
		this.coupons = new ArrayList<Integer>();
		for (int i = 0; i < numCoupons; ++i){
			this.coupons.add(rand.nextInt(50) + 1);
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
			System.out.println("Price: " + NumberFormat.getCurrencyInstance().format(currentStore.price));
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

			if (cartItem.stock > 0){
				System.out.print("How many would you like to buy: ");

				for (String word : stdinHandle.nextLine().split(" ")){
					try{
						cartItem.stock = Integer.parseInt(word);
						if (store.inventory.get(idx).stock - cartItem.stock >= 0){
							store.inventory.get(idx).stock -= cartItem.stock;
							cart.add(cartItem);

							System.out.println("\nAdded to cart:");
							System.out.println("=> " + cartItem.name + ":");
							System.out.println("\tStock: " + cartItem.stock);
							System.out.println("\tPrice: " + NumberFormat.getCurrencyInstance().format(cartItem.price * cartItem.stock));
						}
						else{
							System.out.println("Not enough stock for purchase!");
						}
						break;
					}
					catch (NumberFormatException e){}
				}
			}
			else{
				System.out.println("Product is out of stock!");
			}
		}
		else{
			System.out.println("The item you are looking for does not exist!");
		}
		System.out.println();

		try{ Thread.sleep(1500); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}

	public void printDetails(){
		System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(this.balance));

		System.out.println("Coupons available: " + this.coupons.size());
		for (int i = 0; i < this.coupons.size(); ++i){
			System.out.println("\tCoupon " + (i + 1) + " - " + this.coupons.get(i) + "% off");
		}
		
		if (cart.size() > 0){
			double cartTotal = 0.0;

			System.out.println("Cart:");
			for (Item item : this.cart){
				System.out.println("=> " + item.name + ":");
				System.out.println("\tStock: " + item.stock);
				System.out.println("\tPrice Per: " + NumberFormat.getCurrencyInstance().format(item.price));
				System.out.println("\tPrice Tot: " + NumberFormat.getCurrencyInstance().format((item.price * item.stock)));
				cartTotal += item.price * item.stock;

				try{ Thread.sleep(1500); }
				catch (InterruptedException e){
					Thread.currentThread().interrupt();
				}
			}
			
			System.out.println("Cart Total: " + NumberFormat.getCurrencyInstance().format(cartTotal));
			System.out.println("Predicated Balance: " + NumberFormat.getCurrencyInstance().format((this.balance - cartTotal)));
		}
		else{
			System.out.println("Cart: empty");
		}

		try{ Thread.sleep(1500); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}

	public void printReceipt(){
			
	}
}
