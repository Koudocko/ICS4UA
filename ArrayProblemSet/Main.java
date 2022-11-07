// Author: Tyler Wehrle
// Project: Array Problem Set
// Credit: https://github.com/Koudocko/AP_CSP (sorting algorithm, my github)

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{
	// Sort array (primitive)
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

	// Sort array (arraylist)
	public static void insertionSort(ArrayList<Integer> arr, boolean ascending){
		for (int i = 1; i < arr.size(); ++i){
			int j = i;

			while (j > 0 && (arr.get(j) < arr.get(j - 1) == ascending)){
				int temp = arr.get(j);
				arr.set(j, arr.get(j - 1));
				arr.set(j - 1, temp);
				--j;
			}
		}
	}

	// Display array (primitive)
	public static void printArray(int arr[], int input){
		if (input != 1)
			insertionSort(arr, input == 2);

		for (int i = 0; i < arr.length; ++i)
			System.out.print(arr[i] + ", ");
		System.out.println();
	}

	// Display array (arraylist)
	public static void printArray(ArrayList<Integer> arr, int input){
		if (input != 1)
			insertionSort(arr, input == 2);

		for (int i = 0; i < arr.size(); ++i)
			System.out.print(arr.get(i) + ", ");
		System.out.println();
	}

	public static void main(String args[]){
		// int nums[] = {10,2,3,11,4,12,5,13,18,20,1,2,18,34,25,65,11,0,35,22};
		ArrayList<Integer> nums = new ArrayList<Integer>(
			Arrays.asList(10,2,3,11,4,12,5,13,18,20,1,2,18,34,25,65,11,0,35,22)
		);
		Scanner stdinHandle = new Scanner(System.in);

		// Runtime input loop
		runtime: while (true){
			// Menu display
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

								// try{ nums[index] = value; }
								try{ nums.set(index, value); }
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
						// for (int i = 0; i < nums.length; ++i)
						// 	sum += nums[i];
						for (int i = 0; i < nums.size(); ++i)
							sum += nums.get(i);

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
