import java.util.Arrays;
import java.util.Scanner;

public class Main{
	public static void insertionSort(int arr[], boolean ascending){
		for (int i = 1; i < arr.length; ++i){
			int j = i;

			while (j > 0 && (arr[j] < arr[j - 1] == ascending)){
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
				--j;
			}
		}
	}

	public static void printArray(int arr[], int input){
		if (input != 1)
			insertionSort(arr, input == 2);

		for (int i = 0; i < arr.length; ++i)
			System.out.print(arr[i] + ", ");
		System.out.println();
	}

	public static void main(String args[]){
		int nums[] = {10,2,3,11,4,12,5,13,18,20,1,2,18,34,25,65,11,0,35,22};
		Scanner stdinHandle = new Scanner(System.in);

		runtime: while (true){
			System.out.println("-={ Menu }=-");
			System.out.println("0 - Quit Program");
			System.out.println("1 - Display Array Elements (Normal)");
			System.out.println("2 - Display Array Elements (Ascending)");
			System.out.println("3 - Display Array Elements (Descending)");
			System.out.println("4 - Modify Array Element");
			System.out.println("5 - Display Array Sum");
	
			try{
				int input = stdinHandle.nextInt();

				switch (input){
					case 0:
						break runtime;
					case 1: case 2: case 3:
						printArray(nums, input);
						break;
					case 4:
						System.out.print("Enter Element Index: ");
						if (stdinHandle.hasNextInt()){
							int index = stdinHandle.nextInt();

							System.out.print("Enter Element Value: ");
							if (stdinHandle.hasNextInt()){
								int value = stdinHandle.nextInt();

								try{ nums[index] = value; }
								catch (Exception e)
								{ System.out.println("___Invalid_Index_Value___"); }
							}
							else{
								stdinHandle.nextLine();
							}
						}
						else{
							stdinHandle.nextLine();
						}
						break;
					case 5:
						int sum = 0;
						for (int i = 0; i < nums.length; ++i)
							sum += nums[i];

						System.out.println("Sum of Array Elements: " + sum);
						break;
					default:
						System.out.println("___Invalid_Selection___");
				}
			}
			catch (Exception e){
				System.out.println("___Invalid_Selection___");
				stdinHandle.nextLine();
			}
		}
	}
}
