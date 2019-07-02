package sprint3.product;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import sprint3.product.Board.Cell;
import sprint3.product.Board.GameState;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	public static final int CELL_SIZE = 100; // cell width and height (square)
	public static final int GRID_WIDTH = 8;                   // Grid-line's width
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2; // Grid-line's half-width

	// Symbols (cross/nought) are displayed inside a cell, with padding from border
	public static final int CELL_PADDING = CELL_SIZE / 6;
	public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; // width/height
	public static final int SYMBOL_STROKE_WIDTH = 8; // pen's stroke width

	private int CANVAS_WIDTH; 
	private int CANVAS_HEIGHT;

	private GameBoardCanvas gameBoardCanvas; 
	private JLabel gameStatusBar;  

	private Board board;

	public GUI(Board board) {
		this.board = board;
		setContentPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack(); 
		setTitle("Tic Tac Toe");
		setVisible(true);  
	}
	
	private void setContentPane(){
		gameBoardCanvas = new GameBoardCanvas();  
		CANVAS_WIDTH = CELL_SIZE * board.getTotalRows();  
		CANVAS_HEIGHT = CELL_SIZE * board.getTotalColumns();

		gameBoardCanvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
		
		gameStatusBar = new JLabel("  ");
		gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
		gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
		contentPane.add(gameStatusBar, BorderLayout.PAGE_END); 
	}

	class GameBoardCanvas extends JPanel {
		
		GameBoardCanvas(){
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {  
					if (board.getGameState() == GameState.PLAYING) {
						int rowSelected = e.getY() / CELL_SIZE;
						int colSelected = e.getX() / CELL_SIZE;
						board.makeMove(rowSelected, colSelected);
					} else {       // game over
						board.initBoard(); // restart the game
					}
					repaint();  // Call-back paintComponent().
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g) { 
			super.paintComponent(g);   
			setBackground(Color.WHITE);
			drawGridLines(g);
			drawBoard(g);
			printStatusBar();
		}
		
		private void drawGridLines(Graphics g){
			g.setColor(Color.LIGHT_GRAY);
			for (int row = 1; row < board.getTotalRows(); ++row) {
				g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
						CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
			}
			for (int col = 1; col < board.getTotalColumns(); ++col) {
				g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
						GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
			}

		}

		private void drawBoard(Graphics g){
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
			for (int row = 0; row < board.getTotalRows(); ++row) {
				for (int col = 0; col < board.getTotalColumns(); ++col) {
					int x1 = col * CELL_SIZE + CELL_PADDING;
					int y1 = row * CELL_SIZE + CELL_PADDING;
					if (board.getCell(row,col) == Cell.CROSS) {
						g2d.setColor(Color.RED);
						int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
						int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
						g2d.drawLine(x1, y1, x2, y2);
						g2d.drawLine(x2, y1, x1, y2);
					} else if (board.getCell(row,col) == Cell.NOUGHT) {
						g2d.setColor(Color.BLUE);
						g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
					}
				}
			}
		}

		private void printStatusBar(){
			if (board.getGameState() == GameState.PLAYING) {
				gameStatusBar.setForeground(Color.BLACK);
				if (board.getTurn() == Cell.CROSS) {
					gameStatusBar.setText("X's Turn");
				} else {
					gameStatusBar.setText("O's Turn");
				}
			} else if (board.getGameState() == GameState.DRAW) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("It's a Draw! Click to play again.");
			} else if (board.getGameState() == GameState.CROSS_WON) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("'X' Won! Click to play again.");
			} else if (board.getGameState()== GameState.NOUGHT_WON) {
				gameStatusBar.setForeground(Color.RED);
				gameStatusBar.setText("'O' Won! Click to play again.");
			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new GUI(new Board()); 
			}
		});
	}
}
