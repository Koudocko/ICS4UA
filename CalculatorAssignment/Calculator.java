// Author: Tyler Wehrle
// Assignment: Calculator

import java.util.Scanner;
import java.util.HashMap;

// Node of a binary tree, stores the numeric value, operation, and pointers to left and right dependant expressions
class Node{
	double result;
	String operator;
	Node left, right;

	Node(double result, Node left, Node right, String operator){
		this.result = result;
		this.operator = operator;
		this.left = left;
		this.right = right;
	}
	Node(double result){ this(result, null, null, "");}
}

public class Calculator{
	// HashMap to store operator presedence, quick lookup
	static HashMap<String, Integer> presedence = new HashMap<String, Integer>();

	// Converts a string expression into connected nodes of a binary tree, returns the head node
	public static Node tokenizeExpression(String expression) throws Exception{
		int idx = 0;
		String left, operator, right;

		// Extract subexpression left operand
		if (expression.charAt(0) == '-'){
			if (expression.substring(idx + 1).indexOf("sqrt") == 0){
				left = "-1";
				++idx;
			}
			else{
				left = "-".concat(expression.substring(1).split("[^0-9.]")[0]); 
				idx += left.length(); 
			}
		}
		else{
			if (expression.indexOf("sqrt") == 0){
				left = "1";
			}
			else{
				left = expression.split("[^0-9.]")[0]; 
				idx += left.length(); 	
			}
		}

		// Extract subexpression operator
		if (expression.substring(idx).indexOf("sqrt") == 0)
			operator = expression.substring(idx).split("[0-9]")[0];
		else
			operator = expression.substring(idx).split("[0-9]|sqrt")[0].substring(0, 1);
		idx += operator.length();

		// Extract subexpression right operand
		if (expression.charAt(idx) == '-'){
			if (expression.substring(idx + 1).indexOf("sqrt") == 0){
				right = "-1";
			}
			else{
			  right = "-".concat(expression.substring(idx + 1).split("[^0-9.]")[0]); 
			}
		}
		else{
			if (expression.substring(idx).indexOf("sqrt") == 0){
				right = "1";
			}
			else{
				right = expression.substring(idx).split("[^0-9.]")[0];
			}
		}

		// Construct new node from extraction
		Node newNode = new Node(
			0.0, 
			new Node(Double.parseDouble(left)),
			new Node(Double.parseDouble(right)),
			operator
		);

		// Recursively assemble order of binary tree based on presedence, back-to-front of expression
		if (expression.substring(idx).length() > right.length()){
			Node headNode = tokenizeExpression(expression.substring(idx));
			Node adjacentNode = headNode;
			newNode.right = adjacentNode;

			if (presedence.get(adjacentNode.operator) >= presedence.get(newNode.operator)){
				while (!adjacentNode.left.operator.isEmpty() &&
					presedence.get(adjacentNode.left.operator) >= 
					presedence.get(newNode.operator))
			  { adjacentNode = adjacentNode.left; }

				newNode.right = adjacentNode.left;
				adjacentNode.left = newNode;
				return headNode;
			}
		}
		
		return newNode;
	}

	// Postfix evaluation of the binary tree, returning the result of the expression from the provided node
	public static double parseExpression(Node head) throws Exception{
		if (head != null){
			switch (head.operator){
				case "sqrt":
					return head.left.result * Math.sqrt(parseExpression(head.right));
				case "^":
					return Math.pow(parseExpression(head.left),parseExpression(head.right));
				case "*":
					return parseExpression(head.left) * parseExpression(head.right);
				case "/":
					return parseExpression(head.left) / parseExpression(head.right);
				case "+":
					return parseExpression(head.left) + parseExpression(head.right);
				case "-":
					return parseExpression(head.left) - parseExpression(head.right);
				case "":
					return head.result;
				default:
					throw new Exception("Invalid token!");
			}
		}

		return 0.0;
	}

	public static void main(String args[]){
		// Get input expression and strip whitespace
		String expression = "";
		Scanner stdinHandle = new Scanner(System.in);
		expression = stdinHandle.nextLine().replaceAll("\\s", "");

		// Initialize presedence hashmap
		presedence.put("sqrt", 0); presedence.put("^", 1); presedence.put("/", 2); 
		presedence.put("*", 2); presedence.put("+", 3); presedence.put("-", 3); 

		try{
			System.out.println(parseExpression(tokenizeExpression(expression)));
		}
		catch (Exception e){
			System.err.println("Error Parsing Expression!");
		}

		stdinHandle.close();
	}
}
