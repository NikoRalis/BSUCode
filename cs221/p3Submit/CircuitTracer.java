import java.awt.Point;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Search for shortest paths between start and end points on a circuit board
 * as read from an input file using either a stack or queue as the underlying
 * search state storage structure and displaying output to the console or to
 * a GUI according to options specified via command-line arguments.
 * 
 * @author mvail
 */
public class CircuitTracer {
	private CircuitBoard board;
	private Storage<TraceState> stateStore;
	private ArrayList<TraceState> bestPaths;

	/** launch the program
	 * @param args three required arguments:
	 *  first arg: -s for stack or -q for queue
	 *  second arg: -c for console output or -g for GUI output
	 *  third arg: input file name 
	 *  @throws FileNotFoundExcetion
	 */
	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 3) {
			printUsage();
			System.exit(1);
		}
		try {
			new CircuitTracer(args); //create this with args
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	} 

	/** Print instructions for running CircuitTracer from the command line. */
	private static void printUsage() {
		//TODO: print out clear usage instructions when there are problems with
		// any command line args
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------------------------------------");
		System.out.println("USAGE:");
		System.out.println();
		System.out.println("The program requires three command-line arguments: ");
		System.out.println();
		System.out.println("First argument is for selecting storage: ");
		System.out.println("	'-s' to use a stack");
		System.out.println("	'-q' to use a queue");
		System.out.println();
		System.out.println("Second argument is for seleccting the mode: ");
		System.out.println("	'-c' to run the program in console mode");
		System.out.println("	'-g' to run the program in GUI mode");
		System.out.println("	(GUI is not supported so don't do it)");
		System.out.println();
		System.out.println("Third argument is the name of the input file");
		System.out.println();
		System.out.println("Example: ");
		System.out.println(" $java CircuitTracer -s -c circuit4.dat ");
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------------------------------------");

	}

	/** 
	 * Set up the CircuitBoard and all other components based on command
	 * line arguments.
	 * 
	 * @param args command line arguments passed through from main()
	 * @throws FileNotFoundException 
	 */
	private CircuitTracer(String[] args) throws FileNotFoundException {

		if(args[0].equals("-s")){
			stateStore = new Storage<TraceState>(Storage.DataStructure.stack);
		}
		else if (args[0].equals("-q")){
			stateStore = new Storage<TraceState>(Storage.DataStructure.queue);
		}
		else{
			printUsage();
		}
		if(!args[2].isEmpty()){
			board = new CircuitBoard(args[2]);
		}
		else if(args[2].isEmpty()){
			printUsage();
		}

		bestPaths = new ArrayList<TraceState>();

		Point start = board.getStartingPoint();

		//Checks for around the starting point
		if(board.isOpen(start.x+1, start.y)){
			TraceState trace = new TraceState(board, start.x+1, start.y);
			stateStore.store(trace);
		}
		if(board.isOpen(start.x-1, start.y)){
			TraceState trace = new TraceState(board, start.x-1, start.y);
			stateStore.store(trace);
		}
		if(board.isOpen(start.x, start.y+1)){
			TraceState trace = new TraceState(board, start.x, start.y+1);
			stateStore.store(trace);
		}
		if(board.isOpen(start.x, start.y-1)){
			TraceState trace = new TraceState(board, start.x, start.y-1);
			stateStore.store(trace);
		}

		while(!stateStore.isEmpty()){

			TraceState myPath = stateStore.retreive();

			if(myPath.isComplete()){
				if(bestPaths.isEmpty()){
					bestPaths.add(myPath);
				}
				else if(myPath.pathLength() == bestPaths.get(0).pathLength()){
					bestPaths.add(myPath);
				}
				else if(myPath.pathLength()< bestPaths.get(0).pathLength()){
					bestPaths.clear();
					bestPaths.add(myPath);	
				}
			}
			else{
				//move down
				if(myPath.isOpen(myPath.getRow()+1,myPath.getCol())){
					TraceState trace = new TraceState(myPath, myPath.getRow()+1, myPath.getCol());
					stateStore.store(trace);
				}
				//move up
				if(myPath.isOpen(myPath.getRow()-1,myPath.getCol())){
					TraceState trace = new TraceState(myPath, myPath.getRow()-1, myPath.getCol());
					stateStore.store(trace);
				}
				//move right
				if(myPath.isOpen(myPath.getRow(),myPath.getCol()+1)){
					TraceState trace = new TraceState(myPath, myPath.getRow(), myPath.getCol()+1);
					stateStore.store(trace);
				}
				//move left
				if(myPath.isOpen(myPath.getRow(),myPath.getCol()-1)){
					TraceState trace = new TraceState(myPath, myPath.getRow(), myPath.getCol()-1);
					stateStore.store(trace);
				}
			}
		}
		
		if(args[1].equals("-c")){
			if(bestPaths.isEmpty()){
				System.err.println("There is not a path between the start and the end");
			}
			else{
				for(int i=0; i<bestPaths.size(); i++){
					System.out.println(bestPaths.get(i).toString());
				}
			}
		}
		else if(args[1].equals("-g")){
			System.out.println("You're not very good at following directions are you?");
			System.out.println();
			printUsage();
		}
		else{
			printUsage();
		}


	}

} // class CircuitTracer
