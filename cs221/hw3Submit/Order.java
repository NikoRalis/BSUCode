/**
 * Takes an array and reorders it so elements are in ascending order.
 * @author mvail
 */
public class Order {

	/**
	 * Take an int[] and reorganize it so they are in ascending order.
	 * @param array ints that need to be ordered 
	 */
	
	private static long counter = 0;
	
	public static void order(int[] array) {
		counter+=2;
		
        for (int next = 1; next < array.length; next++) {
            int val = array[next];
            int index = next;
            counter+=5;
            
            while (index > 0 && val < array[index - 1]) {
                array[index] = array[index - 1];
                index--;
                counter+=2;
            }
            array[index] = val;
            counter++;
        }
	}
	static long getStatment(){
		return counter;
	}
}
