/*
 * Copyright 2017 Marc Liberatore.
 */

package list.exercises;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class ExtendedArrayList<E> extends ArrayList<E> {
	
	/**
	 * Counts the number of elements in this list that are equal to e.
	 * 
	 * Uses .equals to check for equality. 
	 * 
	 * @param e the element to count
	 * @return the number of elements equal to e
	 */
	public int count(E e) {
		int count=0;
		for(int n=0;n<this.size();n++) {
			if(this.get(n).equals(e)) {
				count++;
			}
			
		}
		
		return count;
	}
	
	/**
	 * Rotates the list left n places.
	 * 
	 * Rotating a list left means moving the first item to the end of the list:
	 * 
	 * 1, 2, 3, 4, 5 when rotated left once becomes 2, 3, 4, 5, 1
	 * 
	 * A list is rotated right two places as follows:
	 * 
	 * 3, 4, 5, 1, 2
	 * 
	 * and so on.
	 * 
	 * @param n the distance to rotate the list left
	 */
	public void rotateLeft(int n) {
		while(n>0 && this.size()>0) {
			this.add(this.get(0));
			this.remove(0);
			n--;
		}
	}	
	
	/**
	 * Intersperses e between each existing element of the list.
	 * 
	 * For example, given the list: "hey", "ho", "hi", if we intersperse "yo" we get:
	 * "hey", "yo", "ho", "yo", "hi"
	 * 
	 * @param e the element to intersperse
	 */
	public void intersperse(E e) {
			for (int i = 0; i < this.size()-1; i++) {
				this.add(i+1,e);
				i++;
			}
		}
	
		
	
	
	/**
	 * Returns a copy of this list in reverse order.
	 * 
	 * This list is unmodified by this operation.
	 * 
	 * @return a reversed copy of the list
	 */
	public List<E> reversed() {
		List<E>newList=new ArrayList<E>(this.size());
		if(this!=null && !this.isEmpty()) {
			for(int n=this.size()-1;n>=0;n--) {
				newList.add(this.get(n));
			}
		}
	return newList;	
	}
	
	
}
