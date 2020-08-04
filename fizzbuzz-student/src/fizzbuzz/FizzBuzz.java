/*
 * Copyright 2017 Marc Liberatore.
 * Updated by David Wemhoener
 */

package fizzbuzz;

public class FizzBuzz {
	private final int fizzNumber;
	private final int buzzNumber;

	/**
	 * Construct an object that can compute fizzbuzz values for a game of 
	 * fizz and buzz.
	 * 
	 * @param fizzNumber the fizz number; it must be greater than 1
	 * @param buzzNumber the buzz number; it must be greater 
	 * than 1 and not equal to the fizz number 
	 */
	public FizzBuzz(int fizzNumber, int buzzNumber) {
		this.fizzNumber= fizzNumber;	
		this.buzzNumber=buzzNumber;	
	}

	

	/**
	 * Returns the nth fizzbuzz value (a number, fizz, buzz, or fizzbuzz) 
	 * for this game of fizz and buzz.
	 * 
	 * For example, getValue(1) returns "1".
	 * 
	 * @param n
	 *            the number to consider; n > 0
	 * @return the fizzbuzz value, as a String
	 */
	public String getValue(int n) {
		if(n % fizzNumber==0 && n % buzzNumber==0)
			return"FizzBuzz";
		if (n%fizzNumber == 0) {
			return "Fizz";
		}
		if (n% buzzNumber ==0) {
			return "Buzz";
		}
		else {
			return Integer.toString(n)+'!';
		}   
	}

	/**
	 * Returns an array of the fizzbuzz values from start to end, inclusive, for
	 * this game of fizz and buzz.
	 * 
	 * For example, if the fizz number is 3 and the buzz number is 4,
	 * getValues(2,6) should return an array of Strings:
	 * 
	 * {"2", "Fizz", "Buzz", "5", "Fizz"}
	 * 
	 * @param start
	 *            the number to start from; start > 0
	 * @param end
	 *            the number to end at; end >= start
	 * @return the array of fizzbuzz values
	 */
	public String[] getValues(int start, int end) {
        String [] exactArray=new String[(end-start)+1];
		for(int n=0;n<exactArray.length;n++) { 
        exactArray[n]=getValue(start);
		start++;	
		}
		return exactArray;
	  }
	}

