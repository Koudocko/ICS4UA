package shopping;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

public class Customer{
	public ArrayList<Item> cart; // Items in the customer's cart
	public String name; // Name of the customer
	public double balance; // Balance/money of the customer
	public ArrayList<Integer> coupons; // Coupons the customer has available

	// Constructor to initialize a new customer
	public Customer(String name){
		Random rand = new Random();

		this.name = name;
		this.balance = rand.nextInt(3000) + 1; // Balance is a random integer between 1-3000
		this.cart = new ArrayList<Item>(); // Shopping cart is initially empty

		int numCoupons = rand.nextInt(6);
		this.coupons = new ArrayList<Integer>();
		// Number of coupons is a random integer between 0-5, each with a value of 1-50
		for (int i = 0; i < numCoupons; ++i){
			this.coupons.add(rand.nextInt(50) + 1);
		}
	}

	// Handle the customer's actions when at a store
	public void storeMenu(Store store){
		Scanner stdinHandle = new Scanner(System.in);

		int choice = -1, result = 1;
		while (result == 1 || choice < 0 || choice > 2){
			// Display the action menu
			printDetails();
			System.out.println("---==={ Actions at " + store.name + " }===---");

			System.out.println("0 - Checkout");
			System.out.println("--------------------");
			System.out.println("1 - Buy item");
			System.out.println("--------------------");
			System.out.println("2 - Return item");
			System.out.println("--------------------");

			System.out.print("What would you like to do (input an option): ");

			if (stdinHandle.hasNextInt()){
				choice = stdinHandle.nextInt();
			}
			else{
				stdinHandle.nextLine();
			}
			System.out.println();

			// Handle the choice the user made
			switch (choice){
				case 0:
					result = printReceipt(store); // Print the receipt if specifications are met
					break;
				case 1:
					buyItem(store); // Purchase an item at the store
					break;
				case 2:
					sellItem(store); // Return an item at the store
					break;
			}
		}
	}

	// Return an item at the store
	public void sellItem(Store store){
		// Run if items exist in the shopping cart
		if (!this.cart.isEmpty()){
			// Get the name of the item to return using natural language
			Scanner stdinHandle = new Scanner(System.in);

			System.out.println();
			System.out.print("Which product would you like to return: ");

			String input = stdinHandle.nextLine();

			int cartIdx = -1;
			for (int i = 0; i < this.cart.size(); ++i){
				if (input.toLowerCase().contains(this.cart.get(i).name.toLowerCase())){
					cartIdx = i;
					break;
				}
			}

			// Return the item if the input was valid
			if (cartIdx >= 0){
				Item cartItem = this.cart.get(cartIdx);

				System.out.print("How many would you like to return: ");

				// Get quantity of the item to return
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

		try{ Thread.sleep(1500); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}

		System.out.println();
	}

	// Purchase an item at the store
	public void buyItem(Store store){
		Scanner stdinHandle = new Scanner(System.in);

		// Display list of items the store sells
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

		// Obtain item choice with natural language
		int idx = -1;
		for (int i = 0; i < store.inventory.size(); ++i){
			if (input.toLowerCase().contains(store.inventory.get(i).name.toLowerCase())){
				idx = i;
				break;
			}
		}

		// If the input was valid, buy the item
		if (idx >= 0){
			Item cartItem = new Item(store.inventory.get(idx));

			// If item is not out of stock
			if (cartItem.stock > 0){
				System.out.print("How many would you like to buy: ");

				// Obtain the quantity of the item to buy
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

	// Print the detials of the customer
	public void printDetails(){
		System.out.println("---==={ " + this.name + "'s Details }===---");
		System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(this.balance)); // Customer's balance

		// All the coupons the customer owns
		System.out.println("Coupons available: " + this.coupons.size());
		for (int i = 0; i < this.coupons.size(); ++i){
			System.out.println("\tCoupon " + (i + 1) + " - " + this.coupons.get(i) + "% off");
		}
		
		// Everything added to the shopping cart
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
			String sign = this.balance - cartTotal >= 0 ? "" : "-";
			System.out.println("Predicted Balance: " + sign + NumberFormat.getCurrencyInstance().format((this.balance - cartTotal)));
		}
		else{
			System.out.println("Cart: empty");
		}
		System.out.println();

		try{ Thread.sleep(1500); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}

	// Pause to simulate the printing of a receipt
	void receiptDelay(){
		try{ Thread.sleep(250); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}

	// Function to format text on the screen, alligment of left, right, and center
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
		receiptDelay();
	}

	// Print out the customers receipt
	public int printReceipt(Store store){
		// Check to see if the shopping cart has items
		if (!this.cart.isEmpty()){
			int choice = -1, width = 50;

			LocalDateTime currentTime = LocalDateTime.now();
			Scanner stdinHandle = new Scanner(System.in);

			// Input a coupon to use if any exist
			if (!coupons.isEmpty()){
				while (choice < 1 || choice > this.coupons.size()){
					printDetails();
					System.out.print("Which coupon would you like to use: ");

					if (stdinHandle.hasNextInt()){
						choice = stdinHandle.nextInt();
					}
					else{
						stdinHandle.nextLine();
					}

					System.out.println();
				}
			}
			
			double subTotal = 0, totalTax = 0, coupon = 0, total = 0;
			for (Item item : this.cart)
				subTotal += item.price * item.stock;
			totalTax = subTotal * 0.13;
			if (choice > 0)
				coupon = (subTotal + totalTax) * this.coupons.get(choice - 1) / 100;
			total = (subTotal + totalTax - coupon);

			// If the customer has enough money, print the receipt and make the purchase
			if (total <= this.balance){
				System.out.println("--------------------------------------------------");
				receiptDelay();
				fmtText("", "", "", width);

				fmtText("", "[" + store.name + "]", "", width);
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

				fmtText("Name", "Quantity", "Price", width);
				for (Item item : this.cart)
					fmtText(item.name, Integer.toString(item.stock), NumberFormat.getCurrencyInstance().format(item.price), width);

				fmtText("", "", "", width);
				fmtText("", "************************************************", "", width);
				fmtText("", "", "", width);

				fmtText("SUBTOTAL:", "", NumberFormat.getCurrencyInstance().format(subTotal), width);
				fmtText("TOTAL TAX (13%):", "", NumberFormat.getCurrencyInstance().format(totalTax), width);
				if (choice > 0){
					fmtText("COUPON (-" + this.coupons.get(choice - 1) + "%):", "", "-" + NumberFormat.getCurrencyInstance().format(coupon), width);
					this.coupons.remove(choice - 1);
				}
				fmtText("TOTAL:", "", NumberFormat.getCurrencyInstance().format(total), width);

				fmtText("", "", "", width);
				fmtText("", "************************************************", "", width);
				fmtText("", "", "", width);

				String logo =               
							"   a8888b.\n"
					.concat("   d888888b.\n")
					.concat("   8P\"YP\"Y88\n")
					.concat("   8|o||o|88\n")
					.concat("   8'    .88\n")
					.concat("    8`._.' Y8.\n")
					.concat("    d/      `8b.\n")
					.concat("    .dP   .     Y8b.\n")
					.concat("   d8:'   \"   `::88b.\n")
					.concat("  d8\"           `Y88b\n")
					.concat(" :8P     '       :888\n")
					.concat("  8a.    :      _a88P\n")
					.concat("._/\"Yaa_ :    .| 88P|\n")
					.concat("  \\    YP\"      `| 8P  `.\n")
					.concat(" /     \\._____.d|    .'\n")
					.concat("`--..__)888888P`._.'\n");
				for (String line : logo.split("\n"))
					fmtText("", line, "", width);

				fmtText("", "", "", width);
				fmtText("", "Thanks for shopping at " + store.name, "", width);

				fmtText("", "", "", width);
				System.out.println("--------------------------------------------------");
				System.out.println();

				this.balance -= total;
				this.cart.clear();

				try{ Thread.sleep(1500); }
				catch (InterruptedException e){
					Thread.currentThread().interrupt();
				}

				return 0;
			}
			else{
				System.out.println("Card declined! Insufficient balance...");
				System.out.println();

				try{ Thread.sleep(1500); }
				catch (InterruptedException e){
					Thread.currentThread().interrupt();
				}

				return 1;
			}
		}
		else{
			System.out.println("No items in cart to checkout!");
			System.out.println();

			try{ Thread.sleep(1500); }
			catch (InterruptedException e){
				Thread.currentThread().interrupt();
			}

			return 0;
		}
	}
}
