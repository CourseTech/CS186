/*
 * Copyright 2017 Marc Liberatore.
 */

package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * A Java class to simulate the card game War. See assignment writeup for details.
 * 
 * @author liberato
 *
 */
public class War {
	/**
	 * Determines the winner of a game of War, returning 1 if player A wins, -1 if player B wins, 0 if a draw.
	 * 
	 * The rules of the game are defined in the assignment writeup.
	 * 
	 * @param deck
	 * @return 1 if player A wins, -1 if player B wins, 0 if a draw
	 */
	Queue<Integer> deck1;
	Queue<Integer> deck2;
	List<Integer> spoils;
	boolean gameOver;
	int winner;
	int battlecount=0;
	
	public static int findWinner(List<Integer> deck) {
	War newwar= new War(deck);
	return newwar.simulateGame();	
	}
	
	public War(List<Integer> deck) {
		deck1= new LinkedList<>();
		deck2= new LinkedList<>();
		spoils= new ArrayList<>();			
		gameOver=false;
	
		for(int i=0;i<deck.size();i++) {
			if (i%2==0) {
				deck1.add(deck.get(i));
			}
			else {
				deck2.add(deck.get(i));
			}
		}
	}
	
	public int simulateGame() {
		while(!gameOver) {
			battle();
		}
		return winner;
	}
	
	public void battle() {
		if(battlecount==1000) {
			winner=0;
			gameOver=true;
		}
		else if(deck1.size()==0 && deck2.size()==0) { //draw
			winner=0;
			gameOver=true;
			battlecount++;
		}
		else if(deck1.size()==0) {
			winner=-1;
			gameOver=true;
			battlecount++;
		}
		else if(deck2.size()==0){
			winner=1;
			gameOver=true;
			battlecount++;
		}
		else {
		battlecount++;
		int card1=deck1.peek();
		int card2=deck2.peek();
		spoils.add(deck1.poll());
		spoils.add(deck2.poll());
		
		if (card1>card2) {
			deck1.addAll(spoils);
			spoils.removeAll(deck1);
		}
		else if(card2>card1) {
			deck2.addAll(spoils);
			spoils.removeAll(deck2);
		}
		else{
			war();
		}
		}
	}
	public void war() {
		if(battlecount==1000) {
			winner=0;
			gameOver=true;
		}
		else if(deck1.size()<3 && deck2.size()<3) {
			winner=0;
			gameOver=true;
		}
		else if(deck2.size()<3) {
			winner=1;
			gameOver=true;
		}
		else if(deck1.size()<3){
			winner=-1;
			gameOver=true;
		}
		else {
			spoils.add(deck1.poll());
			spoils.add(deck1.poll());
			spoils.add(deck1.poll());
			spoils.add(deck2.poll());
			spoils.add(deck2.poll());
			spoils.add(deck2.poll());
	}
}
}