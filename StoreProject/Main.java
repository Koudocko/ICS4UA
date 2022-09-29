import shopping.*;

public class Main{
	public static void displayMenu(){
		 String logo =               
					"        a8888b.\n"
			.concat("       d888888b.\n")
			.concat("       8P\"YP\"Y88\n")
			.concat("       8|o||o|88\n")
			.concat("       8'    .88         __      __       .__                                  __\n")
			.concat("       8`._.' Y8.       /  \\    /  \\ ____ |  |   ____  ____   _____   ____   _/  |_  ____\n")
			.concat("      d/      `8b.      \\   \\/\\/   // __ \\|  | _/ ___\\/  _ \\ /     \\_/ __ \\  \\   __\\/  _ \\ \n")
			.concat("    .dP   .     Y8b.     \\        /\\  ___/|  |_\\  \\__(  <_> )  Y Y  \\  ___/   |  | (  <_> )\n")
			.concat("   d8:'   \"   `::88b.     \\__/\\  /  \\___  >____/\\___  >____/|__|_|  /\\___  >  |__|  \\____/  \n")
			.concat("  d8\"           `Y88b          \\/       \\/          \\/            \\/     \\/                \n")
			.concat(" :8P     '       :888    ________  .__          __                     _____         .__  .__\n")
			.concat("  8a.    :      _a88P    \\______ \\ |__| _______/  |________  ____     /     \\ _____  |  | |  |\n")
			.concat("._/\"Yaa_ :    .| 88P|     |    |  \\|  |/  ___/\\   __\\_  __ \\/  _ \\   /  \\ /  \\__  \\ |  | |  | \n")
			.concat("\\    YP\"      `| 8P  `.   |    `   \\  |\\___ \\  |  |  |  | \\(  <_> ) /    Y    \\/ __ \\|  |_|  |__\n")
			.concat("/     \\._____.d|    .'   /_______  /__/____  > |__|  |__|   \\____/  \\____|__  (____  /____/____/\n")
			.concat("`--..__)888888P`._.'             \\/        \\/                               \\/     \\/      \n");

		 System.out.println(logo);

	}

	public static void main(String args[]){
		Store canadaComputers = new Store(
			"Canada Computers", 
			"284 College St, Toronto, ON", 
			"M5T1R9", 
			"(416) 926-0107"
		);

		displayMenu();
	}
}
