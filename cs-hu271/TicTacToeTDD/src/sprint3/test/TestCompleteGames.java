package sprint3.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint3.product.Board;
import sprint3.product.Board.GameState;
import sprint3.product.GUI;

public class TestCompleteGames {

	private Board board;
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testXWon() {
		board.makeMove(0, 0);
		board.makeMove(1, 1);		
		board.makeMove(0, 1);
		board.makeMove(1, 0);		
		board.makeMove(0, 2);
		assertEquals("", board.getGameState(), GameState.CROSS_WON); 
		new GUI(board); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testOWon() {
		board.makeMove(2, 2);		
		board.makeMove(0, 0);
		board.makeMove(1, 1);		
		board.makeMove(0, 1);
		board.makeMove(1, 0);		
		board.makeMove(0, 2);
		assertEquals("", board.getGameState(), GameState.NOUGHT_WON); 
		new GUI(board); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDraw() {
		board.makeMove(0, 1);		
		board.makeMove(0, 0);
		board.makeMove(0, 2);		
		board.makeMove(1, 2);
		board.makeMove(1, 0);		
		board.makeMove(1, 1);
		board.makeMove(2, 2);		
		board.makeMove(2, 0);
		board.makeMove(2, 1);
		assertEquals("", board.getGameState(), GameState.DRAW); 
		new GUI(board); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
