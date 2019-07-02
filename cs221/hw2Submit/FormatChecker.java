import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FormatChecker {

	public static void main(String[] args) throws FileNotFoundException {
		for(int i =0; i < args.length; i++){
			checkFormat(args[i]);
		}
	}
	
	public static void checkFormat(String fileName) throws FileNotFoundException{

		//scanner to read in the file
		File temp = new File(fileName);
		Scanner scanFile = new Scanner(temp);
		
	
		
		//scanner and variables to check dimensions
		Scanner first = new Scanner(scanFile.nextLine());
		int row = first.nextInt();
		int col = first.nextInt();
		int actRow = 0;
		int actCol = 0;
		
		//checks for extra dimensions
		if( first.hasNext() ){ 
			first.close();
			throw new DimensionMismatchException();
		}
		first.close();
		
	    //checks matrix
		while (scanFile.hasNextLine()){
			actRow++;
			actCol= 0;

			String colLine = scanFile.nextLine();
			Scanner lineScan = new Scanner(colLine);
			
			while (lineScan.hasNextInt()){
				actCol++;
				lineScan.nextInt();
			}
		if (actRow != row){
			throw new DimensionMismatchException();
		}
		}
		
		//Checks is expected columns equals actual columns
		if (actCol != col){
			throw new DimensionMismatchException();
		}
		else
			throw new ValidFormatException();

	}
	
}
