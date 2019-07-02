import javax.swing.JFrame;

/**
 * GUI contains a grid of buttons.
 * 
 * @author Nik
 */
public class MineWalker {

	/**
	 * Create the JFrame 
	 * @param args not used
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Mine Walker");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null); //centers frame on screen


		MineWalkerPanel mainPanel = new MineWalkerPanel();

		f.getContentPane().add(mainPanel);
		f.pack();

		f.setVisible(true);
	}
}
