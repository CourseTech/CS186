/*
 * Copyright 2017 Marc Liberatore.
 * Edit 2018 David Wemhoener
 */

package list.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class ListExercises {

	/**
	 * Returns the count of characters in the longest string in the list.
	 * @param l a non-null list of strings
	 * @return the number of characters
	 */
	public static int findLongestStringLength(List<String> l) {
		int longest=0;
		for(int n=0; n<l.size();n++) {
			if(l.get(n).length()>longest) {
				longest=l.get(n).length();
			}
		}
		
		return longest;
	}
	
	/**
	 * Splits a string into words and returns a list of the words. 
	 * If the string is empty, split returns a list containing an empty string.
	 * 
	 * @param s a non-null string of zero or more words
	 * @return a list of words
	 */
	public static List<String> split(String s) {
			String[] intoWord=s.split("\\s+");
			List<String>Words=Arrays.asList(intoWord);
			return Words;
	}

	/**
	 * Returns a copy of the list of strings where each string has been 
	 * uppercased (as by String.toUpperCase).
	 * 
	 * The original list is unchanged.
	 * 
	 * @param l a non-null list of strings
	 * @return a list of uppercased strings
	 */
	public static List<String> uppercased(List<String> l){
		List<String>Uppercase=new ArrayList<String>(l.size());
		
		for(int n=0;n<=l.size()-1;n++){
			Uppercase.add(l.get(n).toUpperCase());
			}
		return Uppercase;
		
	}

	/**
	 * Returns true if and only if each string in the supplied list of strings
	 * starts with an uppercase letter. If the list is empty, returns false.
	 * 
	 * @param l a non-null list of strings
	 * @return true iff each string starts with an uppercase letter
	 */
	public static boolean allCapitalizedWords(List<String> l) {
		if(l.isEmpty() || l.contains("")) {
			return false;
		}
	for(int n=0;n< l.size();n++) {  
		if(!Character.isUpperCase(l.get(n).charAt(0))) {
			return false;
		}
		}
		return true;
}
	/**
	 * Returns a list of strings selected from a supplied list, which contain the character c.
	 * 
	 * The returned list is in the same order as the original list, but it omits all strings 
	 * that do not contain c.
	 * 
	 * The original list is unmodified.
	 * 
	 * @param l a non-null list of strings
	 * @param c the character to filter on
	 * @return a list of strings containing the character c, selected from l
	 */
	public static List<String> filterContaining(List<String> l, char c) {
		List<String>Contains=new ArrayList<String>(l.size());
		for(int n=0;n<l.size();n++) {
			for(int a=0;a<=l.get(n).length()-1;a++) {
				if(l.get(n).charAt(a)==c) {
				Contains.add(l.get(n));
				}
			}
		}
		return Contains;
	}
	
	/**
	 * Inserts a string into a sorted list of strings, maintaining the sorted property of the list.
	 * 
	 * @param s the string to insert
	 * @param l a non-null, sorted list of strings
	 */
	public static void insertInOrder(String s, List<String> l) {
	l.add(s);
	Collections.sort(l);
	}
}
