/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package search;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 * @author liberato
 *
 * @param <T> the type for each vertex in the search graph
 */
public class Searcher<T> {
	private final SearchProblem<T> searchProblem;
	
	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	public List<T> findSolution() {		
	  Queue<T> frontier = new LinkedList<>();
	  frontier.add(searchProblem.getInitialState());


	  Map<T, T> predecessor = new HashMap<>();
	  predecessor.put(searchProblem.getInitialState(), null);

	  List<T> path = new ArrayList<>();

	  while (!frontier.isEmpty()) {
	    T current = frontier.remove();
	    for (T next : searchProblem.getSuccessors(current)) {
	      if (!predecessor.containsKey(next)) {
	        frontier.add(next);
	        predecessor.put(next, current);
	      }
	    }
	    if (searchProblem.isGoal(current)) {
	      path.add(current);
	      T previous = predecessor.get(current);
	      while (previous != null) {
	        path.add(0, previous);
	        previous = predecessor.get(previous);
	      }
	      break;
	    }
	  }     
	  return path;
	}
	
	/**
	 * Determines what states are reachable from the start state in a
	 * certain number of moves
	 * 
	 * @param m the number of moves from the start state
	 * @return a set of nodes
	 */
	public Set<T> findReachableSet(int m) {		
		Set<T> reachable = new HashSet<T>();
		T current=searchProblem.getInitialState();
		reachable.add(current);
		if (m<=0) 
			return reachable;
		List<T> temp = searchProblem.getSuccessors(current);
		reachable.addAll(temp);
		for(int n=1;n<m;n++) {
			List<T> update = new ArrayList<>();
			for (int j = 0; j < temp.size(); j++) {
				List<T> successors = searchProblem.getSuccessors(temp.get(j));
				update.addAll(successors);
			}
			reachable.addAll(update);
			temp = update;
		}
		return reachable;
}
	/**
	 * Checks that a solution is valid.
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValidSolution(List<T> solution) {
		if(solution.equals(null))
			throw new NullPointerException();
		if(solution.size()<=1)
			return false;
		if(!solution.get(0).equals(searchProblem.getInitialState()) ||  !searchProblem.isGoal(solution.get(solution.size()-1)))
			return false;	
		for(int n=0;n<solution.size()-1;n++) {
		  if(!searchProblem.getSuccessors(solution.get(n)).contains(solution.get(n+1))) {
			 return false;
			}
		}
		return true;
		}
}
