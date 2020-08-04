/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package list;

public class RecursiveNode<E>{
	
	private E value;
	private RecursiveNode<E> next;
	
	public RecursiveNode(E value){
		this.value=value;
		next=null;
	}
	
	public void setValue(E value){
		this.value = value;
	}
	
	public E getValue(){
		return value;
	}
	
	public void setNext(RecursiveNode<E> next){
		this.next = next;
	}
	
	public RecursiveNode<E> getNext(){
		return next;
	}
	
	public int size() {
		if(next==null)
			return 1;
		else 
			return 1+next.size();
	}
	
	public boolean contains(E e) {
		if(value.equals(e))
			return true;
		if(next==null)
			return false;
		return next.contains(e);
	}
	
	
	public void setTail(RecursiveNode<E> newTail) {
		if(next==null)
		next=newTail;
		else
		next.setTail(newTail);
	}
	
	public StringBuilder generateString(StringBuilder builder) {
		if (next == null) { // next == null at LAST element in the linked list [0, 1, 2, 3] <-- 3 is last in list because 3.next = null
			builder.append(this.value); //you are at last element, so you stop the recursion
			return builder;
		}
		else {
			builder.append(this.value); // print THIS node's value
			builder.append(", ");
			return next.generateString(builder); // goes to next node
		}
	}
	
	public String toString() {
		return value.toString();
	}

}
