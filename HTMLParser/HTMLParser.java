import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HTMLParser{
	public static void main(String[] args) throws IOException
	{
		// variable declarations
		String htmlCode = ""; // HTML code of web page
		String webPageAddress = "http://www.wyoarea.org"; // user inputed web page address
		String title = "";

		// prompt the user to input a web page address & get that address from the user
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter a full web page address (URL) and press the Enter key: ");
		webPageAddress = keyboard.nextLine();
		keyboard.close();

		// retrieve HTML code from web page - backslash Z marks the end of a file
		try{
			htmlCode = new Scanner(new URL(webPageAddress).openStream()).useDelimiter("\\Z").next();

			title = getTitle(htmlCode);
			System.out.println(title);
		}
		catch (IOException ioe){
			System.err.println("Failed to successfully grab HTML content: " + ioe.getMessage());
		}
		catch (IndexOutOfBoundsException ioobe){
			System.err.println("Title of HTML content does not exist!");
		}

		// write a call statement that calls the getTitle method below and
		// assigns the String that it returns to the variable title
		// display the title of the web page
	} // end of main method

		// precondition: htmlCode contains some text between its <title> and </title> tags
		// which will either be lowercase or uppercase (but not mixed).
		// postcondition: returns the text that is between the <title> and </title> tags
		public static String getTitle(String htmlCode) throws IndexOutOfBoundsException
		{
			String lowerHtmlCode = htmlCode.toLowerCase();
			return htmlCode.substring(
				lowerHtmlCode.indexOf("<title>") + 7, 
				lowerHtmlCode.indexOf("</title>")
			);
		} // end of getTitle method
}
