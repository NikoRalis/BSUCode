package sprint2.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2.product.Board;
import sprint2.product.Board.Cell;

public class TestCrossMoves {

	private Board board;

	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialCrossTurn() {
		assertEquals("", board.getTurn(), Cell.CROSS);
	}

	@Test
	public void testCrossTurnMoveVacantCell() {
		board.makeMove(0, 0); 
		assertEquals("", board.getTurn(), Cell.NOUGHT);
	}

	@Test
	public void testCrossTurnMoveNonVacantCell() {
		board.makeMove(0, 0); 
		board.makeMove(0, 0); 
		assertEquals("", board.getTurn(), Cell.NOUGHT);
	}

	@Test
	public void testCrossTurnInvalidRowMove() {
		board.makeMove(4, 0); 
		for (int row = 0; row <board.getTotalRows(); ++row) {
			for (int col = 0; col < board.getTotalColumns(); ++col) {
				assertTrue("", board.getCell(row, col)==Cell.EMPTY); 
			}
		}
	}

	@Test
	public void testCrossTurnInvalidColumnMove() {
		board.makeMove(0, 4); 
		for (int row = 0; row <board.getTotalRows(); ++row) {
			for (int col = 0; col < board.getTotalColumns(); ++col) {
				assertTrue("", board.getCell(row, col)==Cell.EMPTY); 
			}
		}
	}

}
