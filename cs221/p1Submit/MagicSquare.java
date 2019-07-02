import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Source Class
 * @author Nik Morales
 * 
 */
public class MagicSquare 
{
	private static final int ERROR_CODE = 0;
	private File file;
	private int gridSize;
	protected int[][] grid;
	protected int n;
	String fileName;


	/**
	 * This Method is called when the check argument is called.
	 * @param file
	 */
	public MagicSquare(File file)
	{
		this.file = file;
		ReadMatrix(file);
		CheckMatrix();
		printMatrix();
	}

	/**
	 * This Method is called when the create argument is called.
	 * @param N
	 * @param st
	 */
	public MagicSquare(int N, String st){
		gridSize = N;
		fileName = st;
		createMagicSquare();
		WriteMatrix();
	}

	/**
	 * Check Matrix runs through an array to check if it meets
	 * the requirements to be a magic square.
	 * @return 
	 * @return true or false
	 */
	public boolean CheckMatrix()
	{
		final int Math = ((gridSize*(gridSize*gridSize+1))/2);
		int sumOfRow=0, sumOfCol=0;
		int sumOfPrimaryDiagonal, sumOfSecondaryDiagonal;
		boolean[] num= new boolean[gridSize*gridSize];


		for (int row = 0; row < gridSize; row++) 
		{
			sumOfRow=0;
			sumOfCol=0;
			sumOfPrimaryDiagonal=0; 
			sumOfSecondaryDiagonal=0;
			for (int col = 0; col < gridSize; col++) 
			{
				if (col > gridSize) 
				{
					return false;
				}
				else if(num[grid[row][col]-1]==true)
				{
					return false;
				}

				num[grid[row][col]-1]=true;
				sumOfRow += grid[row][col];
				sumOfCol += grid[row][col];
				sumOfPrimaryDiagonal += grid[row][col];
				sumOfSecondaryDiagonal += grid[col][2-col];		
			}

			if(sumOfRow != Math || sumOfCol != Math )
			{
				return false;
			}

			else if(sumOfPrimaryDiagonal != Math || sumOfSecondaryDiagonal != Math)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * The method wasn't suggested in the project description
	 * however I felt it was necessary to my code, This code
	 * prints out the Matrix it was given when called.
	 */
	public void printMatrix(){

		System.out.println("The Matrix is...");

		for(int c=0; c<gridSize; c++)
		{	
			for(int e=0; e <gridSize; e++)
			{
				System.out.print(grid[c][e] + " " );

			}
			System.out.print("\n");
		}

		if (CheckMatrix()==true)
		{
			System.out.println("a magic square");	
		}
		if(CheckMatrix()==false)
		{
			System.out.println("NOT a magic square");
		}
	}

	/**
	 * CreateMagicSquare comes from the pseudo code provide, 
	 * intended to create a magic square.
	 */
	public void createMagicSquare()
	{

		n=gridSize;
		
		grid = new int[n][n];

		int row = n-1;
		int col = n/2;
		int oldRow=0;
		int oldCol=0;

		for(int i = 1; i<= n*n; i++)
		{

			grid[row][col] = i;
			oldRow = row;
			oldCol = col;
			row++;
			col++;
			if(row == n){
				row=0;
			}
			if (col == n){
				col=0;
			}
			if(grid[row][col] != 0){
				row = oldRow;
				col = oldCol;
				row--;
			}
		}

	}

	/**
	 * Write Matrix is suppose to write a matrix into a file
	 */
	public void WriteMatrix()
	{
		n=gridSize;
		try {
			
			File file = new File(fileName);
			PrintWriter outFile = new PrintWriter(new FileWriter(file));
			createMagicSquare();
			

			for(int row = 0; row<(n); row++){
				for(int col = 0; col<(n); col++){
					
					outFile.printf("%2s", grid[row][col]);
				}
				outFile.println();
			}

			outFile.close();
		}
		catch (IOException e){
			System.out.println("IO Exception ");
			System.out.println(e.getMessage());
			System.exit(ERROR_CODE);
		}
	}

	/**
	 * Read Matrix does what you might think,
	 * it reads matrices.
	 * @param file
	 */
	public void ReadMatrix(File file)
	{
		Scanner scan;
		try
		{
			scan = new Scanner(file);

			if(scan.hasNextInt()){
				gridSize = scan.nextInt();
				grid = new int[gridSize][gridSize];

			}
			else
			{
				System.out.println("Error: you did not enter an integer");
				System.exit(1);
			}
			for(int c=0; c<gridSize; c++){
				for(int e=0; e <gridSize; e++){
					if(scan.hasNextInt()){
						grid[c][e]= scan.nextInt();
					}

					else
					{
						System.out.println("Error didn't enter an integer");
						System.exit(1);
					}
				}
			}
		}

		catch(FileNotFoundException e){
			System.out.println("Error trying to open File. " + e.getMessage());
			System.exit(1);
		}
		catch(NumberFormatException e){
			System.out.println("Values do not match. ");
			System.out.println(e.getMessage());
			System.exit(ERROR_CODE);
		}
	}
}