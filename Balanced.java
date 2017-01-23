/**
 * Checks for balanced parenthesis in expressions. I took this code straight 
 * from the Object-Oriented Data Structures using Java 3rd Edition Book 
 * Chapter 3. 
 * 
 * @author Dale/Joyce/Weems
 *
 */
public class Balanced {
	private String openSet; 
	private String closeSet; 
	
	/**
	 * Matching pairs of open and close parenthesis are provided to the constructor
	 * through the two String parameter 
	 * 
	 * @param openSet open parenthesis 
	 * @param closeSet closed parenthesis 
	 */
	public Balanced(String openSet, String closeSet){ 
		this.openSet = openSet; 
		this.closeSet = closeSet; 
	}
	
	/**
	 * This method tests to see if the parenthesis of an expression are balanced or not; 
	 * meaning that there is an even number of open and closed parenthesis in the appropriate 
	 * places in the expression.  
	 * 
	 * @param expression the expression to check if its parenthesis are balanced or not 
	 * 
	 * @return returns 0 if the expression is balanced, 1 if the expression has 
	 * unbalanced symbols, and 2 if the expression came to an end prematurely. 

	 */
	public int test(String expression){ 
		
		//current expression character being checked 
		char currChar; 
		
		//index of current character 
		int currCharIndex;
		
		//index of last character in expression 
		int lastCharIndex;
		
		//index of current character in openSet 
		int openIndex;
		
		//index of current character in closeSet 
		int closeIndex;
		
		//true as long as the expression is balanced 
		boolean stillBalanced = true; 
		
		//holds unmatched open symbols 
		MyStack<Integer> stack;
		stack = new MyStack<Integer>();
		
		currCharIndex = 0; 
		lastCharIndex = expression.length()-1;
		
		//while the expression is still balanced and not at the end of expression 
		while(stillBalanced && (currCharIndex <= lastCharIndex)){ 
			currChar = expression.charAt(currCharIndex);
			openIndex = openSet.indexOf(currChar);
			
			//if the current character is in the openSet
			if(openIndex != -1){ 
				//push the index onto the stack
				stack.push(openIndex);
			} 
			else { 
				closeIndex = closeSet.indexOf(currChar);
				//if the current character is in the closeSet
				if(closeIndex != -1){ 
					try { 
						//try to pop an index off the stack 
						openIndex = stack.peek();
						stack.pop();
						
						//if the pop index doesn't match 
						if(openIndex != closeIndex){ 
							//the the expression is unbalanced 
							stillBalanced = false; 
							
						}
					}
					//if the stack was empty 
					catch(PostFixException e){ 
						//the expression is unbalanced 
						stillBalanced = false; 
					}
				}
			}
			//set up processing of next character 
			currCharIndex ++; 
		}
		if(!stillBalanced)
			//unbalanced parenthesis 
			return 1; 
		else 
			if (!stack.empty())
				//premature end of expression 
				return 2;
			else 
				//expression is balanced 
				return 0; 
 	}

}
