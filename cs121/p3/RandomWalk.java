import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @NikMorales
 *
 */
public class RandomWalk {
	private int gridSize = 0;
	private Random rand = null;
	private boolean done = false;
	private ArrayList<Point>path = null;

	/**
	 * @param gridSize
	 */
	public RandomWalk(int gridSize)
	{
		this.gridSize = gridSize;
		rand = new Random();
		done = false;
		path = new ArrayList<Point>();
		path.add(new Point(0,0));
	}

	/**
	 * @param gridSize
	 * @param seed
	 */
	public RandomWalk(int gridSize, long seed)
	{
		this.gridSize = gridSize;
		rand = new Random(5);
		done = false;
		path = new ArrayList<Point>();
		path.add(new Point(0,0));

	}

	/**
	 * @return check
	 */
	private boolean checkSol()
	{
		int size = path.size();
		Point end = path.get(size-1);
		return (end.x==gridSize-1 && end.y==gridSize-1);
	}

	/**
	 * Creates step 
	 */
	public void step()
	{ 

		int size = path.size();	
		int choice = rand.nextInt(2); 
		Point p = path.get(size-1); 
		if (choice == 0 )
		{ 
			if(p.x == gridSize-1)
			{
				path.add(new Point (p.x,p.y+1)); 
			}
			else{
				path.add(new Point (p.x+1, p.y));
			}
		}

		else
		{ if(p.y == gridSize-1)
		{
			path.add(new Point (p.x+1,p.y)); 
		}
		else{
			path.add(new Point (p.x, p.y+1));
		}

		} 
		this.done = checkSol(); 
	}

	/**
	 * creates walk and finishes when it reaches the the bottom of the grid
	 */
	public void createWalk()
	{
		while(!done){
			step();
		}
	}

	/**
	 * @return
	 */
	public boolean isDone()
	{
		return done;
	}

	/**
	 * @return the path
	 */
	public ArrayList<Point>getPath()
	{
		return path;
	}

	public String toString()
	{
		String rval = ""; 

		for(Point p : path)
		{ 
			rval += "[" + p.x + "," + p.y + "] " ; 
		} 

		return rval; 
	}
}
