/**
 * This class is my own implementation of a stack. It's methods include 
 * setting a stack to empty, checking if a stack is empty or not, looking at the 
 * top element of the stack without removing it, removing the element at the top of 
 * the stack, adding an element to the top of the stack, searching for an object, and 
 * a method that converts to String. 
 * 
 * @author Haley Danylchuk
 * 
 * @version 11/18/2015 
 *
 * @param <T> classifies this entire class as type Generic 
 */
public class MyStack <T> {
	
	//references to the first node in the stack; the only node that will have any 
	//function performed on it. 
	private MyNode<T> top;
	
	//keeps track of the number of elements in the stack 
	private int size;
	
/**
 * This method creates an empty stack. 
 */
	public MyStack() { 
		top = null; 
		size = 0;
	}
/**
 * This method checks if the stack is empty or not. 
 * 
 * @return returns true if the stack is empty; returns false otherwise. 
 */
	boolean empty(){
		boolean isEmpty = false; 
		//if top == null, it means there is no data stored in the node and is 
		//therefore empty 
		if(top==null && size == 0){ 
			isEmpty = true;
		}
		return isEmpty; 
	}

/**
 * This method allows the top element to be seen without removing it. 
 * 
 * @return it returns the element at the top of the stack but does not remove it 
 * 
 * @throws PostFixException if the element that is trying to be seen does not exist because 
 * the stack is empty 
 * 
 */
	public T peek() throws PostFixException{
		if (!empty()) { 
			return top.getData();
		}
		else { 
			throw new PostFixException("Peek attempted on an empty stack!");
		}
	}
	
	

	
/**
 * This method removes the element at the top of the stack. 
 * 
 * @returns returns the object as the value of this function. 
 * 
 * @throws PostFixException if the stack is empty and there is no element to remove. 
 * 
 */
	public T pop() throws PostFixException { 
		T data;
		if(!empty()){ 
			data  = top.getData();
			top = top.getNext();
			}
		else { 
			throw new PostFixException("Pop attempted on an empty stack!");
		}
		return data;
	
	}
 /**
  * This method adds an element of generic type to the top of the stack. 
  * 
  * @param item item is the element of any type since it is generic that should be added to the stack. 
  */
	public void push(T item){ 
		MyNode<T> newNode = new MyNode<T>(item);
		newNode.setNext(top);
		top = newNode;
	}
	
	//I haven't figured out yet why this push method makes my program crash, but since 
	//it does, I used a really simple push implementation for my actual MyStack Class 
	
//	public void push(T item){ 
//		if (item != null){ 
//			MyNode<T> newNode = new MyNode <T> (item, null);
//			
//			if (top == null){ 
//				top = newNode; 
//			} else { 
//				MyNode<T> current = top; 
//				while(current.getNext()!= null){ 
//					current = current.getNext();
//					current.setNext(newNode);
//				}
//				size++;
//			}
//		}
//	}
	


/**
 * This method searches for a particular object in the stack. 
 * 
 * @param Object o is the object that is being looked for in the stack 
 * 
 * @return this method returns the l-based position where an object is on the stack. 
 */
	//this method is getting an error. It says that current needs a type, but I 
	//thought I already gave it a type generic with the MyNode<T> in the 3rd line of code for
	//this method. 
	//Eclipse suggested I get rid of the current = current.getNext(); and index++; 
	//but I feel like I need them to continue advancing the search to look for the 
	//object and to keep track of the count. 
	public int search(Object o){ 
		//index starts at 1 instead of 0 
		int index = 1; 
		MyNode<T> current;
		
		//Object o wasn't found in search 
		if(top == null){ 
			return -1; 
		} 
		else { 
			current = top; 
			while(current != null){ 
				//if Object o is equal to top, just return index because it was found 
				//at the top of the stack
				
				//if not, continue searching by looking at the next node and keeping count 
				if(((MyNode<T>) current).getData().equals(o)){ 
					return index;
					//current = current.getNext();
					//index ++;
				}
			}
			//Object o wasn't found in search 
			return -1; 
	}
}

/** 
 * This method converts a node of generic type to a String. 
 * 
 * @return returns the data as a String 
 *
 */
	public String toString(){ 
		
		StringBuffer s = new StringBuffer();
		MyNode<T> current = top; 
		while(current != null){ 
			s.append(current.getData().toString() + ",");
			current = current.getNext();
		}
		
		return "Stack: " + s; 
		
	}
	
	
	
}
