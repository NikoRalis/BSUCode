import java.util.*;

/**
 * Class for searching and sorting lists that implement the IndexedUnorderedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses binary search and merge sort algorithms. 
 *
 * @author CS221
 */
public class SearchAndSort
{
	/**
	 * Uses binary search algorithm to determine index of a given element in a list 
	 * that implements the IndexedUnorderedList interface.
	 * Elements in list are sorted in ascending order.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be searched
	 * @param target
	 * 			   The object of type T searching for 
	 * @return int  
	 * 			  The index of the given element
	 * @throws 
	 * 			  NoSuchElementException - if list is empty or element can't be found
	 * @see IndexedUnorderedList
	 */
	public static <T extends Comparable<T>> int search(IndexedUnorderedList<T> list, T target) 
	{
		int index = binarySearch(list, target, 0, list.size() - 1);
		if(index == -1)
		{
			throw new NoSuchElementException("SearchAndSort - search");
		}
		return index;
	}

	/**
	 * Uses binary search algorithm with given Comparator 
	 * to determine index of a given element in a list 
	 * that implements the IndexedUnorderedList interface.
	 * Elements in list are sorted in ascending order.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be searched
	 * @param c
	 * 			   The Comparator used
	 * @param target
	 * 			   The object of type T searching for 
	 * @return int  
	 * 			  The index of the given element
	 * @throws 
	 * 			  NoSuchElementException - if list is empty or element can't be found
	 * @see IndexedUnorderedList
	 */
	public static <T> int search(IndexedUnorderedList<T> list, Comparator<T> c, T target) 
	{
		int index = binarySearch(list, c, target, 0, list.size() - 1);
		if(index == -1)
		{
			throw new NoSuchElementException("SearchAndSort - search with Comparator");
		}
		return index;
	}
	
	/**
	 * Recursive binary search algorithm to find index of a given object in a list 
	 * that implements the IndexedUnorderedList interface, 
	 * uses compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be searched
	 *  @param target
	 *  			The object of type T searching for 
	 * @param low
	 * 			   The beginning index of sub-list searching 
	 * @param high
	 * 				The ending index of sub-list searching 
	 * @return int 
	 * 				Index of element, or -1 if empty / element not found
	 */
	private static <T extends Comparable<T>> int binarySearch(IndexedUnorderedList<T> list, T target, int low, int high)
	{
		// TODO write recursive binary search method that uses compareTo() method 
		
		return -1;  // delete this line in your implementation 
	}
	
	/**
	 * Recursive binary search algorithm to find index of a given object in a list 
	 * that implements the IndexedUnorderedList interface, 
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be searched
	 * @param c
	 *            The Comparator used
	 * @param target
	 *  			The object of type T searching for 
	 * @param low
	 * 			   The beginning index of sub-list searching
	 * @param high
	 * 				The ending index of sub-list searching
	 * @return int 
	 * 				Index of element, or -1 if empty / element not found  
	 */
	private static <T> int binarySearch(IndexedUnorderedList<T> list, Comparator<T> c, T target, int low, int high)
	{
		// TODO write recursive binary search method that uses given Comparator 
		
		return -1;  // delete this line in your implementation 
	}

	/**
	 * Sorts a list that implements the IndexedUnorderedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted
	 * @see IndexedUnorderedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnorderedList<T> list, IndexedUnorderedList<T> temp) 
	{
		mergesort(list, temp, 0, list.size() - 1);
	}

	/**
	 * Sorts a list that implements the IndexedUnorderedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnorderedList 
	 */
	public static <T> void sort(IndexedUnorderedList <T> list, IndexedUnorderedList<T> temp, Comparator<T> c) 
	{
		mergesort(list, temp, c, 0, list.size() - 1);
	}
	
	/**
	 * Merge sort algorithm to sort list of objects from a class 
	 * that implements the IndexedUnorderedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted
	 * @param low
	 * 			   The beginning index of sub-list sorting 
	 * @param high
	 * 				The ending index of sub-list sorting 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnorderedList<T> list, IndexedUnorderedList<T> temp, int low, int high)
	{
		// TODO Write recursive Merge sort method that uses compareTo() method 
	}
	
	// TODO Write private merge method that works with mergeSort method that uses compareTo() method

	/**
	 * Merge sort algorithm sorts list of objects from a class 
	 * that implements the IndexedUnorderedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted
	 * @param c
	 *            The Comparator used
	 * @param low
	 * 			   The beginning index of sub-list sorting 
	 * @param high
	 * 				The ending index of sub-list sorting 
	 */
	private static <T> void mergesort(IndexedUnorderedList<T> list, IndexedUnorderedList<T> temp, Comparator<T> c, int low, int high)
	{
		// TODO Write recursive Merge sort method that uses given Comparator  
	}
	
	// TODO Write private merge method that works with mergeSort method that uses a Comparator
	  
}
