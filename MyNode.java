/**
 * This class defines instance variable "data" to hold a reference to the 
 * element represented by the node and an instance variable "next" to reference 
 * another MyNode object. Together these variables create the stack in a linking structure. 
 * A lot of this class was taken from the code Professor Joanna posted from class. 
 * 
 * @author Haley Danylchuk and Joanna Klukowska
 *
 * @param <T> This makes the entire class of Generic type. 
 */
public class MyNode <T> {
	
	//information held in the node 
	private T data; 
	//points to the next node
	private MyNode <T> next; 
	
/**
 * This default constructor creates an empty node. 
 */
	public MyNode() { 
		data = null; 
		next = null; 
	}
/**
 * This constructor creates a node with a specified data item. 
 * 
 * @param data data is the item to store in the node 
 */
	public MyNode(T data) { 
		
		if(data != null){ 
			this.data = data;
		}
	}
	
/**
 * This constructor creates a node with a specified data and reference to the next 
 * node. 
 * 
 * @param data data is the item to store in the current node. 
 * 
 * @param next next is reference to the following node. 
 */
	public MyNode(T data, MyNode<T> next){ 
		if (data!= null){ 
			this.data = data; 
		} if (next!= null) { 
			this.next = next; 
		}
	}
	
/**
 * This method returns the next node in the stack. 
 * 
 * @return returns the next node. 
 */
	public MyNode<T> getNext(){ 
		return next;
	}

/**
 * This method sets the next node. 
 * 
 * @param next is the next node to be set. 
 */
	public void setNext(MyNode<T> next){ 
		this.next = next; 
	}
	
/**
 * This method returns the data. 
 * 
 * @return returns the data located in the node. 
 */
	public T getData(){ 
		return data; 
	}
	
/**
 * This method sets the data to a node. 
 * 
 * @param data data is what the node will be set to. 
 */
	public void setData(T data){ 
		this.data = data; 
	}
	

}
