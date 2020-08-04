package tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicTacToeBoardTest {

	@Test
	public void testEmptyBoardToString() {
		assertEquals(". . .\n. . .\n. . .", new TicTacToeBoard().toString());
	}
	
	@Test
	public void testPlayerChangesAfterMove() {
		TicTacToeBoard board = new TicTacToeBoard();
		assertEquals("X", board.getCurrentPlayer());
		board.move(0);
		assertEquals("O", board.getCurrentPlayer());
	}
	
    @Test
    public void testOneMove() {
        TicTacToeBoard board = new TicTacToeBoard();
        board.move(0);
        assertEquals("X . .\n. . .\n. . .", board.toString());
    }

    @Test
    public void testMultipleMoves() {
        TicTacToeBoard board = new TicTacToeBoard();
        board.move(0);
        assertEquals("X . .\n. . .\n. . .", board.toString());
        board.move(1);
        assertEquals("X O .\n. . .\n. . .", board.toString());
    }
    
    @Test
    public void testXWinsDiagonal() {
        TicTacToeBoard board = new TicTacToeBoard();
        board.move(0);
        board.move(1);
        board.move(4);
        board.move(2);
        board.move(8);
        assertEquals("X wins", board.getResult());
    }

}
