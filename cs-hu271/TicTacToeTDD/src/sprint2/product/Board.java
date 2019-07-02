package sprint2.product;

public class Board {
	   private static final int TOTALROWS = 3;
	   private static final int TOTALCOLUMNS = 3;

	   public enum Cell {
		      EMPTY, CROSS, NOUGHT
		   }		   
	   private Cell[][] grid; 

	   private Cell currentTurn;  
	 
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
		   currentTurn = Cell.CROSS; 
	   }
	   
	   public void makeMove(int rowSelected, int colSelected){
		   if (rowSelected >= 0 && rowSelected < TOTALROWS && colSelected >= 0
				   && colSelected < TOTALCOLUMNS && grid[rowSelected][colSelected] == Cell.EMPTY) {
			   grid[rowSelected][colSelected] = currentTurn; 						   // Place token
			   currentTurn = (currentTurn == Cell.CROSS) ? Cell.NOUGHT : Cell.CROSS;   // change turn
		   }
	   }	   

	   
	   public int getTotalRows(){
		   return TOTALROWS;
	   }
	   
	   public int getTotalColumns(){
		   return TOTALCOLUMNS;
	   }

	   public Cell getTurn(){
		   return currentTurn;
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
