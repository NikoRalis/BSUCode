import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * @author Nik Morales
 * This is the Mine Walker Panel where all the magic happens
 */
public class MineWalkerPanel extends JPanel implements ActionListener {
	private int grid = 10, num;
	private JButton[][] buttons = new JButton[grid][grid];
	private boolean minePath[][];
	private ArrayList<Point> safePath;
	private JButton newGame = new JButton(" New Game ");
	private JButton showMines = new JButton("Show Mines");
	private JButton showPath = new JButton(" Show Path ");
	private JPanel topPanel = new JPanel();
	private JPanel gameBoard = new JPanel();
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	private JPanel keyPanel = new JPanel();
	private JPanel bottomPanel = new JPanel();
	private Point currPoint, newPoint;
	private int findMine = 0, again, againWin;
	private JButton winner;
	private final int DELAY = 100; //milliseconds

	// game stats
	private int livesLeft = 5;
	private int gameScore = 500;
	private JLabel lives, score;

	JLabel gridSize = new JLabel("Grid Size");
	JTextField textField = new JTextField(5);

	Random rand = new Random();
	Font myFont = new Font("Arial", 0, 18);

	/**
	 * This method sets the panel in a border layout and sets
	 * the Panels accordingly
	 */
	public MineWalkerPanel() {
		this.setLayout(new BorderLayout());
		BottomPanel();
		TopPanel();
		RightPanel();
		LeftPanel();
		GameBoard();
		Mines();
	}

	/**
	 * this is the original game board that creates the 
	 * buttons and grid layout and implements the random walk
	 */
	public void GameBoard() {
		gameBoard.setLayout(new GridLayout(grid, grid));
		gameBoard.setPreferredSize(new Dimension(450, 450));
		
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				buttons[i][j] = new JButton("");
				buttons[i][j].setBackground(Color.GRAY);
				buttons[i][j].addActionListener(this);
				gameBoard.add(buttons[i][j]);
				buttons[i][j].setFont(myFont);
				RandomWalk RandomWalk1 = new RandomWalk(grid);
				RandomWalk1.createWalk();
				safePath = RandomWalk1.getPath();
				add(gameBoard, BorderLayout.CENTER);
				buttons[0][0].setBackground(Color.CYAN);
			}
		}
		
		winner = buttons[0][0];
		Post();
		

	}

	/**
	 * This method creates the Top panel with the title
	 */
	public void TopPanel() {
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
		JLabel mineWalker = new JLabel(" MineWalker ", JLabel.CENTER);
		mineWalker.setFont(myFont);
		topPanel.add(mineWalker);
	}

	/**
	 * This method creates the right panel and adds the buttons and their prompts
	 */
	public void RightPanel() {
		rightPanel.setPreferredSize(new Dimension(150, 150));
		rightPanel.add(newGame);
		rightPanel.add(showMines);
		rightPanel.add(showPath);
		showPath.setToolTipText("Here's some training wheels.");
		showMines.setToolTipText("You really going to cheat? cool.");
		newGame.setToolTipText("Giving up? Keep that mentality, you'll go far.");

		rightPanel.add(gridSize);
		rightPanel.add(textField);

		newGame.addActionListener(this);
		showMines.addActionListener(this);
		showPath.addActionListener(this);
	}

	/**
	 * This method is for the left panel that holds the key
	 */
	public void LeftPanel() {
		keyPanel.setLayout(new GridLayout(7, 1));
		keyPanel.setBorder(BorderFactory.createTitledBorder("Key"));
		leftPanel.add(keyPanel);

		// 0 mines
		JLabel zero = new JLabel(" 0 Nearby Mines ", JLabel.CENTER);
		zero.setBackground(Color.GREEN);
		zero.setOpaque(true);
		keyPanel.add(zero);

		// 1 mine
		JLabel one = new JLabel(" 1 Nearby Mine ", JLabel.CENTER);
		one.setBackground(Color.YELLOW);
		one.setOpaque(true);
		keyPanel.add(one);

		// 2 mines
		JLabel two = new JLabel(" 2 Nearby Mines ", JLabel.CENTER);
		two.setBackground(Color.ORANGE);
		two.setOpaque(true);
		keyPanel.add(two);

		// 3 mines
		JLabel three = new JLabel(" 3 Nearby Mines ", JLabel.CENTER);
		three.setBackground(Color.RED);
		three.setOpaque(true);
		three.setPreferredSize(new Dimension(150, 30));
		keyPanel.add(three);

		// exploded mine
		JLabel exp = new JLabel(" Exploded Mine ", JLabel.CENTER);
		exp.setBackground(Color.BLACK);
		Color myWhite = new Color(255, 254, 255);
		exp.setForeground(myWhite);
		exp.setOpaque(true);
		keyPanel.add(exp);

		// X Current Position
		JLabel currentPo = new JLabel(" 'X' Current Position ", JLabel.CENTER);
		currentPo.setBackground(Color.GRAY);
		currentPo.setOpaque(true);
		currentPo.setPreferredSize(new Dimension(150, 30));
		keyPanel.add(currentPo);

		// Destination
		JLabel dest = new JLabel(" Destination ", JLabel.CENTER);
		dest.setBackground(Color.CYAN);
		dest.setOpaque(true);
		dest.setPreferredSize(new Dimension(150, 30));
		keyPanel.add(dest);
	}

	/**
	 * The bottom panel displays the lives and score updating with every move
	 */
	public void BottomPanel() {
		bottomPanel.setPreferredSize(new Dimension(100, 100));
		lives = new JLabel("Lives: " + livesLeft);
		score = new JLabel("Score: " + gameScore);
		bottomPanel.add(lives);
		bottomPanel.add(score);

		// Set up Border
		setLayout(new BorderLayout());
		add(topPanel, BorderLayout.NORTH);
		add(gameBoard, BorderLayout.CENTER);
		add(rightPanel, BorderLayout.EAST);
		add(leftPanel, BorderLayout.WEST);
		add(bottomPanel, BorderLayout.SOUTH);
	}

	/**
	 * @param newPoint
	 * @return
	 * This is used to insure that your next step is valid
	 */
	public boolean isValid(Point newPoint) {
		if (newPoint.x == currPoint.x && newPoint.y == currPoint.y - 1) {
			return true;
		} else if (newPoint.x == currPoint.x - 1 && newPoint.y == currPoint.y) {
			return true;
		} else if (newPoint.x == currPoint.x + 1 && newPoint.y == currPoint.y) {
			return true;
		} else if (newPoint.x == currPoint.x && newPoint.y == currPoint.y + 1) {
			return true;
		}
		return false;
	}

	/**
	 * This method creates the mines and makes sure there aren't too many
	 */
	public void Mines() {
		minePath = new boolean[grid][grid];
		int numMines = (((grid * grid) - safePath.size()) / 4);
		int x = rand.nextInt(grid);
		int y = rand.nextInt(grid);
		for (int m = 0; m < numMines; m++) {
			while (numMines >= 0) {
				while (minePath[x][y] == true
						|| safePath.contains(new Point(x, y))) {
					x = rand.nextInt(grid);
					y = rand.nextInt(grid);
				}
				minePath[x][y] = true;
				numMines--;
			}
		}
	}

	/**
	 * When this method is called a new gamebaord is created to replace the old one
	 */
	public void newGameBoard() {
		textField.setEditable(true);
		this.remove(gameBoard);
		gameBoard = new JPanel();
		gameBoard.setLayout(new GridLayout(grid, grid));
		gameBoard.setPreferredSize(new Dimension(450, 450));
		buttons = new JButton[grid][grid];
		lives.setText("Lives: " + 5);
		score.setText("Score: " + 500);
		

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new JButton("");
				buttons[i][j].setBackground(Color.GRAY);
				buttons[i][j].addActionListener(this);
				gameBoard.add(buttons[i][j]);
				buttons[i][j].setFont(myFont);
				RandomWalk RandomWalk1 = new RandomWalk(grid);
				RandomWalk1.createWalk();
				safePath = RandomWalk1.getPath();
				add(gameBoard, BorderLayout.CENTER);
				buttons[0][0].setBackground(Color.CYAN);
				livesLeft = 5;
				gameScore = 500;
				Mines();
				
			}
		}
		
		this.add(gameBoard);
		winner = buttons[0][0];
		Post();
		this.revalidate();
		ChangeColor();
	}

	/**
	 * this method is it find your current position
	 */
	public void Post() {

		currPoint = new Point(grid - 1, grid - 1);
		buttons[currPoint.x][currPoint.y].setText("X");
		
	}

	/**
	 * This method is used to move
	 */
	public void Move() {
		buttons[currPoint.x][currPoint.y].setText("");
		currPoint = newPoint;
		buttons[currPoint.x][currPoint.y].setText("X");
		gameScore = gameScore - 5;
		score.setText("SCORE: " + gameScore);
		startAnimation();
	}

	/**
	 * @return the mines that was found around you
	 * 
	 */
	public int CheckMines() {
		findMine = 0;
		if (currPoint.y > 0) // up
		{
			if (minePath[currPoint.x][currPoint.y - 1] == true) {
				findMine += 1;
			}
		}

		if (currPoint.y < grid - 1) // down
		{
			if (minePath[currPoint.x][currPoint.y + 1] == true) {
				findMine += 1;
			}
		}
		if (currPoint.x < grid - 1) // right
		{
			if (minePath[currPoint.x + 1][currPoint.y] == true) {
				findMine += 1;
			}
		}
		if (currPoint.x > 0) // left
		{
			if (minePath[currPoint.x - 1][currPoint.y] == true) {
				findMine += 1;
			}
		}
		return findMine;
	}

	/**
	 * using the CheckMines method this method decides which color to change your block to 
	 */
	public void ChangeColor() {
		CheckMines();
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				switch (findMine) {
				case 0:
					buttons[currPoint.x][currPoint.y]
							.setBackground(Color.GREEN);
					break;
				case 1:
					buttons[currPoint.x][currPoint.y]
							.setBackground(Color.YELLOW);
					break;
				case 2:
					buttons[currPoint.x][currPoint.y]
							.setBackground(Color.ORANGE);
					break;
				case 3:
					buttons[currPoint.x][currPoint.y].setBackground(Color.RED);
					break;
				}
			}
		}
	}
	
	/**
	 * This is the method that is called when you run out of lives or run your score down to 0
	 */
	public void PopUpLose() {
		again = JOptionPane
				.showConfirmDialog(this,
						"Not good enough, would you like to make a fool of yourself again?");

		if (again == JOptionPane.YES_OPTION) {
			try {
				num = Integer.parseInt(textField.getText());
			} catch (NumberFormatException nfe) {
				num = 10;
				textField.setText("" + num);
			}

			grid = num;
			newGameBoard();
		} else if (againWin == JOptionPane.NO_OPTION) {
			DisableGrid();
		}
	}

	/**
	 * This is the method that is called when you reach the destination
	 */
	public void PopUpWin() {
		again = JOptionPane.showConfirmDialog(this,
				"I guess you did alright, think you can do it again?");

		if (again == JOptionPane.YES_OPTION) {
			try {
				num = Integer.parseInt(textField.getText());
			} catch (NumberFormatException nfe) {
				num = 10;
				textField.setText("" + num);
			}

			grid = num;
			newGameBoard();
		} else if (againWin == JOptionPane.NO_OPTION) {
			DisableGrid();
			return;
		}
	}

	/**
	 * I created this method so I could disable the grid when I wanted to
	 * I call this when you show mines or the path, I also call this if you
	 * select no from one of the pop up menus
	 */
	public void DisableGrid() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				buttons[i][j].removeActionListener(this);
			}
		}
	}

	/**
     * Performs action when timer event fires.
     */
	private class TimerActionListener implements ActionListener
{
	public void actionPerformed(ActionEvent evt)
	{
             // toggle color/text for current tile
	        // do other periodic checks if needed
		buttons[currPoint.x][currPoint.y].setForeground(Color.WHITE);
		repaint();
		buttons[currPoint.x][currPoint.y].setForeground(Color.BLACK);
		repaint();
		
	}
}

   /**
    * Create an animation thread that runs periodically
    */
    private void startAnimation()
    {
	    TimerActionListener taskPerformer = new TimerActionListener();
	    new Timer(DELAY, taskPerformer).start();
	    
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons.length; j++) {
				if (e.getSource() == buttons[i][j]) {
					newPoint = new Point(i, j);
					if (isValid(newPoint)) {
						if (minePath[i][j] == true) {
							source.setBackground(Color.BLACK);
							lives.setText("LIVES: " + --livesLeft);
							score.setText("SCORE: " + (gameScore - 10));

						} else {
							Move();
							ChangeColor();

							currPoint = newPoint;
							gameScore = gameScore;
						}
					}
				}
			}
		}
		

		// NEW GAME
		if (source.equals(newGame)) {
			Mines();

			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons.length; j++) {
					buttons[i][j].setBackground(null);
				}
			}

			try {
				num = Integer.parseInt(textField.getText());
			} catch (NumberFormatException nfe) {
				num = 10;
				textField.setText("" + num);
			}

			grid = num;
			newGameBoard();
		}

		// SHOW MINES
		else if (source.getText().equals("Show Mines")) {
			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons.length; j++) {
					if (minePath[i][j]) {
						buttons[i][j].setBackground(Color.black);
					}
				}
			}
			DisableGrid();
		}

		// SHOW PATH
		else if (source.equals(showPath)) {
			for (int i = 0; i < safePath.size(); i++) {
				buttons[safePath.get(i).x][safePath.get(i).y]
						.setBackground(Color.BLUE);
			}
			DisableGrid();
		}

		// LOSER
		if (livesLeft == 0 ) {
			PopUpLose();
		}
		if (gameScore == 0) {
			PopUpLose();
		}

		// WINNER
		if (source.equals(winner)) {
			PopUpWin();
		}

	}
}
