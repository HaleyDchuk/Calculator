/**
 * This class defines the PostFixException which is the 
 * exception that should be thrown when an invalid expression is detected. 
 * It extends Exception. 
 * 
 * @author Joanna Klukowska 
 * 
 * @version 11/18/2015
 *
 */
public class PostFixException extends Exception {
	
	/**
	 * 
	 */
	//I really have no idea what this is. Java suggested it and without it, sometimes 
	//not all of my exceptions are caught so I kept it. 
	private static final long serialVersionUID = 1L;

	public PostFixException(){ 
		super();
	}
	
	//INVALD is printed here when this exception is coded in the other classes. 
	public PostFixException(String message){ 
		super(message);
	}

}
