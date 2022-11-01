/* Author: Ethan Leung
 * NetID: eleung6
 * Project 1: URCalculator
 */ 

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URCalculator<E> extends URLSQ<E>{
	// regex: decimals, int, add, mult, div, sub, parenth, exp, or, and, equal, not, less, greater, mod. sin, cos, tan, pi, unary minus
	static String regex = ("\\d+\\.\\d+|\\d+|\\+|\\*|/|-|\\(|\\)|\\^|\\||\\&|\\=|\\!|\\>|\\<|\\%|sin|cos|tan|pi|\\~");
	
	static int lineNum = 0;
	
	// https://introcs.cs.princeton.edu/java/11precedence/
	// https://docs.revenera.com/installshield26helplib/LangRef/LangrefRelational_operator_precedence.htm
	static int precedence(String op) {
		switch (op) {
		case "=":
			return 1;
		
		case "|":
			return 2;
			
		case "&":
			return 3;
			
		case "<":
		case ">":
			return 4;
		
		case "+":
		case "-":
			return 5;
			
		case "*":
		case "/":
		case "%":
			return 6;
			
		case "^":
			return 7;
			
		case "sin":
		case "cos":
		case "tan":
			return 8;
			
		case "~":
		case "!":
			return 9;
			
		}
		return -1;
	}
	
	// https://www.baeldung.com/java-check-string-number
	public static boolean isNum(String token) {
		try {
			double d = Double.parseDouble(token);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	
	public static double booleanToDouble(boolean truthVal) {
	    return (truthVal) ? 1.00 : 0.00;
	}
	
	public static URLSQ<String> inFixToPostFix (String input){
		URLSQ<String> queue = new URLSQ<>();
		Stack<String> stack = new Stack<>();
		URLinkedList<String> inFix = new URLinkedList<>();
		input = input.replace(" ", "");
		
		if (!(input.replaceAll(regex, "").equals(""))) {
			return null;
		}
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			inFix.add(matcher.group());
		}
		
		// https://www.geeksforgeeks.org/stack-set-2-infix-to-postfix/
		for (int i = 0; i <= inFix.size() - 1; i++) {
			String currToken = inFix.get(i);
			if (isNum(currToken) || currToken.equals("pi")) {
				queue.enqueue(currToken);
			} else if (currToken.equals("(")) {
				stack.push(currToken);
			} else if (currToken.equals(")")) {
				while (!stack.isEmpty() && !stack.peek().equals("(")) {
					queue.enqueue(stack.peek());
					stack.pop();
				}
				
				stack.pop();
			} else {
				while (!stack.isEmpty() && ((precedence(currToken) <= precedence(stack.peek())))) {
					queue.enqueue(stack.peek());
					stack.pop();
				}
				stack.push(currToken);
			}
				
		}
		
		while (!stack.isEmpty()) {
			if (stack.peek().equals("(")) 
				stack.pop();
			queue.enqueue(stack.peek());
			stack.pop();
		}
		return queue;
		
	}
	
	public static Stack<Double> postFixEval (String input){
		URLSQ<String> queue = inFixToPostFix(input);
		Stack<Double> stack = new Stack<>();
		
		if (queue == null) {
			System.out.println("(Line " + lineNum + ") Invalid Expression");
			return null;
		}
		
		int size = queue.size();
		for (int i = 0; i <= size - 1; i++) {
			String currToken = (String) queue.first();
			queue.dequeue();
			if (isNum(currToken)) {
				stack.push(Double.parseDouble(currToken));
			} else if (currToken.equals("pi")) {
				stack.push(Math.PI);
			} else {
				double num1 = 0;
				double num2 = 0;
				if (currToken.equals("!") || currToken.equals("sin") || currToken.equals("cos") || currToken.equals("tan") || currToken.equals("~")) {
					num1 = stack.peek();
					stack.pop();
				} else {
					num1 = stack.peek();
					stack.pop();
					num2 = stack.peek();
					stack.pop();
				}
                boolean tval1 = false;
                boolean tval2 = false;
				switch(currToken) {
				
					case "+":
                    stack.push(num2+num1);
                    break;
                     
                    case "-":
                    stack.push(num2-num1);
                    break;
                     
                    case "/":
                    if (num1 == 0) {
                    	System.out.println("(Line " + lineNum + ") ERROR: Division by zero");
                    	return null;
                    } else {
                    	stack.push(num2/num1);
                        break;
                    }
                    
                    case "%":
                    if (num1 == 0) {
                        System.out.println("(Line " + lineNum + ") ERROR: Division by zero");
                        return null;
                    } else {
                        stack.push(num2%num1);
                        break;
                    }
                    
                    case "sin":
                    stack.push(Math.sin(num1));
                    break;
                    
                    case "cos":
                    stack.push(Math.cos(num1));
                    break;
                        
                    case "tan":
                    stack.push(Math.tan(num1));
                    break;
                    
                    case "~":
                    stack.push(-1*num1);
                    break;
                    
                    case "*":
                    stack.push(num2*num1);
                    break;
                    
                    case "<":
                    stack.push(booleanToDouble(num2<num1));
                    break;
                    
                    case ">":
                    stack.push(booleanToDouble(num2>num1));
                    break;
                    
                    case "=":
                    stack.push(booleanToDouble(num2==num1));
                    break;
                    
                    case "!":
                    	if (num1 == 1) {
                        	tval1 = true;
                        } else if (num1 == 0) {
                        	tval1 = false;
                        } else {
                        	System.out.println("(Line " + (lineNum) + ") ! Operation: Can only take '1' or '0'");
                        	return null;
                        }
                    stack.push(booleanToDouble(!(tval1)));
                    break;
                    
                    case "|":
                    if (num1 == 1) {
                    	tval1 = true;
                    } else if (num1 == 0) {
                    	tval1 = false;
                    } else {
                    	System.out.println("(Line " + (lineNum) + ") | Operation: Can only take '1' or '0'");
                    	return null;
                    }
                    
                    if (num2 == 1) {
                    	tval2 = true;
                    } else if (num2 == 0) {
                    	tval2 = false;
                    } else {
                    	System.out.println("(Line " + (lineNum) + ") | Operation: Can only take '1' or '0'");
                    	return null;
                    }
                    stack.push(booleanToDouble(tval1 || tval2));
                    break;
                        
                    case "&":
                    if (num1 == 1) {
                        tval1 = true;
                    } else if (num1 == 0) {
                        tval1 = false;
                    } else {
                        System.out.println("(Line " + (lineNum) + ") & Operation: Can only take '1' or '0'");
                        return null;
                    }
                        
                    if (num2 == 1) {
                        tval2 = true;
                    } else if (num2 == 0) {
                        tval2 = false;
                    } else {
                        System.out.println("(Line " + (lineNum) + ") & Operation: Can only take '1' or '0'");
                        return null;
                    }
                    stack.push(booleanToDouble(tval1 && tval2));
                    break;
				}
			}
		}
		return stack;
	}
	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		In in = new In(args[0]);
		PrintWriter writer = new PrintWriter(args[1]);
		String[] expressions = in.readAllLines();
		for (String exp : expressions) {
			lineNum++;
			if (postFixEval(exp) == null) {
				writer.println("Invalid Expression");
			} else {
	            writer.println(postFixEval(exp));
			}
        }
		writer.close();
	}
}
