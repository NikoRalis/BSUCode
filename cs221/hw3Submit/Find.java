/**
 * Returns index of a value in an int[] or -1 if it isn't found.
 * @author mvail
 */
public class Find {

	/**
	 * Return index where value is found in array or -1 if not found.
	 * @param array ints where value may be found
	 * @param value int that may be in array
	 * @return index where value is found or -1 if not found
	 */
	
	private static long count = 0;
	
	public static int find(int[] array, int value) {
		
		count+=2; //init and check array length
		for (int i = 0; i < array.length; i++) {
			count+=2;
			
			if (array[i] == value) {
				return i;
			}
			count++;
		}
		return -1;
	}
	
	protected static long getStatment(){
		return count;
	}
	protected static void resetStatements(){
		count = 0;
	}
}
