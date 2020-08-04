/*
 * Copyright 2017 Marc Liberatore.
 * Edited 2018 David Wemhoener.
 */

package similarity;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class SimilarityUtilitiesTest {
//	@Rule
//	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	String simple = "Apple  \n   Banana  \n Clementine Durian\n";
	String lessSimple = "\nApple  \n \n  Banana  \n\n Clementine Durian\n";

	List<String> simpleList;

	String template = "Complete the following sentence:\nCOMPSCI 186 is ...\n";
	String good = "Complete the following sentence:\nCOMPSCI 186 is a perfectly cromulent class.\n";
	String bad = "Complete the following sentence:\nCOMPSCI 186 is a hot mess.\n";
	String containsEmpty = "Complete the following sentence:\nCOMPSCI 186 is ...\n\nThat's a tricky one.\n\nI'm not sure how I'd answer that question.\n";

	@Before
	public void setup() {
		simpleList = Arrays.asList("a", "b", "c", "d", "e");
	}

	@Test
	public void testEmptyTrimmedLines() {
		assertEquals(new HashSet<String>(), SimilarityUtilities.trimmedLines(""));
	}

	@Test
	public void testSimpleTrimmedLines() {
		assertEquals(new HashSet<String>(Arrays.asList("Apple", "Banana", "Clementine Durian")),
				SimilarityUtilities.trimmedLines(simple));
	}

	@Test
	public void testContainsEmptyTrimmedLines() {
		assertEquals(new HashSet<String>(Arrays.asList("Apple", "Banana", "Clementine Durian")),
				SimilarityUtilities.trimmedLines(lessSimple));
	}

	@Test
	public void testAsLowercaseWordsEmpty() {
		assertTrue(SimilarityUtilities.asLowercaseWords("").isEmpty());
	}

	@Test
	public void testAsLowercaseWordsEmptyLines() {
		assertTrue(SimilarityUtilities.asLowercaseWords(" \n\n  \n    \n\n\n").isEmpty());
	}

	@Test
	public void testAsLowercaseWordsSimple() {
		assertEquals(Arrays.asList("apple", "banana", "clementine", "durian"),
				SimilarityUtilities.asLowercaseWords(simple));
	}

	@Test
	public void testAsLowercaseWordsLessSimple() {
		assertEquals(Arrays.asList("apple", "banana", "clementine", "durian"),
				SimilarityUtilities.asLowercaseWords(lessSimple));
	}

	@Test
	public void testAsLowercaseWordsEvenLessSimple() {
		assertEquals(Arrays.asList("apple", "q", "banana", "clementine", "durian"),
				SimilarityUtilities.asLowercaseWords("\nApple.  \nq\n  Banana  \n\n Clementine;Durian\n"));
	}

	@Test
	public void testLineSimilarityIdentical() {
		assertEquals(1.0, SimilarityUtilities.lineSimilarity(simple, simple), 0.0);
	}

	@Test
	public void testLineSimilarityEquivalent() {
		assertEquals(1.0, SimilarityUtilities.lineSimilarity(simple, lessSimple), 0.0);
	}

	@Test
	public void testLineSimilarityGoodBad() {
		assertEquals(0.333, SimilarityUtilities.lineSimilarity(good, bad), 0.001);
	}

	@Test
	public void testLineSimilarityGoodBadTemplate() {
		assertEquals(0.0, SimilarityUtilities.lineSimilarity(good, bad, template), 0.0);
	}

	@Test
	public void testLineSimilarityBadGood() {
		assertEquals(0.333, SimilarityUtilities.lineSimilarity(bad, good), 0.001);
	}

	@Test
	public void testLineSimilarityGoodContainsEmpty() {
		assertEquals(0.2, SimilarityUtilities.lineSimilarity(good, containsEmpty), 0.0);
	}

	@Test
	public void testLineSimilarityGoodContainsEmptyTemplate() {
		assertEquals(0.0, SimilarityUtilities.lineSimilarity(good, containsEmpty, template), 0.0);
	}

	@Test
	public void testShingleSimple() {
		assertTrue(SimilarityUtilities.shingle(Arrays.asList(), 1).isEmpty());
	}

	@Test
	public void testShingleOne() {
		assertEquals(new HashSet<String>(simpleList), SimilarityUtilities.shingle(simpleList, 1));
	}

	@Test
	public void testShingleTwo() {
		assertEquals(new HashSet<String>(Arrays.asList("ab", "bc", "cd", "de")),
				SimilarityUtilities.shingle(simpleList, 2));
	}

	@Test
	public void testShingleThree() {
		assertEquals(new HashSet<String>(Arrays.asList("abc", "bcd", "cde")),
				SimilarityUtilities.shingle(simpleList, 3));
	}

	@Test
	public void testShingleFour() {
		assertEquals(new HashSet<String>(Arrays.asList("abcd", "bcde")), SimilarityUtilities.shingle(simpleList, 4));
	}

	@Test
	public void testShingleFive() {
		assertEquals(new HashSet<String>(Arrays.asList("abcde")), SimilarityUtilities.shingle(simpleList, 5));
	}

	@Test
	public void testShingleTooMany() {
		assertEquals(new HashSet<String>(), SimilarityUtilities.shingle(simpleList, 6));
	}

	@Test
	public void testWordSimilarityIdentical() {
		assertEquals(1.0, SimilarityUtilities.shingleSimilarity(simple, simple, "", 1), 0.0);
	}

	@Test
	public void testWordSimilarityEquivalent() {
		assertEquals(1.0, SimilarityUtilities.shingleSimilarity(simple, simple, "", 1), 0.0);
	}

	@Test
	public void testWordSimilarityGoodBad() {
		assertEquals(0.615, SimilarityUtilities.shingleSimilarity(good, bad, "", 1), 0.001);
	}

	@Test
	public void testWordSimilarityGoodBadTemplate() {
		assertEquals(0.166, SimilarityUtilities.shingleSimilarity(good, bad, template, 1), 0.001);
	}

	@Test
	public void testWordSimilarityBadGood() {
		assertEquals(0.615, SimilarityUtilities.shingleSimilarity(bad, good, "", 1), 0.001);
	}

	@Test
	public void testWordSimilarityGoodContainsEmpty() {
		assertEquals(0.348, SimilarityUtilities.shingleSimilarity(good, containsEmpty, "", 1), 0.001);
	}

	@Test
	public void testWordSimilarityGoodContainsEmptyTemplate() {
		assertEquals(0.063, SimilarityUtilities.shingleSimilarity(good, containsEmpty, template, 1), 0.001);
	}

	@Test
	public void testPhraseSimilarityIdentical() {
		assertEquals(1.0, SimilarityUtilities.shingleSimilarity(simple, simple, "", 3), 0.0);
	}

	@Test
	public void testPhraseSimilarityEquivalent() {
		assertEquals(1.0, SimilarityUtilities.shingleSimilarity(simple, simple, "", 3), 0.0);
	}

	@Test
	public void testPhraseSimilarityGoodBad() {
		assertEquals(0.545, SimilarityUtilities.shingleSimilarity(good, bad, "", 3), 0.001);
	}

	@Test
	public void testPhraseSimilarityGoodBadTemplate() {
		assertEquals(0.166, SimilarityUtilities.shingleSimilarity(good, bad, template, 3), 0.001);
	}

	@Test
	public void testPhraseSimilarityBadGood() {
		assertEquals(0.545, SimilarityUtilities.shingleSimilarity(bad, good, "", 3), 0.001);
	}

	@Test
	public void testPhraseSimilarityGoodContainsEmpty() {
		assertEquals(0.208, SimilarityUtilities.shingleSimilarity(good, containsEmpty, "", 3), 0.001);
	}

	@Test
	public void testPhraseSimilarityGoodContainsEmptyTemplate() {
		assertEquals(0.0, SimilarityUtilities.shingleSimilarity(good, containsEmpty, template, 3), 0.0);
	}
	
}
