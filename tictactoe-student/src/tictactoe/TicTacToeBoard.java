package tictactoe;

public class TicTacToeBoard {
	private enum Space {
		X, O, EMPTY;
		public String toString() {
			if (this == X) {
				return "X";
			}
			else if (this == O) {
				return "O";
			}
			else if (this == EMPTY) {
				return ".";
			}
			return "WTFBBQ";
		}
	}
	
	Space[] board;
	Space currentPlayer;
	
	public TicTacToeBoard() {
		board = new Space[9];
		for (int i = 0; i < board.length; i++) {
			board[i] = Space.EMPTY;
		}
		currentPlayer = Space.X;
	}
	
	public String toString() {
		String string = "";
		for (int i = 0; i < board.length; i++) {
			string = string + board[i];
			if (i % 3 == 2) {
				string = string + "\n";
			} else {
				string = string + " ";
			}
		}
		return string.substring(0, string.length() - 1);
	}

	private boolean isWinner(Space player) {
		// rows
		for (int row = 0; row < 3; row++) {
			if (board[row * 3].equals(player) &&
					board[row * 3 + 1].equals(player) &&
					board[row * 3 + 2].equals(player)) {
				return true;
			}
		}
		
		// cols
		for (int col = 0; col < 3; col++) {
			if (board[col].equals(player) && 
				board[col + 3].equals(player) && 
				board[col + 6].equals(player)) {
					return true;
				}
		}
		
		// diagonals
		if (board[0].equals(player) && 
				board[4].equals(player) && 
				board[8].equals(player)) {
					return true;
		}

		if (board[2].equals(player) && 
				board[4].equals(player) && 
				board[6].equals(player)) {
					return true;
		}
		
		return false;
}
	
	public boolean isGameOver() {
		if (isWinner(Space.X) || isWinner(Space.O) || isDraw()) {
			return true;
		}
		return false;
	}

	public boolean isDraw() {
		if (isWinner(Space.X) || isWinner(Space.O)) {
			return false;
		}
		
		for (Space s: board) {
			if (s.equals(Space.EMPTY)) {
				return false;
			}
		}
		
		return true;
	}
	
	public String getCurrentPlayer() {
		return currentPlayer.toString();
	}

	public void move(int move) {
		board[move] = currentPlayer;
		
		if (currentPlayer.equals(Space.X)) {
			currentPlayer = Space.O;
		}
		else {
			currentPlayer = Space.X;
		}
	}

	public String getResult() {
		if (isWinner(Space.X)) {
			return "X wins";
		}
		else if (isWinner(Space.O)) {
			return "O wins";
		}
		else if (isDraw()) {
			return "Draw";
		}
		else {
			return "Game ain't ovah.";
		}
	}
}
