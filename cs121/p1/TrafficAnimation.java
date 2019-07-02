/* 
 * TrafficAnimation.java 
 * CS 121 Project 1: Traffic Animation
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Animates a car going across a sreet
 * @NikMorales
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel {
	//Note: This area is where you declare constants and variables that
	//	need to keep their values between calls	to paintComponent().
	//	Any other variables should be declared locally, in the
	//	method where they are used.

	//constant to regulate the frequency of Timer events
	// Note: 100ms is 10 frames per second - you should not need
	// a faster refresh rate than this
	private final int DELAY = 100; //milliseconds
	//anchor coordinate for drawing / animating
	private int x = 0;
	//pixels added to x each time paintComponent() is called
	private int stepSize = 10;
	
	private BufferedImage image;
	
	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics canvas) 
	{
		//clears the previous image
		//super.paintComponent(canvas);
		
		//account for changes to window size
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height
		
		//Fill the canvas with the background color
		
		canvas.setColor(getBackground());
		canvas.fillRect(0, 0, width, height);
    	
		//Calculate the new position
		x = (x + stepSize) % width;
    	
		//Draw new square
		//TODO: replace this square with your drawing
		int squareSide = height/2;
		int y = height;
		
		//road
		Color road = new Color(96, 96, 96);
		canvas.setColor(road);
		canvas.fillRect(0, height/2, width, height/2);
		
		//grass
		Color grass = new Color(23, 148, 23);
		canvas.setColor(grass);
		canvas.fillRect(0, height/4, width, height/4);
		
		//sky
		Color skyBlue = new Color(0, 196, 255);
		canvas.setColor(skyBlue);
		canvas.fillRect(0, 0, width, height/4);
		
		//cloud
		Color white = new Color(255, 255, 255);
		canvas.setColor(white);
		canvas.fillOval(width/6, y/20, width/8, y/8);
		canvas.fillOval(width/12, y/40, width/6, y/6);
		canvas.fillOval(width/22, y/20, width/8, y/8);
		
		
		//car
		canvas.setColor(Color.red);
		canvas.fillRect(x, y/2, squareSide, squareSide/2);
		canvas.fillRect(x+squareSide / 4 , y/2 + y/8 , squareSide, squareSide/4);
		canvas.setColor(Color.black);
		canvas.fillOval(x+(squareSide / 10), y/2 + y/5, squareSide/4, squareSide/4);
		canvas.fillOval(x+(squareSide/2) + width/10, y/2 + y/5, squareSide/4, squareSide/4);
		canvas.setColor(Color.blue);
		canvas.fillRect(x+(squareSide/2) + width/16, y/2 +y/55 , squareSide/4, squareSide/5);
		
		
		//words
		canvas.setFont(new Font("Serif", Font.BOLD, width/20));
		canvas.setColor(Color.blue);
		canvas.drawString("Hurry up grandma!", width/4, height/12);
		
		//avatar
		int radius = Math.min(width, height)/4;
		int midx = width/2;
		int imageX = midx - radius;
		int imageY = height/4;
		
		canvas.drawImage(image, imageX/2 + width/2, imageY/3, width/3, height/2, null);
		
		
	}

	/**
	 * Constructor for the display panel initializes
	 * necessary variables. Only called once, when the
	 * program first begins.
	 * This method also sets up a Timer that will call
	 * paint() with frequency specified by the DELAY
	 * constant.
	 */
	public TrafficAnimation() 
	{
		setBackground(Color.black);
		//Do not initialize larger than 800x600
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);
		
		
		try {
			image = ImageIO.read(new File("images/android.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Android png not found");
//			e.printStackTrace();
		}

		
		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/////////////////////////////////////////////
	// DO NOT MODIFY main() or startAnimation()
	/////////////////////////////////////////////
	
	/**
	 * Starting point for the TrafficAnimation program
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

   /**
    * Create an animation thread that runs periodically
	* DO NOT MODIFY this method!
	*/
    private void startAnimation()
    {
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                repaint();
            }
        };
        new Timer(DELAY, taskPerformer).start();
    }
}
