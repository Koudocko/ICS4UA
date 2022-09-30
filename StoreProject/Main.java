import java.lang.Thread;
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

			System.out.println("0 - Quit shopping");
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
		
		try{ Thread.sleep(1500); }
		catch (InterruptedException e){
			Thread.currentThread().interrupt();
		}
	
		while (true){
			int option = selectStore(stores);
			if (option == 0){
				customer.printReceipt();
				return;	
			}
			else{
				customer.buyAt(stores.get(option - 1));
			}
		}
	}
}
