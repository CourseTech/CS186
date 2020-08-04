/*
 * Copyright 2017 Marc Liberatore.
 * Edited 2018 David Wemhoener
 */

package string.exercises;

public class StringExercises {
	/**
	 * Searches for "David" in a string.
	 * 
	 * @param string a non-null string
	 * @return the index of the first occurrence of "David" in string, or -1 if not found
	 */
	public static int findDavid(String string) {
		if(string.contains("David")) {
		int FirstIndex=string.indexOf("David");
		return FirstIndex;
		}
		return -1;
	}

	/**
	 * Searches for a substring within a string.
	 * @param string a non-null string
	 * @param substring a non-null string
	 * @return the index of the first occurrence of the substring within the string, or -1 if not found
	 */
	public static int findSubstring(String string, String substring) {
		if(string.contains(substring)) {
			int firstOccur=string.indexOf(substring);
			return firstOccur;
		}
		
		return -1;
	}
		
	/**
	 * Returns true if and only if the string contains the substring.
	 * @param string a non-null string
	 * @param substring a non-null string
	 * @return true if and only if the string contains the substring
	 */
	public static boolean contains(String string, String substring) {
		if(string.contains(substring))
	    return true;
		return false;
	}
	
	/**
	 * Splits a string into words, using whitespace to delimit the words.
	 * 
	 * See the assignment writeup for the magic argument to split().
	 * 
	 * @param string a non-null string
	 * @return an array representing the words in the string.
	 */
	public static String[] splitIntoWords(String string) {
		String[] Words= string.split("\\s+");
		return Words;
	}
	
	/**
	 * Returns the substring representing the first four characters of the string.
	 * @param string a non-null string of length >= 5
	 * @return the substring representing the first four characters of the string
	 */
	public static String firstFive(String string) {
		return string.substring(0,5);
	}

	/**
	 * Returns the substring representing the first n characters of the string.
	 * @param string a non-null string of length >= n
	 * @param n an integer >= 0
	 * @return the substring representing the first n characters of the string
	 */
	public static String firstN(String string, int n) {
		return string.substring(0,n);
	}
	
	/**
	 * Returns the substring representing the last four characters of the string.
	 * @param string a non-null string of length >= 4
	 * @return the substring representing the last four characters of the string
	 */
	public static String lastFour(String string) {
		return string.substring(string.length()-4,string.length());
	}

	/**
	 * Returns the substring representing the last n characters of the string.
	 * @param string a non-null string of length >= n
	 * @param n an integer >= 0
	 * @return the substring representing the last n characters of the string
	 */
	public static String lastN(String string, int n) {
		return string.substring(string.length()-n);
	}
}
