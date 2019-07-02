import java.util.Scanner;

/**
 * @NikMorales
 */
public class RandomWalkTest
{
	private static int gridSize = 0;
	private static long seed = 0;

	/**
	 * Gets input
	 */
	private static void getInput() 
	{

		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter grid size: ");
		gridSize = scan.nextInt();

		System.out.println("Please enter a seed: ");
		seed = scan.nextLong();


		while(gridSize <= 0)
		{
			System.out.println("Error: Grid size must be positive!");
			System.out.println("Please enter grid size: ");
			gridSize = scan.nextInt();

		}

		while(seed < 0)
		{
			System.out.println("Error: seed must be >= 0!");
			System.out.println("Please enter a seed: ");
			seed = scan.nextLong();

		}
	scan.close();
	}


	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// call getInput to process user input
		getInput();

		// create RandomWalk object using the appropriate constructor
		RandomWalk walker;
		if (seed > 0) 
		{
			walker = new RandomWalk(gridSize, seed);
		} else 
		{
			walker = new RandomWalk(gridSize);
		}

		// create the random walk and then print it
		walker.createWalk();
		System.out.println(walker.toString());

	}
}
