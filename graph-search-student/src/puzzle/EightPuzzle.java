/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
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
	private List<Integer>starting=new ArrayList<>();
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
	public EightPuzzle(List<Integer> startingValues) {
	Set<Integer>exact=new HashSet<>(startingValues);
	if(startingValues.size()!=9 || exact.size()!=9) {
		throw new IllegalArgumentException();
	}
	for(Integer a: exact) {
		if(a<0 || a >8) {
			throw new IllegalArgumentException();
			}
	this.starting=startingValues;
		}
	}

	@Override
	public List<Integer> getInitialState() {
		return starting;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		List<List<Integer>>output= new ArrayList<>();
		List<Integer>swap1=new ArrayList<>(currentState);
		List<Integer>swap2=new ArrayList<>(currentState);
		List<Integer>swap3=new ArrayList<>(currentState);
		List<Integer>swap4=new ArrayList<>(currentState);
		if(currentState.indexOf(0)==4) {
			Collections.swap(swap1,4,1);
			output.add(swap1);
			Collections.swap(swap2,4,3);
			output.add(swap2);
			Collections.swap(swap3,4,5);
			output.add(swap3);
			Collections.swap(swap4,4,7);
			output.add(swap4);
		}
		
		else if(currentState.indexOf(0)==0) {
			Collections.swap(swap1,0,1);
			output.add(swap1);
			Collections.swap(swap2,0,3);
			output.add(swap2);
		}	
		
		else if(currentState.indexOf(0)==1) {
			Collections.swap(swap1,1,0);
			output.add(swap1);
			Collections.swap(swap2,1,2);
			output.add(swap2);
			Collections.swap(swap3,1,4);
			output.add(swap3);
		}
		
		else if(currentState.indexOf(0)==2) {
			Collections.swap(swap1,2,1);
			output.add(swap1);
			Collections.swap(swap2,2,5);
			output.add(swap2);
		}
	
		else if(currentState.indexOf(0)==3) {
			Collections.swap(swap1,3,0);
			output.add(swap1);
			Collections.swap(swap2,3,4);
			output.add(swap2);
			Collections.swap(swap3,3,6);
			output.add(swap3);
		}
		
		else if(currentState.indexOf(0)==5) {
			Collections.swap(swap1,5,2);
			output.add(swap1);
			Collections.swap(swap2,5,4);
			output.add(swap2);
			Collections.swap(swap3,5,8);
			output.add(swap3);
		}
		
		else if(currentState.indexOf(0)==6) {
			Collections.swap(swap1,6,3);
			output.add(swap1);
			Collections.swap(swap2,6,7);
			output.add(swap2);
		}
		else if(currentState.indexOf(0)==7) {
			Collections.swap(swap1,7,4);
			output.add(swap1);
			Collections.swap(swap2,7,6);
			output.add(swap2);
			Collections.swap(swap3,7,8);
			output.add(swap3);
		}
		else if(currentState.indexOf(0)==8) {
			Collections.swap(swap1,8,5);
			output.add(swap1);
			Collections.swap(swap2,8,7);
			output.add(swap2);
		}
	 return output;	
	}
	


	@Override
	public boolean isGoal(List<Integer> state) {
		if(state.get(state.size()-1)!=0)
			return false;
		for(int i=0;i<state.size()-1;i++) {
			if(state.get(i)!=i+1)
				return false;
	}
		return true;
	}
	
	public static void printState(List<Integer> state) {
		String rowOne = state.get(0).toString() + state.get(1).toString() + state.get(2).toString();
		String rowTwo = state.get(3).toString() + state.get(4).toString() + state.get(5).toString();
		String rowThree = state.get(6).toString() + state.get(7).toString() + state.get(8).toString();
		System.out.println(rowOne);
		System.out.println(rowTwo);
		System.out.println(rowThree);
		System.out.println();
	}
	
	public static void main(String[] args) {
		EightPuzzle eightPuzzle = new EightPuzzle(Arrays.asList(new Integer[] {1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> solution = new Searcher<List<Integer>>(eightPuzzle).findSolution();
		for (List<Integer> state : solution) {
			System.out.println(state);
			printState(state);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
