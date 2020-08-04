/*
 * Copyright 2017 Marc Liberatore.
 */

package simulation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class PeaceTest {
	@Test
	public void testEmpty() {
		assertEquals(0, Peace.findWinner(new ArrayList<Integer>()));
	}

	@Test
	public void testOne() {
		List<Integer> deck = Arrays.asList(2);
		assertEquals(1, Peace.findWinner(deck));
	}

	@Test
	public void testTwoA() {
		List<Integer> deck = Arrays.asList(3, 2);
		assertEquals(-1, Peace.findWinner(deck));
	}

	@Test
	public void testTwoB() {
		List<Integer> deck = Arrays.asList(2, 3);
		assertEquals(1, Peace.findWinner(deck));
	}
	
}
