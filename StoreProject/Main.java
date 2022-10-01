import java.util.ArrayList;
import java.util.Scanner;
import shopping.*;

public class Main{
	public static int selectStore(ArrayList<Store> stores){
		Scanner stdinHandle = new Scanner(System.in);

		int choice = -1;
		while (choice < 0 || choice > stores.size()){
			System.out.println();
			System.out.println("---==={ Available Shops in DistroMall }===---");

			System.out.println("0 - Checkout");
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

		return choice;
	}

	public static void initStores(ArrayList<Store> stores){
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

		Store utilities = new Store(
			"Ubuntu Utilities", 
			"25 The West Mall #1900, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 620-9435"
		);
		utilities.addItem("Wood plank", 24.14);
		utilities.addItem("Paint", 33.97);
		utilities.addItem("Lightbulb", 3.30);
		utilities.addItem("Drills", 99.00);
		utilities.addItem("Hammer", 19.98);
		stores.add(utilities);
		
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
		ArrayList<Store> stores = new ArrayList<Store>();
		initStores(stores);

		String logo =               
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

		System.out.println(logo);

		Scanner stdinHandle = new Scanner(System.in);
		System.out.print("Hello new customer, please enter your name: ");
		String customerName = stdinHandle.nextLine();
		System.out.println();

		Customer customer = new Customer(customerName);
		System.out.println("---==={ Welcome to DistroMall, " + customer.name + " }===---");
		
		while (true){
			int option = selectStore(stores);

			if (option == 0){
				return;	
			}
			else{
				customer.storeMenu(stores.get(option - 1));
			}
		}
	}
}
