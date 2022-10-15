public class Word{
	// Default constructor
	public Word(){
		this("");
	}
	// Parameterized constructor
	public Word(String word){
		myWord = word;
	}
	// Accessor method, returns the internal word string
	public String getWord(){
		return myWord;
	}
	// Return first letter of internal word string
	public String getFirstLetter(){
		return myWord.substring(0, 1);
	}
	// Return last letter of internal word string
	public String getLastLetter(){
		return myWord.substring(myWord.length()-1);
	}
	// Remove first letter of internal word string
	public void removeFirstLetter(){
		myWord = myWord.substring(1);
	}
	// Remove last letter of internal word string
	public void removeLastLetter(){
		myWord = myWord.substring(0, myWord.length()-1);
	}
	// Return index of character within internal word string
	public int findLetter(String stringToFind){
		for (int i = 0; i < myWord.length(); ++i){
			if (myWord.substring(i, i + 1).equals(stringToFind)){
				return i;
			}
		}
		return -1;
	}

	// Internal word string
	private String myWord;
}
