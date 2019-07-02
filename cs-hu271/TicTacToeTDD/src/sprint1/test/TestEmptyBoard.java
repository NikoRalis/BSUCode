package sprint1.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint1.product.Board;
import sprint1.product.Board.Cell;

public class TestEmptyBoard {

	private Board board = new Board();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testNewBoard() {
		for (int row = 0; row <board.getTotalRows(); ++row) {
			for (int col = 0; col < board.getTotalColumns(); ++col) {
				assertTrue("", board.getCell(row, col)==Cell.EMPTY); 
			}
		}
	}

	@Test
	public void testInvalidRow() {
		assertTrue("", board.getCell(3, 0)==null); 
	}

	@Test
	public void testInvalidColumn() {
		assertTrue("", board.getCell(0, 3)==null); 
	}
}
