import java.util.ArrayList;
import java.util.Scanner;
import shopping.*;

// Project: Store Project
// Author: Tyler Wehrle 

// Work Cited:
// Get date/time - https://www.javatpoint.com/java-get-current-date 
// Pause thread of execution - https://www.geeksforgeeks.org/how-to-temporarily-suspend-a-thread-in-java/
// Format double as currency - https://stackabuse.com/how-to-format-number-as-currency-string-in-java/

// Upgrades:
// Added multiple stores under one mall heading, rather than just one store
// Added the option to return items from your shopping cart
// Program is functional with multiple customers due to the object-oriented-nature
// Trivial to add more items to stores and more stores to the mall
// Added the ability to use coupons at checkout

// Debugging:
// Trial and error trying to allign the ASCII art completely

public class Main{
	// Interface for the customer to select a store to shop at
	public static int selectStore(ArrayList<Store> stores){
		Scanner stdinHandle = new Scanner(System.in);

		int choice = -1;
		while (choice < 0 || choice > stores.size()){
			System.out.println("---==={ Available Shops in DistroMall }===---");

			System.out.println("0 - Exit mall");
			System.out.println("--------------------");
			for (int i = 0; i < stores.size(); ++i){
				System.out.println((i + 1) + " - " + stores.get(i).name);
				System.out.println("--------------------");
			}

			System.out.print("Where would you like to go (input an option): ");

			if (stdinHandle.hasNextInt()){
				choice = stdinHandle.nextInt();
			}
			else{
				stdinHandle.nextLine();
			}
			System.out.println();
		}

		//Return the choice the user selected
		return choice;
	}

	// Initializes the stores array with predefined data
	public static void initStores(ArrayList<Store> stores){
		// Store 1
		Store games = new Store(
			"Gentoo Games", 
			"25 The West Mall #40, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 620-0050"
		);
		games.addItem("Controller", 49.99);
		games.addItem("Console", 469.99);
		games.addItem("PC", 760.00);
		games.addItem("Game cartridge", 60.00);
		games.addItem("Extension cable", 10.17);
		stores.add(games);

		// Store 2
		Store food = new Store(
			"Debian Diner", 
			"25 The West Mall, #700 Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 621-0203"
		);
		food.addItem("Steak", 26.43);
		food.addItem("Burger", 9.19);
		food.addItem("Salad", 5.99);
		food.addItem("Soup", 7.27);
		food.addItem("Cake", 13.99);
		stores.add(food);

		// Store 3
		Store fishing = new Store(
			"Fedora Fishing", 
			"25 The West Mall #1660, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 620-7750"
		);
		fishing.addItem("Rod", 37.59);
		fishing.addItem("Line", 5.00);
		fishing.addItem("Bait", 0.99);
		fishing.addItem("Lure", 2.87);
		fishing.addItem("Bobber", 1.20);
		stores.add(fishing);

		// Store 4
		Store utilities = new Store(
			"Ubuntu Utilities", 
			"25 The West Mall #1900, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 620-9435"
		);
		utilities.addItem("Wood plank", 24.14);
		utilities.addItem("Paint", 33.97);
		utilities.addItem("Lightbulb", 3.30);
		utilities.addItem("Drill", 99.00);
		utilities.addItem("Hammer", 19.98);
		stores.add(utilities);
		
		// Store 5
		Store appliances = new Store(
			"Arch Appliances", 
			"25 The West Mall #3038, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 622-3769"
		);
		appliances.addItem("Toaster", 59.49);
		appliances.addItem("Fridge", 289.99);
		appliances.addItem("Oven", 899.98);
		appliances.addItem("Microwave", 109.98);
		appliances.addItem("Dishwasher", 599.99);
		stores.add(appliances);
		
	}

	public static void main(String args[]){
		// List of stores the mall has to offer
		ArrayList<Store> stores = new ArrayList<Store>();
		initStores(stores); // Populate empty array of stores

		// Welcome message to be displayed on program start
		String welcomeMessage =               
					"        a8888b.\n"
			.concat("       d888888b.\n")
			.concat("       8P\"YP\"Y88\n")
			.concat("       8|o||o|88\n")
			.concat("       8'    .88         __      __       .__                                  __\n")
			.concat("       8`._.' Y8.       /  \\    /  \\ ____ |  |   ____  ____   _____   ____   _/  |_  ____\n")
			.concat("      d/      `8b.      \\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\  \\   __\\/  _ \\\n")
			.concat("    .dP   .     Y8b.     \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/   |  | (  <_> )\n")
			.concat("   d8:'   \"   `::88b.     \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >  |__|  \\____/\n")
			.concat("  d8\"           `Y88b          \\/       \\/          \\/            \\/     \\/\n")
			.concat(" :8P     '       :888    ________  .__          __                     _____         .__  .__\n")
			.concat("  8a.    :      _a88P    \\______ \\ |__| _______/  |________  ____     /     \\ _____  |  | |  |\n")
			.concat("._/\"Yaa_ :    .| 88P|     |    |  \\|  |/  ___/\\   __\\_  __ \\/  _ \\   /  \\ /  \\\\__  \\ |  | |  |\n")
			.concat("\\    YP\"      `| 8P  `.   |    `   \\  |\\___ \\  |  |  |  | \\(  <_> ) /    Y    \\/ __ \\|  |_|  |__\n")
			.concat("/     \\._____.d|    .'   /_______  /__/____  > |__|  |__|   \\____/  \\____|__  (____  /____/____/\n")
			.concat("`--..__)888888P`._.'             \\/        \\/                               \\/     \\/\n");

		// Goodbye message to be displayed on program exit
		String goodbyeMessage =               
					"        a8888b.\n"
			.concat("       d888888b.\n")
			.concat("       8P\"YP\"Y88\n")
			.concat("       8|o||o|88\n")
			.concat("       8'    .88            ___________.__                   __               _____\n")
			.concat("       8`._.' Y8.           \\__    ___/|  |__ _____    ____ |  | __  ______ _/ ____\\___________\n")
			.concat("      d/      `8b.            |    |   |  |  \\\\__  \\  /    \\|  |/ / /  ___/ \\   __\\/  _ \\_  __ \\\n")
			.concat("    .dP   .     Y8b.          |    |   |   Y  \\/ __ \\|   |  \\    <  \\___ \\   |  | (  <_> )  | \\/\n")
			.concat("   d8:'   \"   `::88b.         |____|   |___|  (____  /___|  /__|_ \\/____  >  |__|  \\____/|__|\n")
			.concat("  d8\"           `Y88b                       \\/     \\/     \\/     \\/     \\/\n")
			.concat(" :8P     '       :888        _________.__                         .__\n")
			.concat("  8a.    :      _a88P       /   _____/|  |__   ____ ______ ______ |__| ____    ____\n")
			.concat("._/\"Yaa_ :    .| 88P|       \\_____  \\ |  |  \\ /  _ \\\\____ \\\\____ \\|  |/    \\  / ___\\\n")
			.concat("\\    YP\"      `| 8P  `.     /        \\|   Y  (  <_> )  |_> >  |_> >  |   |  \\/ /_/  >\n")
			.concat("/     \\._____.d|    .'     /_______  /|___|  /\\____/|   __/|   __/|__|___|  /\\___  /\n")
			.concat("`--..__)888888P`._.'               \\/      \\/       |__|   |__|           \\//_____/\n");

		System.out.println(welcomeMessage);

		// Obtain a name for the customer through standard input stream
		Scanner stdinHandle = new Scanner(System.in);
		System.out.print("Hello new customer, please enter your name: ");
		String customerName = stdinHandle.nextLine();
		System.out.println();

		Customer customer = new Customer(customerName);
		System.out.println("Welcome to DistroMall, " + customer.name + "!");
		System.out.println();

		// Pause for the user to process
		try{ Thread.sleep(1500); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
		
		while (true){
			int option = selectStore(stores); // Get the store the user wants to shop at

			// 0 is exit program, anything else is shop at the corresponding store
			if (option == 0){
				System.out.println(goodbyeMessage);
				return;	
			}
			else{
				customer.storeMenu(stores.get(option - 1));
			}
		}
	}
}
