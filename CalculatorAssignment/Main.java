import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class DoubleWrapper{
	double value;

	public DoubleWrapper(double value){
		this.value = value;
	}
}

class Term{
	DoubleWrapper left, right;
	String operator;

	public Term(){
		this(new DoubleWrapper(0.0), new DoubleWrapper(0.0), " ");
	}

	public Term(DoubleWrapper left, DoubleWrapper right, String operator){
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
}

public class Main{
	public static String safeIntegerParse(String expression){
		int idx = 0; 
		if (expression.charAt(0) == '-')
			++idx;

		boolean decimal = false;
		for (; idx < expression.length(); ++idx){
			char symbol = expression.charAt(idx);

			if (symbol == '.' && !decimal){
				decimal = true;
				continue;
			}
			else if ('0' <= symbol && symbol <= '9'){
				continue;
			}

			break;	
		}

		return expression.substring(0, idx);
	}

	public static double evaluateExpression(String expression) throws Exception{
		ArrayList<ArrayList<Term>> terms = new ArrayList<ArrayList<Term>>();
		for (int i = 0; i < 4; ++i)
			terms.add(new ArrayList<Term>());

		HashMap<String, Integer> presedence = new HashMap<String, Integer>();
		presedence.put("sin", 3);
		presedence.put("cos", 3);
		presedence.put("tan", 3);
		presedence.put("sqrt", 3);
		presedence.put("^", 2);
		presedence.put("/", 1);
		presedence.put("%", 1);
		presedence.put("*", 1);
		presedence.put("+", 0);
		presedence.put("-", 0);
		presedence.put(" ", -1);

		ArrayList<String> unaryOperators = new ArrayList<String>(
				Arrays.asList("sqrt", "sin", "cos", "tan"));

		Term prevTerm = new Term();
		
		int iter = 0, idx = 0, endLength = 0;
		expression = expression.replaceAll("\\s", "");
		
		for (int i = 0; i < unaryOperators.size(); ++i){
			String[] splits = expression.split(unaryOperators.get(i));
			expression = "";
			for (int j = 0; j < splits.length; ++j){
				if (j < splits.length - 1)
					expression += splits[j] + "0" + unaryOperators.get(i);
				else
					expression += splits[j];
			}
		}

		while (idx + endLength < expression.length()){
			String left = safeIntegerParse(expression.substring(idx));
			idx += left.length();
			
			String operator = "";
			String[] split = expression.substring(idx).split("[0-9]");
			if (split.length > 0){
				operator = split[0];
				idx += split[0].length();
			}

			String right = safeIntegerParse(expression.substring(idx));
			endLength = right.length();

			Term currTerm = new Term();
			switch (operator){
				case "sqrt": case "sin": case "cos": case "tan": 
				case "^": case "*": case "/": case "%": case "+": case "-": 
					if (presedence.get(operator) > presedence.get(prevTerm.operator)){
						currTerm = new Term(
							new DoubleWrapper(Double.parseDouble(left)), 
							new DoubleWrapper(Double.parseDouble(right)),
							operator
						);

						if (iter > 0)
							prevTerm.right = currTerm.left;	
					}
					else{
						currTerm = new Term(
							prevTerm.right,
							new DoubleWrapper(Double.parseDouble(right)),
							operator
						);
					}

					terms.get(presedence.get(operator)).add(currTerm);
				break;
				default:
					throw new Exception("Error Parsing Expression!");
			}

			prevTerm = currTerm; 
			++iter;
		}

		double result = 0.0;
		for (int i = terms.size() - 1; i >= 0; --i){
			for (int j = 0; j < terms.get(i).size(); ++j){
				double left = terms.get(i).get(j).left.value, right = terms.get(i).get(j).right.value;

				switch (terms.get(i).get(j).operator){
					case "sin":
						result = Math.sin(right);
						break;
					case "cos":
						result = Math.cos(right);
						break;
					case "tan":
						result = Math.tan(right);
						break;
					case "sqrt":
						result = Math.sqrt(right);
						break;
					case "^":
						result = Math.pow(left, right);
						break;
					case "*":
						result = left * right;
						break;
					case "%":
						result = left % right;	
						break;
					case "/":
						result = left / right;	
						break;
					case "+":
						result = left + right;
						break;
					case "-":
						result = left - right;
						break;
				}

				terms.get(i).get(j).left.value = result;
				terms.get(i).get(j).right.value = result;
			}
		}

		return result;
	}

	public static void main(String args[]){
		System.out.print(0);
		String expression = "";
		Scanner stdinHandle = new Scanner(System.in);
	
		expression = stdinHandle.nextLine();
		expression = "0" + expression;
		try{
			System.out.println(evaluateExpression(expression));
		}
		catch (Exception e){
			System.err.println("Error Parsing Expression!");
		}

		stdinHandle.close();
	}
}
