// WordTest
// @author Tyler Wehrle Period 4

import java.util.Scanner;

public class WordTest
{
	public static void main(String[] args)
	{
		Scanner keyboard = new Scanner(System.in);

		// Get word to be used by Word class
		System.out.print("Enter a word: ");
		String userInput = keyboard.next(); 
		Word word1 = new Word(userInput); 
		System.out.println("You entered the word: " + word1.getWord()); 
	
		// Print first letter of word
		System.out.println("The first letter of your word is: " + word1.getFirstLetter()); 

		// Print last letter of word
		System.out.println("The last letter of your word is: " + word1.getLastLetter()); 

		// Print index of letter within word
		System.out.print("Enter a letter to find: ");
		String letter = keyboard.next(); 
		System.out.println("Index of letter is: " + word1.findLetter(letter));

		// Remove first letter of word
		word1.removeFirstLetter();
		System.out.println("The word without the first letter is: " + word1.getWord());

		// Remove last letter of word
		word1.removeLastLetter();
		System.out.println("The word without the last letter is: " + word1.getWord());
	}
}
