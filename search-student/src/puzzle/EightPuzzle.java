/*
 * Copyright 2017 Marc Liberatore.
 */

package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import search.SearchProblem;
import search.Searcher;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	List<Integer> startingValues = new ArrayList<>();
	public EightPuzzle(List<Integer> startingValues) {
		Set<Integer> nonDuplicates = new HashSet<>(startingValues);
		if (startingValues.size() != 9 || nonDuplicates.size() != 9) {
			throw new IllegalArgumentException();
		}
		for (int value : nonDuplicates) {
			if (value < 0 || value > 8) {
				throw new IllegalArgumentException();
			}
		}
		for (int value : startingValues) {
			this.startingValues.add(value);
		}
	}

	@Override
	public List<Integer> getInitialState() {
		return startingValues;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		List<List<Integer>> result = new ArrayList<>();
		if (currentState.indexOf(0) == 4) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			List<Integer> swap3 = new ArrayList<>(currentState);
			List<Integer> swap4 = new ArrayList<>(currentState);
			Collections.swap(swap1, 4, 1);
			result.add(swap1);
			Collections.swap(swap2, 4, 3);
			result.add(swap2);
			Collections.swap(swap3, 4, 5);
			result.add(swap3);
			Collections.swap(swap4, 4, 7);
			result.add(swap4);
		}
		else if (currentState.indexOf(0) == 0) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			Collections.swap(swap1, 0, 1);
			result.add(swap1);
			Collections.swap(swap2, 0, 3);
			result.add(swap2);
		}
		else if (currentState.indexOf(0) == 1) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			List<Integer> swap3 = new ArrayList<>(currentState);
			Collections.swap(swap1, 1, 0);
			result.add(swap1);
			Collections.swap(swap2, 1, 2);
			result.add(swap2);
			Collections.swap(swap3, 1, 4);
			result.add(swap3);
		}
		else if(currentState.indexOf(0) == 2) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			Collections.swap(swap1, 2, 1);
			result.add(swap1);
			Collections.swap(swap2, 2, 5);
			result.add(swap2);
		}
		else if(currentState.indexOf(0) == 3) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			List<Integer> swap3 = new ArrayList<>(currentState);
			Collections.swap(swap1, 3, 0);
			result.add(swap1);
			Collections.swap(swap2, 3, 4);
			result.add(swap2);
			Collections.swap(swap3, 3, 6);
			result.add(swap3);
		}
		else if (currentState.indexOf(0) == 5) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			List<Integer> swap3 = new ArrayList<>(currentState);
			Collections.swap(swap1, 5, 2);
			result.add(swap1);
			Collections.swap(swap2, 5, 4);
			result.add(swap2);
			Collections.swap(swap3, 5, 8);
			result.add(swap3);
		}
		else if (currentState.indexOf(0) == 6) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			Collections.swap(swap1, 6, 3);
			result.add(swap1);
			Collections.swap(swap2, 6, 7);
			result.add(swap2);
		}
		else if (currentState.indexOf(0) == 7) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			List<Integer> swap3 = new ArrayList<>(currentState);
			Collections.swap(swap1, 7, 4);
			result.add(swap1);
			Collections.swap(swap2, 7, 6);
			result.add(swap2);
			Collections.swap(swap3, 7, 8);
			result.add(swap3);
		}
		else if (currentState.indexOf(0) == 8) {
			List<Integer> swap1 = new ArrayList<>(currentState);
			List<Integer> swap2 = new ArrayList<>(currentState);
			Collections.swap(swap1, 8, 5);
			result.add(swap1);
			Collections.swap(swap2, 8, 7);
			result.add(swap2);
		}
		return result;
	}


	@Override
	public boolean isGoal(List<Integer> state) {
		if (state.get(state.size() - 1) != 0) {
			return false;
		}
		for (int i = 0; i < state.size() - 1; i++) {
			if (state.get(i) != (i + 1)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		EightPuzzle eightPuzzle = new EightPuzzle(Arrays.asList(new Integer[] {1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> solution = new Searcher<List<Integer>>(eightPuzzle).findSolution();
		for (List<Integer> state : solution) {
			System.out.println(state);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
