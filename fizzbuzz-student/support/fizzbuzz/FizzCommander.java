/*
 * Copyright 2017 Marc Liberatore.
 * Updated by David Wemhoener
 */

package fizzbuzz;

import java.util.Scanner;

public class FizzCommander {
	public static void main(String[] args) {
		Scanner conIn = new Scanner(System.in);
		try {

			System.out.println("Enter a fizz number and a buzz number:");
			final int fizzNumber = conIn.nextInt();
			final int buzzNumber = conIn.nextInt();
			System.out.println("fizz number: " + fizzNumber);
			System.out.println("buzz number: " + buzzNumber);
			FizzBuzz hs = new FizzBuzz(fizzNumber, buzzNumber);
			
			System.out.println("Enter an integer greater than zero:");
			final int n = conIn.nextInt();
			System.out.println("The FizzBuzz value of " + n + " is: " + 
					hs.getValue(n));			
			
		} finally {
			conIn.close();
		}
	}
}