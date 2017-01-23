/**
 * This class converts the infix expression read from the input file in the main 
 * method of the Calculator Class to a postfix expression. It then passes the postfix 
 * expression to a method that evaluates it. The result from the method that evaluates is 
 * printed to the output file in the Calculator Class. 
 * 
 * @author Haley Danylchuk and Joanna KluKowska
 * 
 * @version 11/18/2015
 */
import java.util.Scanner;

public class ExpressionTools {
	
	/**
	 * This method converts the expression read from the input file from infix to 
	 * postfix. 
	 * 
	 * @param infix infix represents the expression being read from the input file in the main method. 
	 * 
	 * @return returns the newly converted postfix expression so it can then be evaluated. 
	 * 
	 * @throws PostFixException throws an exception "INVALID" if an invalid expression 
	 * is detected before the method is done being executed. 
	 */
	public static String converter(String infix) throws PostFixException{ 
		
		//spits the expression by spaces so each number or symbol can be appropriately managed 
		String [] expression = infix.split(" ");
		
		//creates an instance of a stack that will hold elements of type String 
		MyStack<String> stack = new MyStack<String>();
		
		//this is the final String that will be returned to the main method
		String postfix = "";
		
		//accesses the first element of the expression that will letter be checked 
		//for validity 
		String first = expression[0];
		
		//accesses the last element of the expression that will letter be checked 
		//for validity 
		String last = expression[expression.length-1];
		
		//if checkFirst returns true
		if(checkFirst(first)){ 
			//if checkLast returns true 
			if(checkLast(last)){
				//for each element in the expression 
				
				//this checkSymbols method is supposed to validate that all of the 
				//symbols of the expression are either positive integers, operators, 
				//or open and closed parenthesis, but it is making my program crash and
				//I haven't exactly figured out why yet. 
				//if(checkSymbols(infix)){
				
				for(int x = 0; x < expression.length; x++){ 
					String s = expression[x];
					//if it is a positive integer
					if(check(s) && checkValue(s)){ 
					//if(check(s)){
						//add it to the postfix expression 
						postfix += (expression[x] + " "); 
					}
					else if(expression[x].equals("(")){ 
						stack.push(expression[x]);
					}
					else if(expression[x].equals("*") || expression[x].equals("/") || expression[x].equals("-") || expression[x].equals("+")){ 
						while ((!stack.empty()) && (!stack.peek().equals("("))){ 
							//precendence checks whick operator should be evaluated first 
							if(precedence(stack.peek(), expression[x])){ 
								postfix += (stack.pop() + " ");
							}
							else { 
								break;
							}
						}
						//pushes the appropriate * / + - symbol onto the stack 
						stack.push(expression[x]);
					}
					else if(expression[x].equals(")")){ 
						//while you find a matching pair of parenthesis, pop the 
						//latest element of the stack, which will be in int
						while((!stack.empty()) && (!stack.peek().equals("("))){ 
							postfix += (stack.pop() + " ");
						}
						if(!stack.empty())
							stack.pop();
					}
					else{ 
				}
			//while stack is still not empty, continue popping and appending 
			//to the postfix expression 
			}
				while(!stack.empty()){ 
					postfix += (stack.pop() + " ");
				}
		}
	}
			
		return postfix;
	} 
		
/**
* This method evaluates the expression that has been converted from infix to postfix through the use of 
* a stack that is instantiated from the MyStack Class. 
* 
* @param expression
* @return returns an int, which is the final result after the expression has been evaluated 
* this result will be printed to the output file 
*
* @throws PostFixException will print INVALID on the output file if the expression that is given as the 
* argument is invalid such as division by zero, more operators than operands, if one of the operators is 
* a negative number, etc.
*/

	public static String evaluate(String expression) throws PostFixException{ 
		//this stack is of type Integer because it needs to be evaluated mathematically 
		MyStack<Integer> stack = new MyStack<Integer>();
		
		int value;
		// * / + - 
		String operator;
		int operand1; 
		int operand2; 
		int result = 0; 
		
		Scanner tokenizer = new Scanner(expression);
		
		while(tokenizer.hasNext()){
			//if the next element of the expression is an integer 
			if(tokenizer.hasNextInt()){ 
				//get that int and push it onto the stack 
				value = tokenizer.nextInt();
				stack.push(value);
			}
			else { 
				//look at the next element which won't be an int 
				operator = tokenizer.next();
				
				//at this point if the stack is empty, it is not supposed to be if the 
				//expression is valid, so a PostFixException is thrown 
				if (stack.empty()){ 
					tokenizer.close(); 
					throw new PostFixException("INVALID");
				}
				operand2 = stack.peek();
				stack.pop();
				
				if(stack.empty()){ 
					tokenizer.close();
					throw new PostFixException("INVALID");
				}
				operand1 = stack.peek();
				stack.pop();
				
				if(operator.equals("/")){ 
					if(operand2 == 0){ 
						//throws a PostFixException if expression tries to divide by 0 
						extracted();
					}
					//if it isn't trying to divide by 0, regular intgere division is carried out 
					else { 
						result = operand1/operand2; 
					}
					//order of operand1 and operand2 don't matter for multiplication or addition 
				} else if(operator.equals("*")){ 
					result = operand1 * operand2; 
				}else if(operator.equals("+")){ 
					result = operand1 + operand2; 
					//order of operand1 and operand2 matter for subtraction 
				}else if(operator.equals("-")){ 
					result = operand1 - operand2; 
				}else { 
					tokenizer.close();
					throw new PostFixException("INVALD");
				}
				//after all of the appropriate pushes and pops, the final result 
				//will be the last thing left in the stack 
				stack.push(result);
			}
		}
		tokenizer.close();
		
		//at this point if there is nothing left in the stack at the end, the expression must have 
		//been invalid 
		if(stack.empty())
			throw new PostFixException("INVALID");
		result = stack.peek(); 
		stack.pop();
		
		if(!stack.empty())
			throw new PostFixException("INVALID");
		
		String results = ""; 
		results = Integer.toString(result);
		return results; 
	}

	/**
	 * This method throws an exception when integer division by 0 is attempted 
	 * 
	 * @throws PostFixException throws exception saying that the expression is UNDEFINED 
	 * if it tries to divide by 0. It might not print UNDEFINED to the output file if 
	 * a different exception was thrown earlier in the evaluation process. 
	 */
	private static void extracted() throws PostFixException {
		throw new PostFixException("UNDEFINED");
	}

	/**
	 * This method checks if an element of the expression is an integer or not. 
	 * 
	 * @param infixCharacter takes in an element of the String expression into its parameters 
	 * 
	 * @return true if the piece of the expression being checked is an integer; false otherwise 
	 * 
	 * @throws PostFixException throws exception not if false is returned, but if some other error 
	 * is detected. This is because not all pieces of the expression need to be an integer. 
	 */
	public static boolean check(String infixCharacter) throws PostFixException{ 
		String infixChar = infixCharacter.trim();
		try {
			//if the element of the expression is an integer, return true 
			Integer.parseInt(infixChar);
			return true;
		}
			
		catch (Exception e){ 
			return false; 
		}
	}
	
	/**
	 * This method checks whether or not a specific element of the expression is positive 
	 * .or not since we are only working with positive integers. 
	 * 
	 * @param infix infix here represents the element of the infix expression being checked. 
	 * 
	 * @return returns true if element of expression is an integer and positive; false otherwise 
	 * 
	 * @throws PostFixException throws exception if some other errow is found. 
	 */
	public static boolean checkValue(String infix) throws PostFixException{ 
		String infixC = infix.trim();
		boolean value = false;  
		//if element is an integer
		if(check(infix)){ 
			int num = Integer.parseInt(infixC);
			
			//if that number is not negative, then it is valid and return true 
			if(num >= 0){ 
				value =  true;
			}
		 }
		else{ 
			value = false;
		}
		 return value;
	}
	
	/**
	 * This method checks all of the symbols in the expression to see if they are either 
	 * positive integers, open or closed parenthesis, or operators. 
	 * 
	 * @param infixx infixx is the entire expression to be checked 
	 * 
	 * @return true if all symbols are valid; false otherwise 
	 * 
	 * @throws PostFixException throws an exception if a symbol is found to be invalid 
	 * because then that means the entire expression is invalid 
	 * 
	 */
	//This method is commented out where I call it in the converter method because 
	//it makes my program crash. It's purpose is to make sure all of the elements of 
	//the expression are valid. 
	// Right now, an expression like, "hello 3 + 4" prints INVALID because of my method 
	//that checks the validity of the first element. 
	// An expression like "2 + 4 hello" prints INVALID because of my method that checks 
	//the validity of the last element. 
	// An expression like "3 + hello 4" prints 7 because this method isn't working 
	//properly, although it should print INVALID. 
	
	public static boolean checkSymbols(String infixx) throws PostFixException{ 
		String infix = infixx.trim();
		boolean correctSymbols = false;
		String[] infixEx = infix.split(" ");
		if(infix.length() == 1){
			if((check(infixEx[0]) && checkValue(infixEx[0])) || (infixEx[0].equals("*") || infixEx[0].equals("/") || infixEx[0].equals("+") || infixEx[0].equals("-") || infixEx[0].equals("(") || infixEx[0].equals(")"))){ 
				correctSymbols = true;
				//throw new PostfixException("IVALID");
			}
			else { 
				//correctSymbols = true;
				throw new PostFixException("IVALID");
			}
		}
		
		else{ 
			for(int x = 0; x< infix.length()-1; x++){ 
				if((check(infixEx[x]) && checkValue(infixEx[x])) || (infixEx[x].equals("*") || infixEx[x].equals("/") || infixEx[x].equals("+") || infixEx[x].equals("-") || infixEx[x].equals("(") || infixEx[x].equals(")"))){ 
					correctSymbols = true;
			}
			else { 
				//correctSymbols = true;
				throw new PostFixException("IVALID");
			}
		}
	}
		return correctSymbols;
	}

	/**
	 * This method checks that the first element of the expression is either a positive 
	 * integer or an "(". 
	 * 
	 * @param firstElement firstElement is the first element of the expression. 
	 * 
	 * @return true if the first element is a valid one; false otherwise 
	 * 
	 * @throws PostFixException throws exception if the first element is not valid; 
	 * because if the first element is not valid, neither is the rest of the expression. 
	 */
	public static boolean checkFirst(String firstElement) throws PostFixException{ 
		
	 	boolean validFirst = false;
	 	
		if((check(firstElement) && checkValue(firstElement)) || (firstElement.equals("("))){ 
	//if((check(firstElement)) || (firstElement.equals("("))){ 
			validFirst = true; 
			//throw new PostfixException("INVALID");
	}
		return validFirst;
	}

	/**
	 * This method checks that the last element of the expression is either a positive 
	 * integer or an ")" and not an operator. 
	 * 
	 * @param lastElement lastElement is the last element of the expression. 
	 * 
	 * @return true if the last element is a valid one; false otherwise 
	 * 
	 * @throws PostFixException throws exception if the last element is not valid; 
	 * because if the last element is not valid, neither is the rest of the expression since an 
	 * infix expression has operators between operands, not after them. 
	 */
	public static boolean checkLast(String lastElement) throws PostFixException{ 
		
	 	boolean validLast = false;
	 	if((check(lastElement) && checkValue(lastElement)) || (lastElement.equals(")"))){ 
	 		//if((check(lastElement)) || (lastElement.equals(")"))){ 	
			validLast = true; 
			}
		return validLast;
	}
	
	/**
	 * This method checks which two numbers to evaluate first depending on the operator 
	 * 
	 * @param top top is the accessible element of the stack 
	 * 
	 * @param c c is the element in the expression that is currently being looked at 
	 * 
	 * @return true if the operators are in correct place to be evaluated in the right order; 
	 * false otherwise
	 */
	public static boolean precedence(String top, String c){ 
		if(top.equals("+") && c.equals("*")){ 
			return false;
		} else if(top.equals("*") && c.equals("-")){ 
			return true;
		} else if(top.equals("+") && c.equals("-")){ 
			return true;
		}
		return true;
	}
}
