package tictactoe;

import java.io.IOException;
import java.util.Scanner;

public class TicTacToeDriver {

	public static void main(String[] args) {
		TicTacToeBoard board = new TicTacToeBoard();

		try (Scanner scanner = new Scanner(System.in)) {
			while (!board.isGameOver()) {
				System.out.println(board);
				System.out.println("Player " + board.getCurrentPlayer() + ", choose a move (0-8):");
				int move = scanner.nextInt();
				board.move(move);
			}
			System.out.println("Game result: " + board.getResult());
		}
	}

}
