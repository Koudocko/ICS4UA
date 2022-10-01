package shopping;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
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
		this.balance = rand.nextInt(3000) + 1;
		this.cart = new ArrayList<Item>();

		int numCoupons = rand.nextInt(6);
		this.coupons = new ArrayList<Integer>();
		for (int i = 0; i < numCoupons; ++i){
			this.coupons.add(rand.nextInt(50) + 1);
		}
	}

	public void storeMenu(Store store){
		Scanner stdinHandle = new Scanner(System.in);

		int choice = -1;
		while (choice != 0){
			printDetails();
			System.out.println();
			System.out.println("---==={ Actions at " + store.name + " }===---");

			System.out.println("0 - Checkout");
			System.out.println("--------------------");
			System.out.println("1 - Buy item");
			System.out.println("--------------------");
			System.out.println("2 - Sell item");
			System.out.println("--------------------");

			System.out.print("What would you like to do (input an option): ");

			if (stdinHandle.hasNextInt()){
				choice = stdinHandle.nextInt();
			}
			else{
				stdinHandle.nextLine();
			}
			System.out.println();

			switch (choice){
				case 0:
					printReceipt(store);
					break;
				case 1:
					buyItem(store);
					break;
				case 2:
					sellItem(store);
					break;
			}
		}
	}

	public void sellItem(Store store){
		if (!this.cart.isEmpty()){
			Scanner stdinHandle = new Scanner(System.in);

			System.out.println();
			System.out.print("Which product would you like to sell: ");

			String input = stdinHandle.nextLine();

			int cartIdx = -1;
			for (int i = 0; i < this.cart.size(); ++i){
				if (input.toLowerCase().contains(this.cart.get(i).name.toLowerCase())){
					cartIdx = i;
					break;
				}
			}

			if (cartIdx >= 0){
				Item cartItem = this.cart.get(cartIdx);

				System.out.print("How many would you like to sell: ");

				for (String word : stdinHandle.nextLine().split(" ")){
					try{
						int deduction = Integer.parseInt(word);
						int itemAmount = cartItem.stock > deduction ? deduction : cartItem.stock;

						cartItem.stock -= itemAmount;
						int storeIdx = 0;
						for (int i = 0; i < store.inventory.size(); ++i){
							if (store.inventory.get(i).name == cartItem.name){
								storeIdx = i;
							}
						}
						store.inventory.get(storeIdx).stock += itemAmount;

						System.out.println("\nRemoved from cart:");
						System.out.println("=> " + cartItem.name + ":");
						System.out.println("\tQuantity: " + itemAmount);
						System.out.println("\tPrice Per: " + NumberFormat.getCurrencyInstance().format(cartItem.price));
						System.out.println("\tPrice Total: " + NumberFormat.getCurrencyInstance().format(cartItem.price * itemAmount));

						if (this.cart.get(cartIdx).stock <= 0)
							this.cart.remove(cartIdx);
						break;
					}
					catch (NumberFormatException e){}
				}
			}
			else{
				System.out.println("The item you are looking for does not exist!");
			}
		}
		else{
			System.out.println("Nothing in shopping cart to sell!");
		}
	}

	public void buyItem(Store store){
		Scanner stdinHandle = new Scanner(System.in);

		System.out.println();
		System.out.println("---==={ Available Items in " + store.name + " }===---");

		for (int i = 0; i < store.inventory.size(); ++i){
			Item currentItem = store.inventory.get(i);
			System.out.println("Name: " + currentItem.name);
			System.out.println("Stock: " + currentItem.stock);
			System.out.println("Price: " + NumberFormat.getCurrencyInstance().format(currentItem.price));
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
							System.out.println("\tQuantity: " + cartItem.stock);
							System.out.println("\tPrice Per: " + NumberFormat.getCurrencyInstance().format(cartItem.price));
							System.out.println("\tPrice Total: " + NumberFormat.getCurrencyInstance().format(cartItem.price * cartItem.stock));
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
				System.out.println("\tPrice Total: " + NumberFormat.getCurrencyInstance().format((item.price * item.stock)));
				cartTotal += item.price * item.stock;

				try{ Thread.sleep(500); }
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

	// void fmtText(String center, int width){
	// 	double offset = (width - center.length()) / 2.0 - 1;
	// 	System.out.print("|");
	// 	for (int i = 0; i < Math.ceil(offset); ++i)
	// 		System.out.print(" ");
	// 	System.out.print(center);
	// 	for (int i = 0; i < Math.floor(offset); ++i)
	// 		System.out.print(" ");
	// 	System.out.println("|");
	// }

	// void fmtText(String left, String right, int width){
	// 	int offset = width - right.length() - left.length() - 2;
	// 	System.out.print("|");
	// 	System.out.print(left);
	// 	for (int i = 0; i < offset; ++i)
	// 		System.out.print(" ");
	// 	System.out.print(right);
	// 	System.out.println("|");
	// }

	void fmtText(String left, String middle, String right, int width){
		int leftOffset = (int)Math.ceil((width - middle.length()) / 2.0 - left.length() - 1);
		int rightOffset = (int)Math.floor((width - middle.length()) / 2.0 - right.length() - 1);

		System.out.print("|" + left);

		for (int i = 0; i < leftOffset; ++i)
			System.out.print(" ");

		System.out.print(middle);

		for (int i = 0; i < rightOffset; ++i)
			System.out.print(" ");

		System.out.println(right + "|");
	}

	public void printReceipt(Store store){
		int width = 50;
		LocalDateTime currentTime = LocalDateTime.now();

		System.out.println("--------------------------------------------------");
		fmtText("", "", "", width);

		fmtText("", store.name, "", width);
		fmtText("", store.address, "", width);
		fmtText("", store.phoneNumber, "", width);
		fmtText("", store.postalCode, "", width);

		fmtText("", "", "", width);
		fmtText("", "************************************************", "", width);
		fmtText("", "", "", width);
		fmtText(
			DateTimeFormatter.ofPattern("yyyy/MM/dd").format(currentTime), 
			"",
			DateTimeFormatter.ofPattern("hh:mm a").format(currentTime),
			width
		);
		fmtText("", "", "", width);
		fmtText("", "************************************************", "", width);
		fmtText("", "", "", width);

		double subTotal = 0, totalTax = 0, coupon = 0, total = 0;
		for (Item item : this.cart){
			fmtText(item.name, Integer.toString(item.stock), NumberFormat.getCurrencyInstance().format(item.price), width);
			subTotal += item.price;
		}

		fmtText("", "", "", width);
		fmtText("", "************************************************", "", width);
		fmtText("", "", "", width);

		fmtText("SUBTOTAL:", "", NumberFormat.getCurrencyInstance().format(subTotal), width);
		totalTax = subTotal * 0.13;
		fmtText("TOTAL TAX (13%):", "", NumberFormat.getCurrencyInstance().format(totalTax), width);
		coupon = (subTotal + totalTax) * this.coupons.get(0) / 100;
		fmtText("COUPON (-" + this.coupons.get(0) + "%):", "", "-" + NumberFormat.getCurrencyInstance().format(coupon), width);
		total = (subTotal + totalTax - coupon);
		fmtText("TOTAL:", "", NumberFormat.getCurrencyInstance().format(total), width);

		fmtText("", "", "", width);
		System.out.println("--------------------------------------------------");

		this.balance -= total;
		this.cart.clear();
	}
}
