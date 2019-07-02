import java.util.Comparator;
import java.util.Random;

import org.testng.Assert;

/**
 * Test case class for testing the methods of the SearchAndSort class,
 * using an instance of the IUSingleLinkedList class. 
 * @author CS221 
 */
public class SearchAndSort_TestCase
{
	// named elements for use in tests
	public static final Integer A = new Integer(1);
	public static final Integer B = new Integer(2);
	public static final Integer C = new Integer(3);
	public static final Integer D = new Integer(4);
	public static final Integer E = new Integer(5);
	public static final Integer F = new Integer(6);
	public static final Integer G = new Integer(7);
	public static final Integer H = new Integer(8);
	public static final Integer I = new Integer(9);
	public static final Integer J = new Integer(10);
	public static final Integer K = new Integer(11);
	public static final Integer L = new Integer(12);
	public static final Integer M = new Integer(13);
	public static final Integer N = new Integer(14);
	public static final Integer O = new Integer(15);
	public static final Integer P = new Integer(16);
	public static final Integer Q = new Integer(17);
	
	// max time for big list to pass tests
	public static final long MAX_TIME = 30000;   // in microseconds 
	// max value in random array 
	public static final int MAX_VALUE = 123; 
	
	//*************** Test Methods ***********************************
	/**
	 * Tests search method
	 * @param sortedList - implementation of IndexedUnorderedList interface with sorted elements
	 * @param target - Integer searching for 
	 * @param expectedIndex - location of target in list 
	 */
	public static void search(IndexedUnorderedList<Integer> sortedList, Integer target, int expectedIndex) 
	{
		int index = SearchAndSort.search(sortedList, target);
		Assert.assertEquals(index, expectedIndex);
	}
	
	/**
	 * Tests search method with a Comparator 
	 * @param sortedList - implementation of IndexedUnorderedList interface with sorted elements
	 * @param c - Comparator object 
	 * @param target - Integer searching for 
	 * @param expectedIndex - location of target in list 
	 */
	public static void search(IndexedUnorderedList<Integer> sortedList, Comparator<Integer> c, Integer target, int expectedIndex)
	{
		int index = SearchAndSort.search(sortedList, c, target);
		Assert.assertEquals(index, expectedIndex);
	}

	/**
	 * Tests sort method
	 * @param list - implementation of IndexedUnorderedList interface
	 * @param sortedList - implementation of IndexedUnorderedList interface
	 */
	public static void sort(IndexedUnorderedList<Integer> list, IndexedUnorderedList<Integer> sortedList) 
	{
		IndexedUnorderedList<Integer> temp = tempList(list.size());
		SearchAndSort.sort(list, temp);
		Assert.assertEquals(list, sortedList); 
	}
	
	/**
	 * Tests sort method with a Comparator 
	 * @param list - implementation of IndexedUnorderedList interface
	 * @param sortedList - implementation of IndexedUnorderedList interface
	 * @param c - Comparator object 
	 */
	public static void sort(IndexedUnorderedList<Integer> list, IndexedUnorderedList<Integer> sortedList, Comparator<Integer> c) 
	{
		IndexedUnorderedList<Integer> temp = tempList(list.size());
		SearchAndSort.sort(list, temp, c);
		Assert.assertEquals(list, sortedList); 
	}
	
	/**
	 * Tests runtime of sort method - 
	 * 		runs sort and determines whether it runs less than the max time allowed.  
	 * @param list - implementation of IndexedUnorderedList interface
	 * @param time - long value 
	 */
	public static void sort(IndexedUnorderedList<Integer> list) 
	{
		IndexedUnorderedList<Integer> temp = tempList(list.size());
		long startTime = System.currentTimeMillis(); 
		SearchAndSort.sort(list, temp);
		long stopTime = System.currentTimeMillis(); 
		long duration = stopTime - startTime;
		Assert.assertTrue(duration < MAX_TIME);
	}
	
	/**
	 * Tests runtime of sort method with a Comparator - 
	 * runs sort with Comparator and determines whether it runs less than the max time allowed. 
	 * @param list - implementation of IndexedUnorderedList interface
	 * @param c - Comparator object 
	 * @param time - long value 
	 */
	public static void sort(IndexedUnorderedList<Integer> list, Comparator<Integer> c) 
	{
		IndexedUnorderedList<Integer> temp = tempList(list.size());
		long startTime = System.currentTimeMillis(); 
		SearchAndSort.sort(list, temp, c);
		long stopTime = System.currentTimeMillis(); 
		long duration = stopTime - startTime;
		Assert.assertTrue(duration < MAX_TIME);
	}
	
	//*************** Utility Methods  ***********************************
	/**
	 * Converts array of Integers to IndexedUnorderedList list with same elements 
	 * @param elements - array of Integers 
	 * @return list - IndexedUnorderedList implementation  
	 */
	public static IndexedUnorderedList<Integer> newList(Integer[] elements) 
	{
		IndexedUnorderedList<Integer> list = new IUSingleLinkedList<Integer>();
		for (int i = 0; i < elements.length; i++) 
		{
			list.add(elements[i]);
		}
		return list;
	}
	
	/**
	 * Returns a temporary IUSingleLinkedList<Integer> filled with invalid elements
	 * @param size - int value
	 * @return IndexedUnorderedList<Integer> - returns IUSingleLinkedList<Integer> instance 
	 */
	public static IndexedUnorderedList<Integer> tempList(int size)
	{
		IndexedUnorderedList<Integer> temp = new IUSingleLinkedList<Integer>();
		for(int i = 0; i < size; i++)
		{
			temp.add(Q);
		}
		return temp;
	}
	
	/**
	 * Creates a IndexedUnorderedList list of given size with random Integers. 
	 * @param size - int value  
	 * @return list - IndexedUnorderedList implementation  
	 */
	public static IndexedUnorderedList<Integer> newList(int size) 
	{
		IndexedUnorderedList<Integer> list = new IUSingleLinkedList<Integer>();
		Random rand = new Random(MAX_VALUE);
		for (int i = 0; i < size; i++) 
		{
			list.add(new Integer(rand.nextInt()));
		}
		return list;
	}
		
	/**
	 * Returns an instance of the ReverseComparator
	 * @return - ReverseComparator object 
	 */
	public static Comparator<Integer> comparator()
	{
		return new SearchAndSort_TestCase.ReverseComparator<>(); 
	}
	
	/**
	 * Reverses the ordering defined by the class. 
	 * @param <T extends Comparable<T>> - type of objects being compared 
	 */
	private static class ReverseComparator<T extends Comparable<T>> implements Comparator<T> 
	{
		@Override
		public int compare(T o1, T o2) 
		{
			return -(o1.compareTo(o2));
		}
	}
			
}
