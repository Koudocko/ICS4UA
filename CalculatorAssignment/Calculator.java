import java.util.Scanner;
import java.util.HashMap;

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
	static HashMap<String, Integer> presedence = new HashMap<String, Integer>();

	public static Node tokenizeExpression(String expression) throws Exception{
		int idx = 0;
		String left, operator, right;

		if (expression.substring(idx).indexOf("sqrt") == 0){
			left = "0";
		}
		else{
			left = expression.substring(idx).split("[^0-9.]")[0];
			idx += left.length();
		}

		operator = expression.substring(idx).split("[0-9]")[0];
		idx += operator.length();

		right = expression.substring(idx).split("[^0-9.]")[0];

		Node newNode = new Node(
			0.0, 
			new Node(Double.parseDouble(left)),
			new Node(Double.parseDouble(right)),
			operator
		);

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

	public static double parseExpression(Node head) throws Exception{
		if (head != null){
			switch (head.operator){
				case "sqrt":
					return Math.sqrt(parseExpression(head.right));
				case "^":
					return Math.pow(parseExpression(head.left),parseExpression(head.right));
				case "*":
					return parseExpression(head.left) * parseExpression(head.right);
				case "%":
					return parseExpression(head.left) % parseExpression(head.right);
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
		String expression = "";
		Scanner stdinHandle = new Scanner(System.in);
		expression = stdinHandle.nextLine().replaceAll("\\s", "");

		presedence.put("sqrt", 0); presedence.put("^", 1); presedence.put("/", 2); 
		presedence.put("%", 2); presedence.put("*", 2); presedence.put("+", 3);
		presedence.put("-", 3); 

		try{
			System.out.println(parseExpression(tokenizeExpression(expression)));
		}
		catch (Exception e){
			System.err.println("Error Parsing Expression!");
		}

		stdinHandle.close();
	}
}
