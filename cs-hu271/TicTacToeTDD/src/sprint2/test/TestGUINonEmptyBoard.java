package sprint2.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sprint2.product.Board;
import sprint2.product.GUI;

public class TestGUINonEmptyBoard {
	private Board board;
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		board.makeMove(0, 0);
		board.makeMove(1, 1);		
		new GUI(board); 
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
