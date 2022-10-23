import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

class DoubleWrapper{
	double value;

	public DoubleWrapper(double value){
		this.value = value;
	}
}

class Term{
	DoubleWrapper left, right;
	char operator;

	public Term(){
		this(new DoubleWrapper(0.0), new DoubleWrapper(0.0), ' ');
	}

	public Term(DoubleWrapper left, DoubleWrapper right, char operator){
		this.left = left;
		this.right = right;
		this.operator = operator;
	}
}

public class Main{
	public static String safeIntegerParse(String expression){
		int idx = 0; 

		for (; idx < expression.length(); ++idx){
			char symbol = expression.charAt(idx);
			if ('0' > symbol || symbol > '9')
				break;	
		}

		return expression.substring(0, idx);
	}

	public static double evaluateExpression(String expression) throws Exception{
		ArrayList<ArrayList<Term>> terms = new ArrayList<ArrayList<Term>>(3);
		terms.add(new ArrayList<Term>());
		terms.add(new ArrayList<Term>());
		terms.add(new ArrayList<Term>());

		HashMap<Character, Integer> presedence = new HashMap<Character, Integer>();
		presedence.put('^', 2);
		presedence.put('*', 1);
		presedence.put('/', 1);
		presedence.put('%', 1);
		presedence.put('+', 0);
		presedence.put('-', 0);
		presedence.put(' ', -1);

		Term prevTerm = new Term();
		
		int iter = 0, idx = 0;
		expression = expression.replaceAll("\\s", "");
		while (idx < expression.length()-1){
			String left = safeIntegerParse(expression.substring(idx));
			idx += left.length();
			
			char operator = expression.charAt(idx);
			idx += 1;

			String right = safeIntegerParse(expression.substring(idx));

			Term currTerm = new Term();
			switch (operator){
				case '^': case '*': case '/': case '%': case '+': case '-': 
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
					throw new Exception("Invalid symbol!");
			}

			prevTerm = currTerm; 
			++iter;
		}

		double result = 0.0;
		for (int i = terms.size() - 1; i >= 0; --i){
			for (int j = 0; j < terms.get(i).size(); ++j){
				double left = terms.get(i).get(j).left.value, right = terms.get(i).get(j).right.value;

				switch (terms.get(i).get(j).operator){
					case '^':
						result = Math.pow(left, right);
						break;
					case '*':
						result = left * right;
						break;
					case '/':
						result = left / right;	
						break;
					case '%':
						result = left % right;
						break;
					case '+':
						result = left + right;
						break;
					case '-':
						result = left = right;
						break;
				}

				terms.get(i).get(j).left.value = result;
				terms.get(i).get(j).right.value = result;
			}
		}

		return result;
	}

	public static void main(String args[]){
		String expression = "";
		Scanner stdinHandle = new Scanner(System.in);
	
		expression = stdinHandle.nextLine();
		try{
			System.out.println(evaluateExpression(expression));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
