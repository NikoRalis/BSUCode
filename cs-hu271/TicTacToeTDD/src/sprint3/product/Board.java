package sprint3.product;

public class Board {
	   private static final int TOTALROWS = 3;
	   private static final int TOTALCOLUMNS = 3;

	   public enum Cell {
		      EMPTY, CROSS, NOUGHT
		   }		   
	   private Cell[][] grid; 
	   private Cell currentTurn;  

	   public enum GameState {
	      PLAYING, DRAW, CROSS_WON, NOUGHT_WON
	   }
	   private GameState currentGameState; 
	 
	   public Board(){
		   grid = new Cell[TOTALROWS][TOTALCOLUMNS]; 
		   initBoard(); 
	   }

	   public void initBoard() {
		   for (int row = 0; row < TOTALROWS; ++row) {
			   for (int col = 0; col < TOTALCOLUMNS; ++col) {
				   grid[row][col] = Cell.EMPTY; 
			   }
		   }
		   currentGameState = GameState.PLAYING; 
		   currentTurn = Cell.CROSS;       // cross plays first
	   }


	   public void makeMove(int rowSelected, int colSelected){
		   if (rowSelected >= 0 && rowSelected < TOTALROWS && colSelected >= 0
				   && colSelected < TOTALCOLUMNS && grid[rowSelected][colSelected] == Cell.EMPTY) {
			   grid[rowSelected][colSelected] = currentTurn; 						   // Place token
			   updateGameState(currentTurn, rowSelected, colSelected); 				   // Update state
			   currentTurn = (currentTurn == Cell.CROSS) ? Cell.NOUGHT : Cell.CROSS;   // change turn
		   }
	   }	   

	   private void updateGameState(Cell turn, int rowSelected, int colSelected) {
		   if (hasWon(turn, rowSelected, colSelected)) {  // check for win
			   currentGameState = (turn == Cell.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
		   } else if (isDraw()) { 
			   currentGameState = GameState.DRAW;
		   }
		   // Otherwise, no change to current state (still GameState.PLAYING).
	   }

	   /** draw when no more empty cell */
	   public boolean isDraw() {
		   for (int row = 0; row < TOTALROWS; ++row) {
			   for (int col = 0; col < TOTALCOLUMNS; ++col) {
				   if (grid[row][col] == Cell.EMPTY) {
					   return false; // an empty cell found, not draw
				   }
			   }
		   }
		   return true; 
	   }

	   public boolean hasWon(Cell turn, int rowSelected, int colSelected) {
		   return (grid[rowSelected][0] == turn  // 3-in-the-row
				   && grid[rowSelected][1] == turn
				   && grid[rowSelected][2] == turn
				   || grid[0][colSelected] == turn      // 3-in-the-column
				   && grid[1][colSelected] == turn
				   && grid[2][colSelected] == turn
				   || rowSelected == colSelected            // 3-in-the-diagonal
				   && grid[0][0] == turn
				   && grid[1][1] == turn
				   && grid[2][2] == turn
				   || rowSelected + colSelected == 2  // 3-in-the-opposite-diagonal
				   && grid[0][2] == turn
				   && grid[1][1] == turn
				   && grid[2][0] == turn);
	   }

	   public GameState getGameState(){
		   return currentGameState;
	   }

	   public Cell getTurn(){
		   return currentTurn;
	   }
	   
	   public int getTotalRows(){
		   return TOTALROWS;
	   }
	   
	   public int getTotalColumns(){
		   return TOTALCOLUMNS;
	   }

	   public Cell getCell(int rowSelected, int colSelected){
		   if (rowSelected >= 0 && rowSelected < TOTALROWS && colSelected >= 0
				   && colSelected < TOTALCOLUMNS) {
			   return grid[rowSelected][colSelected];
		   } else {
			   return null;
		   }
	   }
}
