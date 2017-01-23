/**
 * This class is the runnable program that contains the main method.
 * It parses the command line argument, reads the input file, as it reads
 * each line from the input file, it passes the input to methods in the 
 * ExpressionTools Class to evaluate it, writes the result to the output file, 
 * and then continues with the next line from the input file until no more lines exist. 
 * 
 * @author Haley Danylchuk 
 *
 * @version 11/18/2015
 * 
 */
import java.io.BufferedWriter;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Calculator {
	
	/**
	 * The main method parses the command line argument, reads the input file, and evaluates 
	 * each line of the input file as it is read by passing it to methods in the ExpressionTools 
	 * Class. 
	 * 
	 * @param args args are the command line argument for which file to be read 
	 * @throws Exception 
	 */

	public static void main(String[] args) throws Exception{ 
		
		if (args.length < 1) { 
			System.err.println("File name missing.");
			System.exit(0);
		}

		//whatever argument is first, no matter how many, will be the file to be read 
		java.io.File fileName = new java.io.File(args[0]); 
		if(!fileName.canRead()){ 
			System.err.printf("Cannot read from file %s\n", fileName.getAbsolutePath()); 
			System.exit(0);
		}
		
		if(!fileName.exists()){ 
			System.err.println("No such file exists.");
			System.exit(0);
		}
		
		Scanner reader = new Scanner(fileName);
		//keeps track of all of the results of the expressions as they are 
		//read in, converted to postfix, and evaluated 
		//also keeps track of INVALID results 
		String result = "";
		
		//creates an incstance of the Balanced class which checks to see if there 
		//are an equal number of open parenthesis as there are closed ones 
		Balanced bal = new Balanced("(", ")");
		//the method in Balanced Class keeps track if parenthesis are balanced or 
		//not through the use of numbers 0, 1, or 2
		int parenthesisResult;
		
		while(reader.hasNext()){ 
			try{ 
				//reads each line of input file 
				String mathExpres = reader.nextLine();
				
				//trims the excess white space in beginning and end of each expression 
				String mathExpress = mathExpres.trim();
				
				//checks for balanced parenthesis 
				parenthesisResult = bal.test(mathExpress);
				
				if(parenthesisResult == 1){ 
					throw new PostFixException("INVALID");
				} else if(parenthesisResult == 2){ 
					throw new PostFixException("INVALID");
				//if the parenthesis of the expression are balanced, convert it to 
				//postfix, and if no exception is raised after that, evaluate it 	
				}else{ 

					//passes the expression read from input file to the method converter 
					//in the ExpressionTools Class 
					String mathExpression = ExpressionTools.converter(mathExpress);
					
					//passes the newly converted expression to the method evaluate in the 
					//ExpressionTools Class 
					String converted = ExpressionTools.evaluate(mathExpression);
					result += converted + "\n";
			}
		}
		
		//if the expression did not have balanced parenthesis, an exception was thrown 
		//in either the converter or evaluate method, INVALID will be added to the 
		//final string of results 	
		catch(PostFixException e){
			result += "INVALID" + "\n";
		
	}
}			
		//close the scanner that read in the input file 
		reader.close();
		
		//this section writes to the output file 
		if (args.length < 2) { 
			System.err.println("Name of the output file missing");
			System.exit(0);
		}
		
		//whatever is the second argument, no matter how many arguments there are 
		//in the command line, will be the name of the output file 
		java.io.File output = new java.io.File(args[1]);
		try { 
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			writer.write(result);
			writer.close();
		} catch (Exception e) { 
			throw new Exception("File cannot be created");
		}
	}
}


