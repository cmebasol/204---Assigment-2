import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * contains static methods used to convert and evaluate infix and post-fix expressions
 * @author Meba Tadesse
 * 
 *
 */
public class Notation {
	private static MyQueue<String> postfix;
	private static MyStack<String> stack;

	/**
	 * convertw infix to postfix
	 * 
	 * @param infix string to be converted
	 * @return string converted to postfix
	 * @throws InvalidNotationFormatException thrown if
	 * @throws StackUnderflowException
	 * @throws QueueOverflowException
	 */
	public static String convertInfixToPostfix(String infix)
			throws InvalidNotationFormatException, StackUnderflowException, QueueOverflowException {
		stack = new MyStack<String>(infix.length());
		postfix = new MyQueue<String>(infix.length());
		String last;

		for (String current : infix.split("")) {
			// if the next character is digit add it to the stack
			if (isDigit(current)) {
				try {
					postfix.enqueue(current);
				} catch (QueueOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			else if (current.equals("(")) {
				try {
					stack.push(current);
				} catch (StackOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (isOperator(current)) {
				while (!stack.isEmpty() && hasLowerPrecedence(current, stack.top()) && !current.equals("(")) {
					try {
						postfix.enqueue(stack.pop());

					} catch (StackUnderflowException | QueueOverflowException e) {
						// TODO Auto-generated catch block

						e.printStackTrace();
					}
				}
				try {
					stack.push(current);
					if (stack.isEmpty())
						;
					last = current;

				} catch (StackOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (current.equals(")")) {

				// move all the operators in the stack to the postfix until the top stack is
				// left parenthesis
				while (!stack.isEmpty() && !stack.top().equals("(")) {
//						if (current.equals(infix))

					postfix.enqueue(stack.pop());
//						if(stack.isEmpty())
//							throw new Exception("left parenthesis is missing"); 
				}

				// pop the left parenthesis because it is at the top if the loop is broken
				if (!stack.isEmpty())
					stack.pop();
				else
					throw new InvalidNotationFormatException();// thrown if there is no opening parenthesis
																// corresponding to the current closing parenthesis

			}
			// the following lines of code easily shows what is happening in each loop
//			System.out.println("current: " + current);
//			System.out.println("stack: " + stack.toString());
//			System.out.println("postfix: " + postfix.toString()+ "\n");

		}
		// put all the operators in the stack
		while (!stack.isEmpty()) {
			try {
				if (!stack.top().equals("(")) {
//					last = stack.top();
					postfix.enqueue(stack.pop());
				}

			} catch (StackUnderflowException | QueueOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return postfix.toString();

	}

	/**
	 * checks whether a string is an operator
	 * 
	 * @param c string to be checked
	 * @return true if the parameter is an operator
	 */
	public static boolean isOperator(String c) {
		return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
	}

	/**
	 * checks if the first operator has lower precedence than the second operator
	 * 
	 * @param o1 the first operator to be compared
	 * @param o2 the second operator to be compared
	 * @return true if the first operator has lower precedence than the second one
	 *         and returns false otherwise
	 */
	public static boolean hasLowerPrecedence(String o1, String o2) {
		return precedence(o1) < precedence(o2);
	}

	/**
	 * gives a precedence value to operators
	 * 
	 * @param operator the operator whose precedence value will be determined
	 * @return an integer precedence value for every operator
	 */
	public static int precedence(String operator) {
		return switch (operator) {
		case "(" -> 0;
		case "+", "-" -> 1;
		case "/", "*" -> 2;
		case "%" -> 3;
		default -> 4;
		};
	}

	/**
	 * evaluate if a post-fix is appropriate
	 * 
	 * @param postfix string to be evaluated
	 * @return true if the post-fix is appropriate and false if otherwise
	 */
	public static int evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException { 
		Stack<Integer> stack = new Stack<>();
		for (String component : postfix.split("")) {
			if (isDigit(component)) {

				stack.push(Integer.parseInt(component));
			} else {
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				int n1 = stack.pop();
				int n2 = stack.pop();
				switch (component) {
				case "+" -> stack.push(n2 + n1);
				case "-" -> stack.push(n2 - n1);
				case "/" -> stack.push(n2 / n1);
				case "*" -> stack.push(n2 * n1);
				case "%" -> stack.push(n2 % n1);
				}

			}
		}
		return stack.pop();
	}

	/**
	 * a method that takes in post-fix and convert it to infix
	 * 
	 * @param postfix the string that will be converted to infix
	 * @return a string with infix notation
	 * @throws InvalidNotationFormatException exception thrown if the postfix
	 *                                        notation given is invalid
	 * @throws InvalidPostfixExpression
	 */

	public static String convertPostfixToInfix(String postfix)
			throws InvalidNotationFormatException, InvalidPostfixExpression {
		MyStack<String> stack = new MyStack<String>(55);
		for (String each : postfix.split("")) {
			if (isDigit(each)) {
				try {
					stack.push(each);
				} catch (StackOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (isOperator(each)) {
				if (stack.size() < 2)
					throw new InvalidNotationFormatException();
				String num1 = stack.pop();
				String num2 = stack.pop();
				String next = "(" + num2 + each + num1 + ")";
				try {
					stack.push(next);
				} catch (StackUnderflowException | StackOverflowException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
//			System.out.println("current: " + each);
//			System.out.println("stack now:" + stack.toString());
		}

		if (stack.size() > 1)
			throw new InvalidNotationFormatException();

		return stack.toString();
	}

	private static boolean isDigit(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
