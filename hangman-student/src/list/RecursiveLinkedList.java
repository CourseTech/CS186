/*
 * Copyright 2017 Neena Liberatore.
 * Modified 2018 David Wemhoener.
 */

package list;

public class RecursiveLinkedList<E> {
	private RecursiveNode<E> head;
	
	
	public RecursiveLinkedList() {
		this.head = null;
	}
	
	public RecursiveNode<E> getHead(){
		return head;
	}
	
	/**
	 * 
	 * @return the number of elements in this list
	 */
	public int size() {
		if(head==null) 
			return 0;
		else
		return head.size();
	}
	
	/**
	 * 
	 * @param e the element search for
	 * @return true iff the list contains an element of whose value equals that of e
	 */
	public boolean contains(E e) {
		if (head == null) {
			return false;
		}
		else {
			return head.contains(e);
		}
	}
	
	/**
	 * Appends the element e to the end of the list.
	 * 
	 * @param e the value to append
	 */
	public void append(E e) {
		RecursiveNode<E>append= new RecursiveNode<E>(e);
		if (head==null) {
		head=append;
		}
		else {
			head.setTail(append);
			}
		}
	
	
	
	@Override
	public String toString() {
		StringBuilder build=new StringBuilder();
		if (head == null) {
			return "";
		}
		return head.generateString(build).toString();
	}		
}

