import java.io.File;

/**
 * Driver Class
 * @author Nik Morales
 * 
 */
public class MagicSquareTester {

	public static String check = "-check", create = "-create", size; 
	public static void main(String[] args) 
	{ 

		if (args[0].equals(check) && args.length == 2)
		{
			File file = new File(args[1]);
			MagicSquare f = new MagicSquare(file);
		}
		else if (args[0].equals(create) && args.length== 3){
			int N = Integer.parseInt(args[2]);
			String st = args[1];
			MagicSquare h = new MagicSquare(N, st);
		}
		else{
			System.out.println("Please enter a valid argument");
		}
	}

}
