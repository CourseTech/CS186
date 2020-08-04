/*
 * Copyright 2017 Marc Liberatore.
 * Edited 2018 David Wemhoener.
 */

package sets;

import java.util.HashSet;
import java.util.Set;

public class SetUtilities {
	/**
	 * Returns a new set representing the union of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the union of s and t
	 */
	public static <E> Set<E> union(Set<E> s, Set<E> t) {
		Set<E>Union=new HashSet<E>();
		Union.addAll(s);
		Union.addAll(t);
		return Union;
	}

	/**
	 * Returns a new set representing the intersection of s and t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the intersection of s and t
	 */
	public static <E> Set<E> intersection(Set<E> s, Set<E> t) {
		Set<E> Intersect=new HashSet<E>();
		for(E same:s) {
		 if(t.contains(same)) {
		Intersect.add(same);
		}
	}
		return Intersect;
	}

	/**
	 * Returns a new set representing the set difference s and t,
	 * that is, s \ t.
	 * 
	 * Does not modify s or t.
	 * @param s
	 * @param t
	 * @return a new set representing the difference of s and t
	 */
	public static <E> Set<E> setDifference(Set<E> s, Set<E> t) {
		Set<E>Diff=new HashSet<E>();
		Diff.addAll(s);
		Diff.removeAll(t);
		return Diff;
	}
	
	   /**
     * Returns a new set representing the symmetric difference of s and t,
     * 
     * Does not modify s or t.
     * @param s
     * @param t
     * @return a new set representing the symmetric difference of s and t
     */
    public static <E> Set<E> symmetricDifference(Set<E> s, Set<E> t) {
       Set<E>Sym=new HashSet<E>();
       Sym.addAll(s);
       for(E diff:t) {
    	   if(s.contains(diff)) {
    		   Sym.remove(diff);
    		   }
    	   else if(!s.contains(diff)){
    		   Sym.add(diff);
    		   }
    	   }
    	   
       return Sym;
    }
    
	
	/**
	 * Returns the Jaccard index of the two sets s and t.
	 * 
	 * It is defined as 1 if both sets are empty.
     *
	 * Otherwise, it is defined as the size of the intersection of the sets, 
	 * divided by the size of the union of the sets.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return the Jaccard index of s and t
	 */
	
    public static <E> double jaccardIndex(Set<E> s, Set<E> t) {
    	if(s.isEmpty() && t.isEmpty()) { 
			return 1.0;
		}
		else if((!s.isEmpty()) && (!t.isEmpty())) {
			Set<E>Jaccard1=union(s,t);
			Set<E>Jaccard2=intersection(s,t);
		 return (double)Jaccard2.size()/ Jaccard1.size();
		}
		else
			return 0.0;
		}
}