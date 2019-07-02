package sprint2.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2.product.Board;
import sprint2.product.Board.Cell;

public class TestNoughtMoves {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
		board.makeMove(1, 1);
	}

	@Test
	public void testNoughtTurn() {
		assertEquals("", board.getTurn(), Cell.NOUGHT);
	}

	@Test
	public void testNoughtTurnMoveVacantCell() {
		board.makeMove(0, 0); 
		assertEquals("", board.getTurn(), Cell.CROSS);
	}

	@Test
	public void testNoughtTurnMoveNonVacantCell() {
		board.makeMove(0, 0); 
		board.makeMove(0, 0); 
		assertEquals("", board.getTurn(), Cell.CROSS);
	}

	@Test
	public void testNoughtTurnInvalidRowMove() {
		board.makeMove(4, 0); 
		for (int row = 0; row <board.getTotalRows(); ++row) {
			for (int col = 0; col < board.getTotalColumns(); ++col) {
				if (row!=1 && col!=1)
					assertTrue("", board.getCell(row, col)==Cell.EMPTY); 
			}
		}
	}

	@Test
	public void testNoughtTurnInvalidColumnMove() {
		board.makeMove(0, 4); 
		for (int row = 0; row <board.getTotalRows(); ++row) {
			for (int col = 0; col < board.getTotalColumns(); ++col) {
				if (row!=1 && col!=1)
					assertTrue("", board.getCell(row, col)==Cell.EMPTY); 
			}
		}
	}

}
