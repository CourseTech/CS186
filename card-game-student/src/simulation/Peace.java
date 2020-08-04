/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemheoner.
 */

package simulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * A Java class to simulate the card game Peace. See assignment writeup for details.
 * 
 * @author liberato
 *
 */
public class Peace {
	private List<Integer>spoil;
	private static Queue<Integer>deck1;
	private static Queue<Integer>deck2;
	private int cap;
	private int outcome;
	private boolean gameOver;
	/**
	 * Determines the winner of a game of Peace, returning 1 if player A wins, -1 if player B wins, 0 if a draw.
	 * 
	 * The rules of the game are defined in the assignment writeup.
	 * 
	 * @param deck
	 * @return 
	 * @return 1 if player A wins, -1 if player B wins, 0 if a draw
	 */
	public Peace(List<Integer> deck) {
		deck1= new LinkedList<>();
		deck2= new LinkedList<>();
		spoil= new ArrayList<>();			
		gameOver=false;
	
		for(int n=0;n<deck.size();n+=2) {
				deck1.add(deck.get(n));
		}
		
		for(int a=1;a<deck.size();a+=2) {
			deck2.add(deck.get(a));
		}
	}	
	
	public void battle() {
		if(cap==1000) {
			outcome=0;
			gameOver=true;
		}
		else if(deck1.isEmpty() && deck2.isEmpty()) { //draw
			outcome=0;
			gameOver=true;
			cap++;
		}
		else if(deck1.isEmpty()) {
			outcome=-1;
			gameOver=true;
			cap++;
		}
		else if(deck2.isEmpty()){
			outcome=1;
			gameOver=true;
			cap++;
		}
		else {
		cap++;
		int card1=deck1.peek();
		int card2=deck2.peek();
		spoil.add(deck1.poll());
		spoil.add(deck2.poll());
		
		if (card1<card2) {
			deck1.addAll(spoil);
			spoil.removeAll(deck1);
		}
		else if(card2<card1) {
			deck2.addAll(spoil);
			spoil.removeAll(deck2);
		}
		else{
			war();
			}
		}
	}
	
	public void war() {
		if(cap==1000) {
			outcome=0;
			gameOver=true;
		}
		else if(deck1.size()<3 && deck2.size()<3) {
			outcome=0;
			gameOver=true;
		}
		else if(deck2.size()<3) {
			outcome=1;
			gameOver=true;
		}
		else if(deck1.size()<3){
			outcome=-1;
			gameOver=true;
		}
		else {
		for(int n=0;n<3;n++) {
			spoil.add(deck1.poll());
		}
		for(int a=0;a<3;a++) {
			spoil.add(deck2.poll());
			}
		}
	}
	
	public int simulateGame() {
		while(!gameOver) {
			battle();
		}
		return outcome;
	}
	
	public static int findWinner(List<Integer> deck) {
		Peace p=new Peace(deck);
		return p.simulateGame();
		}
	}
