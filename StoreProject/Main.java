import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import shopping.*;

public class Main{
	public static void displayMenu(ArrayList<Store> stores){

	}

	public static void initStores(ArrayList<Store> stores){
		Store games = new Store(
			"Gentoo Games", 
			"25 The West Mall #40, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 620-0050"
		);
		games.addItem("Controller", 54.50);
		games.addItem("Console", 150.00);
		games.addItem("PC", 760.00);
		games.addItem("Game catridge", 90.00);
		games.addItem("Extension cable", 15.00);
		stores.add(games);

		Store food = new Store(
			"Debian Diner", 
			"25 The West Mall, #700 Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 621-0203"
		);
		food.addItem("Steak", 54.50);
		food.addItem("Buger", 150.00);
		food.addItem("Salad", 760.00);
		food.addItem("Soup", 90.00);
		food.addItem("Cake", 15.00);
		stores.add(food);

		stores.add(new Store(
			"Fedora Fishing", 
			"25 The West Mall #1660, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 620-7750"
		));
		stores.add(new Store(
			"Ubuntu Utilities", 
			"25 The West Mall #1900, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 620-9435"
		));
		stores.add(new Store(
			"Arch Art", 
			"25 The West Mall #3038, Etobicoke, ON", 
			"M9C 1B8", 
			"(416) 622-3769"
		));
	}

	public static void main(String args[]){
		ArrayList<Store> stores = new ArrayList<Store>();
		initStores(stores);

		Scanner stdinHandle = new Scanner(System.in);

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
			.concat("._/\"Yaa_ :    .| 88P|     |    |  \\|  |/  ___/\\   __\\_  __ \\/  _ \\   /  \\ /  \\__  \\ |  | |  |\n")
			.concat("\\    YP\"      `| 8P  `.   |    `   \\  |\\___ \\  |  |  |  | \\(  <_> ) /    Y    \\/ __ \\|  |_|  |__\n")
			.concat("/     \\._____.d|    .'   /_______  /__/____  > |__|  |__|   \\____/  \\____|__  (____  /____/____/\n")
			.concat("`--..__)888888P`._.'             \\/        \\/                               \\/     \\/\n");

		System.out.println(logo);

		System.out.print("Hello new customer, please enter your name: ");
		String customerName = stdinHandle.nextLine();
		
		Customer customer = new Customer(customerName);
		System.out.println("Welcome to DistroMall, " + customerName + "!");
		System.out.println("Balance: " + NumberFormat.getCurrencyInstance().format(customer.balance));
		System.out.println("Coupons available: " + customer.coupons.size());
		for (int i = 0; i < customer.coupons.size(); ++i){
			System.out.println("\tCoupon " + (i + 1) + " - " + customer.coupons.get(i) + "% off");
		}

		stdinHandle.close();
	}
}
