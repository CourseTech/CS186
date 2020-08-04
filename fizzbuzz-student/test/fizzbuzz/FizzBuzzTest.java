/*
 * Copyright 2017 Marc Liberatore.
 * Updated by David Wemhoener
 */

package fizzbuzz;

import static org.junit.Assert.*;

import org.junit.Test;


public class FizzBuzzTest {
	private final FizzBuzz FizzBuzzThreeFour = new FizzBuzz(3, 4);
	private final FizzBuzz FizzBuzzTwoFive = new FizzBuzz(2, 5);
	private final FizzBuzz FizzBuzzThreeSix = new FizzBuzz(6, 3);
	
	@Test
	public void testNotFizzOrBuzzGetValue() {
		assertEquals("getValue returns incorrect value", "2!",
				FizzBuzzThreeFour.getValue(2));
	}

	@Test
	public void testNotFizzOrBuzzGetValueAlt() {
		assertEquals("getValue returns incorrect value", "3!",
				FizzBuzzTwoFive.getValue(3));
	}

	@Test
	public void testFizzOrBuzzGetValue() {
		assertEquals("getValue returns incorrect value", "Fizz",
				FizzBuzzThreeFour.getValue(3));
		assertEquals("getValue returns incorrect value", "Buzz",
				FizzBuzzThreeFour.getValue(4));
	}

	@Test
	public void testFizzOrBuzzMultipleGetValue() {
		assertEquals("getValue returns incorrect value", "Fizz",
				FizzBuzzThreeFour.getValue(9));
		assertEquals("getValue returns incorrect value", "Buzz",
				FizzBuzzThreeFour.getValue(8));
	}

	@Test
	public void testFizzAndBuzzGetValue() {
		assertEquals("getValue returns incorrect value",
				"FizzBuzz", FizzBuzzThreeFour.getValue(12));
	}

	@Test
	public void testGetValuesSimple() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"1!", "2!", "Fizz", "Buzz" }, FizzBuzzThreeFour.getValues(1, 4));
	}

	@Test
	public void testGetValuesOffset() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"2!", "Fizz", "Buzz", "5!", "Fizz" },
				FizzBuzzThreeFour.getValues(2, 6));
	}

	@Test
	public void testNotFizzOrBuzzGetValueShared() {
		assertEquals("getValue returns incorrect value", "7!",
				FizzBuzzThreeSix.getValue(7));
	}

	@Test
	public void testFizzOrBuzzMultipleGetValueShared() {
		assertEquals("getValue returns incorrect value", "Buzz",
				FizzBuzzThreeSix.getValue(9));
	}

	@Test
	public void testFizzAndBuzzGetValueShared() {
		assertEquals("getValue returns incorrect value",
				"FizzBuzz", FizzBuzzThreeSix.getValue(6));
	}

	@Test
	public void testFizzOrBuzzGetValueAlt() {
		assertEquals("getValue returns incorrect value", "Fizz",
				FizzBuzzTwoFive.getValue(2));
		assertEquals("getValue returns incorrect value", "Fizz",
				FizzBuzzTwoFive.getValue(4));
		assertEquals("getValue returns incorrect value", "Buzz",
				FizzBuzzTwoFive.getValue(5));
	}

	@Test
	public void testFizzAndBuzzGetValueAlt() {
		assertEquals("getValue returns incorrect value", "FizzBuzz",
				FizzBuzzTwoFive.getValue(10));
	}


	@Test
	public void testGetValuesAlt() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"1!", "Fizz", "3!", "Fizz", "Buzz" },
				FizzBuzzTwoFive.getValues(1, 5));

		assertArrayEquals("getValues returns incorrect values", new String[] {
				"7!", "Fizz", "9!", "FizzBuzz", "11!" },
				FizzBuzzTwoFive.getValues(7, 11));
	}

	@Test
	public void testGetValuesShared() {
		assertArrayEquals("getValues returns incorrect values", new String[] {
				"Buzz", "4!", "5!", "FizzBuzz" }, FizzBuzzThreeSix.getValues(3, 6));
	}
}
