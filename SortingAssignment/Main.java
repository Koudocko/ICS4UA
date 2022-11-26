// Author: Tyler Wehrle
// Project: Sorting Assignment
// Credit: https://github.com/Koudocko/AP_CSP (sorting algorithm, my github)
// 				 https://stackify.com/heres-how-to-calculate-elapsed-time-in-java/ (time elapsed)

import java.util.Scanner;
import java.util.Random;

public class Main{
	// Swaps two indecies of an array
	static void swap(double nums[], int left, int right){
		double temp = nums[left];
		nums[left] = nums[right];
		nums[right] = temp;
	}

	// Displays array contents 
	public static void printArray(double nums[]){
		for (double ele : nums)
			System.out.print(ele + " ");
		System.out.println("\n");
	}
	
	// Move pivot element into correct location and either to left or right
	public static int partition(double nums[], int begin, int end){
		double pivotData = nums[begin];

		// Find pivot index of array subsection
		int pivotIndex = begin;
		for (int i = begin + 1; i <= end; ++i){
			if (nums[i] <= pivotData)
				++pivotIndex;
		}

		// Move the pivot element into pivot index
		swap(nums, pivotIndex, begin);

		// Move all other elements to left or right of pivot, if greater or lesser
		while (begin < pivotIndex && end > pivotIndex){
			while (nums[begin] <= pivotData)
				++begin;
			while (nums[end] > pivotData)
				--end;

			if (begin < pivotIndex && end > pivotIndex)
				swap(nums, begin++, end--);
		}

		return pivotIndex;
	}

	// Sorts two array subsections together
	public static void merge(double nums[], int left, int mid, int right){
		int leftIndex = left,
				rightIndex = mid + 1,
				mergeIndex = left;

		double temp[] = new double[right - left + 1];

		// Move elements into temp array in ascending order until one segment has completed
		while (leftIndex <= mid && rightIndex <= right){
			if (nums[leftIndex] < nums[rightIndex])
				temp[mergeIndex++ - left] = nums[leftIndex++];
			else
				temp[mergeIndex++ - left] = nums[rightIndex++];
		}
		
		// Move remaining elements into temp array
		while (leftIndex <= mid)
			temp[mergeIndex++ - left] = nums[leftIndex++];
		while (rightIndex <= right)
			temp[mergeIndex++ - left] = nums[rightIndex++];

		// Update original array with sorted temp array
		for (int i = 0; i < right - left + 1; ++i)
			nums[left + i] = temp[i];
	}

	// Selection sort
	public static void selectionSort(double nums[]){
		// Each iteration moves element i into correct place
		for (int i = 0; i < nums.length; ++i){
			int curr = i;

			// Scans array for next lowest value
			for (int j = i + 1; j < nums.length; ++j){
				if (nums[j] < nums[curr])
					curr = j;
			}

			swap(nums, i, curr);
		}
	}

	// Bubble sort
	public static void bubbleSort(double nums[]){
		// Performed as each element is displaced by one index only per iteration
		for (int i = 0; i < nums.length - 1; ++i){
			// Move each index one index closer to correct position
			for (int j = 0; j < nums.length - i - 1; ++j){
				if (nums[j + 1] < nums[j])
					swap(nums, j, j + 1);
			}
		}
	}

	// Insertion sort
	public static void insertionSort(double nums[]){
		// Performed on each element i
		for (int i = 1, j = 1; i < nums.length; ++i, j = i){
			// For each index i, sort everything properly to left of i
			while (j > 0 && nums[j] < nums[j - 1]){
				swap(nums, j , j - 1);
				--j;
			}
		}
	}

	// Quick sort
	public static void quickSort(double nums[], int begin, int end){
		// Recursively sort based on pivot index of each subsegment
		if (begin < end){
			int pivotIndex = partition(nums, begin, end);

			// To left of pivot
			quickSort(nums, begin, pivotIndex - 1);
			// To right of pivot
			quickSort(nums, pivotIndex + 1, end);
		}
	}

	// Merge sort
	public static void mergeSort(double nums[], int begin, int end){
		// Recursively sort based on subsegment midpoint
		if (begin < end){
			int mid = (begin + end) / 2;

			// Perform on left sub-segment
			mergeSort(nums, begin, mid);
			// Perform on right sub-segment
			mergeSort(nums, mid + 1, end);
			// Merge/sort left and right subsegments
			merge(nums, begin, mid, end);
		}
	}

	// Auto populate array
	public static void autoCreate(double nums[]){
		Random rand = new Random();

		// Random num between -100 <-> 100
		for (int i = 0; i < nums.length; ++i)
			nums[i] = rand.nextInt(201) - 100;
	}

	// Manually populate array
	public static void manualCreate(double nums[], Scanner stdinHandle){
		for (int i = 0; i < nums.length; ++i){
			double input = 0.0;

			// Keep asking for input until input is valid
			do{
				stdinHandle.nextLine();
				System.out.print("Enter index " + i + " value: ");
			} while (!stdinHandle.hasNextDouble() || 
							(input = stdinHandle.nextDouble()) < -100 || 
							input > 100);

			nums[i] = input; 
		}
	}

	public static void main(String args[]){
		double nums[] = new double[50];
		Scanner stdinHandle = new Scanner(System.in);

		create_numsay: while (true){
			System.out.println("-={ Create Array }=-");
			System.out.println("=> Size 50");
			System.out.println("=> Range -100 <-> 100");
			System.out.println("0 - Quit Program");
			System.out.println("1 - Manually Create Array");
			System.out.println("2 - Automatically Create Array\n");

			try{
				int input = stdinHandle.nextInt();
				
				// Array creation input handling
				switch (input){
					case 0:
						break create_numsay;
					case 1:
						manualCreate(nums, stdinHandle);
						break;
					case 2:
						autoCreate(nums);
						break;
					default:
						System.out.println("___Invalid_Selection___\n");
						continue;
				}
			}
			catch (Exception e){
				System.out.println("___Invalid_Selection___\n");
				stdinHandle.nextLine();
				continue;
			}

			System.out.println("Array Before:");
			printArray(nums);

			sort_numsay: while (true){
				System.out.println("-={ Create Array > Sort Array }=-");
				System.out.println("0 - Quit Program");
				System.out.println("1 - Selection Sort");
				System.out.println("2 - Bubble Sort");
				System.out.println("3 - Insertion Sort");
				System.out.println("4 - Quick Sort");
				System.out.println("5 - Merge Sort\n");

				try{
					int input = stdinHandle.nextInt();
					double temp[] = nums.clone();

					long start = System.nanoTime();
					// Sorting method input handling
					switch (input){
						case 0:
							break sort_numsay;
						case 1:
							selectionSort(temp);
							break;
						case 2:
							bubbleSort(temp);
							break;
						case 3:
							insertionSort(temp);
							break;
						case 4:
							quickSort(temp, 0, temp.length - 1);
							break;
						case 5:
							mergeSort(temp, 0, temp.length - 1);
							break;
						default:
							System.out.println("___Invalid_Selection___\n");
							continue;
					}
					long end = System.nanoTime();

					System.out.println("Array After:");
					printArray(temp);
					System.out.println("Time to sort: " + (end - start) + " nanoseconds\n");
				}
				catch (Exception e){
					System.out.println("___Invalid_Selection___\n");
					stdinHandle.nextLine();
					continue;
				}
			}
		}

		stdinHandle.close();
	}
}
