
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test class for SearchAndSort class.
 * @author CS221 
 */

public class SearchAndSortTest 
{		
	// arrays of reverse sorted lists
	public static final Integer[] SortedArray_1 = {SearchAndSort_TestCase.A};
	public static final Integer[] SortedArray_2 = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B};
	public static final Integer[] SortedArray_2_DupA = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.A};
	public static final Integer[] SortedArray_3 = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.C};
	public static final Integer[] SortedArray_3_DupA = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.A, SearchAndSort_TestCase.B};
	public static final Integer[] SortedArray_3_DupB = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.B};
	public static final Integer[] SortedArray_4 = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.C, SearchAndSort_TestCase.D};
	public static final Integer[] SortedArray_4_DupC = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.C, SearchAndSort_TestCase.C};
	public static final Integer[] RevSortedArray_2 = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] RevSortedArray_2_DupA = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.A};
	public static final Integer[] RevSortedArray_3 = {SearchAndSort_TestCase.C, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] RevSortedArray_3_DupA = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.A, SearchAndSort_TestCase.A};
	public static final Integer[] RevSortedArray_3_DupB = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] RevSortedArray_4 = {SearchAndSort_TestCase.D, SearchAndSort_TestCase.C, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] RevSortedArray_4_DupC = {SearchAndSort_TestCase.C, SearchAndSort_TestCase.C, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	
	// arrays of lists
	public static final Integer[] EmptyArray = {}; 
	public static final Integer[] Array_A = {SearchAndSort_TestCase.A};
	public static final Integer[] Array_AB = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B};
	public static final Integer[] Array_BA = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] Array_AA = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.A};
	public static final Integer[] Array_ACB = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.C, SearchAndSort_TestCase.B};
	public static final Integer[] Array_ABC = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.C};
	public static final Integer[] Array_BAC = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.A, SearchAndSort_TestCase.C};
	public static final Integer[] Array_BCA = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.C, SearchAndSort_TestCase.A};
	public static final Integer[] Array_CAB = {SearchAndSort_TestCase.C, SearchAndSort_TestCase.A, SearchAndSort_TestCase.B};
	public static final Integer[] Array_CBA = {SearchAndSort_TestCase.C, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] Array_AAB = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.A, SearchAndSort_TestCase.B};
	public static final Integer[] Array_ABA = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] Array_BAA = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.A, SearchAndSort_TestCase.A};
	public static final Integer[] Array_ABB = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.B};
	public static final Integer[] Array_BAB = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.A, SearchAndSort_TestCase.B};
	public static final Integer[] Array_BBA = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final 	Integer[] Array_BDAC = {SearchAndSort_TestCase.B, SearchAndSort_TestCase.D, SearchAndSort_TestCase.A, SearchAndSort_TestCase.C};
	public static final Integer[] Array_CADB = {SearchAndSort_TestCase.C, SearchAndSort_TestCase.A, SearchAndSort_TestCase.D, SearchAndSort_TestCase.B};
	public static final 	Integer[] Array_DCBA = {SearchAndSort_TestCase.D, SearchAndSort_TestCase.C, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};
	public static final Integer[] Array_ABCC = {SearchAndSort_TestCase.A, SearchAndSort_TestCase.B, SearchAndSort_TestCase.C, SearchAndSort_TestCase.C};
	public static final Integer[] Array_CACB = {SearchAndSort_TestCase.C, SearchAndSort_TestCase.A, SearchAndSort_TestCase.C, SearchAndSort_TestCase.B};
	public static final Integer[] Array_CCBA = {SearchAndSort_TestCase.C, SearchAndSort_TestCase.C, SearchAndSort_TestCase.B, SearchAndSort_TestCase.A};

	// Sorted list for searching with indexes
	private static final Object[][] INDEXED_SORTED_ELEMENTS = { 	{0, SearchAndSort_TestCase.A}, 
																											{1, SearchAndSort_TestCase.B}, 
																											{2, SearchAndSort_TestCase.C}, 
																											{3, SearchAndSort_TestCase.D}, 
																											{4, SearchAndSort_TestCase.E}, 
																											{5, SearchAndSort_TestCase.F}, 
																											{6, SearchAndSort_TestCase.G}, 
																											{7, SearchAndSort_TestCase.H}, 
																											{8, SearchAndSort_TestCase.I}, 
																											{9, SearchAndSort_TestCase.J}, 
																											{10, SearchAndSort_TestCase.K}, 
																											{11, SearchAndSort_TestCase.L}, 
																											{12, SearchAndSort_TestCase.M}, 
																											{13, SearchAndSort_TestCase.N}
																									   };
	// Reverse-sorted list for searching with indexes
	private static final Object[][] INDEXED_REVERSE_SORTED_ELEMENTS = { 		{0, SearchAndSort_TestCase.P},
																															{1, SearchAndSort_TestCase.O}, 
																															{2, SearchAndSort_TestCase.N},
																															{3, SearchAndSort_TestCase.M},
																															{4, SearchAndSort_TestCase.L},
																															{5, SearchAndSort_TestCase.K},
																															{6, SearchAndSort_TestCase.J},
																															{7, SearchAndSort_TestCase.I},
																															{8, SearchAndSort_TestCase.H}, 
																															{9, SearchAndSort_TestCase.G},
																															{10, SearchAndSort_TestCase.F},
																															{11, SearchAndSort_TestCase.E},
																															{12, SearchAndSort_TestCase.D}, 
																															{13, SearchAndSort_TestCase.C}
																													  };
	// Sorted list for searching with indexes
	private static final Integer[] SORTED_ELEMENTS = { 	SearchAndSort_TestCase.A, 
																						SearchAndSort_TestCase.B, 
																						SearchAndSort_TestCase.C, 
																						SearchAndSort_TestCase.D, 
																						SearchAndSort_TestCase.E, 
																						SearchAndSort_TestCase.F, 
																						SearchAndSort_TestCase.G, 
																						SearchAndSort_TestCase.H, 
																						SearchAndSort_TestCase.I, 
																						SearchAndSort_TestCase.J, 
																						SearchAndSort_TestCase.K, 
																						SearchAndSort_TestCase.L, 
																						SearchAndSort_TestCase.M, 
																						SearchAndSort_TestCase.N
																					 };
	// Reverse-sorted list for searching with indexes
	private static final Integer[] REVERSE_SORTED_ELEMENTS = { 	SearchAndSort_TestCase.P,
																										SearchAndSort_TestCase.O, 
																										SearchAndSort_TestCase.N,
																										SearchAndSort_TestCase.M,
																										SearchAndSort_TestCase.L,
																										SearchAndSort_TestCase.K,
																										SearchAndSort_TestCase.J,
																										SearchAndSort_TestCase.I,
																										SearchAndSort_TestCase.H, 
																										SearchAndSort_TestCase.G,
																										SearchAndSort_TestCase.F,
																										SearchAndSort_TestCase.E,
																										SearchAndSort_TestCase.D, 
																										SearchAndSort_TestCase.C
																									};

	// element not in list
	private static final Integer INVALID_ELEMENT = SearchAndSort_TestCase.Q;
	// invalid index
	private static final int INVALID_INDEX = -1; 
	// size of a not so big list 	
	private static final int BIG_SIZE = 20000;
	
	// comparator for testing, sorts elements in descending order  
	private Comparator<Integer> comparator; 
	
	/**
	 * Initialize Comparator for tests. 
	 */
	@BeforeTest
	public void init()
	{
		comparator = SearchAndSort_TestCase.comparator(); 
	}
	
	/**
	 * Test: search() - search for valid elements 
	 * @param expectedIndex - location of target in list
	 * @param target - object searching for  
	 */
	@Test(dataProvider = "indexedSortedElements")
	public void testSearch_validElement(int expectedIndex, Integer target)
	{ 
		SearchAndSort_TestCase.search(SearchAndSort_TestCase.newList(SORTED_ELEMENTS), target, expectedIndex);
	}
	
	/**
	 * Test: search() - search for valid element using Comparator
	 * @param expectedIndex - location of target in list
	 * @param target - object searching for  
	 */
	@Test(dataProvider = "indexedReverseSortedElements")
	public void testSearchComparator_reverseValidElement(int expectedIndex, Integer target)
	{
		SearchAndSort_TestCase.search(SearchAndSort_TestCase.newList(REVERSE_SORTED_ELEMENTS), comparator, target, expectedIndex);		
	}
	
	/**
	 * Test: search() - search for invalid element 
	 * @param expectedIndex - location of target in list
	 * @param target - object searching for  
	 */
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testSearch_invalidElement()
	{ 
		SearchAndSort_TestCase.search(SearchAndSort_TestCase.newList(SORTED_ELEMENTS), INVALID_ELEMENT, INVALID_INDEX);
	}
	
	/**
	 * Test: search() - search for invalid element using Comparator
	 * @param expectedIndex - location of target in list
	 * @param target - object searching for  
	 */
	@Test(expectedExceptions = NoSuchElementException.class)
	public void testSearchComparator_invalidElement()
	{
		SearchAndSort_TestCase.search(SearchAndSort_TestCase.newList(REVERSE_SORTED_ELEMENTS), comparator, INVALID_ELEMENT, INVALID_INDEX);		
	}

	/**
	 * Test: sort() - try to sort an empty list. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_newList()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(EmptyArray), SearchAndSort_TestCase.newList(EmptyArray));
	}
	
	/**
	 * Test: sort() - try to sort an empty list using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_newList()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(EmptyArray), SearchAndSort_TestCase.newList(EmptyArray), comparator);		
	}

	/**
	 * Test: sort() - sort a list with one element (A). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_A()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_A), SearchAndSort_TestCase.newList(SortedArray_1));
	}
	
	/**
	 * Test: sort() - sort a list with one element (A) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_A()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_A), SearchAndSort_TestCase.newList(SortedArray_1), comparator);		
	}
	
	/**
	 * Test: sort() - sort a list with two elements (AB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_AB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_AB), SearchAndSort_TestCase.newList(SortedArray_2));
	}

	/**
	 * Test: sort() - sort a list with two elements (AB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_AB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_AB), SearchAndSort_TestCase.newList(RevSortedArray_2), comparator);
	}
	
	/**
	 * Test: sort() - sort a list with two elements (BA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BA), SearchAndSort_TestCase.newList(SortedArray_2));
	}
	
	/**
	 * Test: sort() - sort a list with two elements (BA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BA), SearchAndSort_TestCase.newList(RevSortedArray_2), comparator);
	}

	/**
	 * Test: sort() - sort a list with two repeated elements (AA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_AA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_AA), SearchAndSort_TestCase.newList(RevSortedArray_2_DupA));
	}
	
	/**
	 * Test: sort() - sort a list with two repeated elements (AA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_AA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_AA), SearchAndSort_TestCase.newList(RevSortedArray_2_DupA), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (ABC). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_ABC()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_ABC), SearchAndSort_TestCase.newList(SortedArray_3));
	}
	
	/**
	 * Test: sort() - sort a list with three elements (ABC) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_ABC()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_ABC), SearchAndSort_TestCase.newList(RevSortedArray_3), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (ACB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_ACB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_ACB), SearchAndSort_TestCase.newList(SortedArray_3));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (ACB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_ACB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_ACB), SearchAndSort_TestCase.newList(RevSortedArray_3), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BAC). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BAC()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BAC), SearchAndSort_TestCase.newList(SortedArray_3));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (BAC) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BAC()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BAC), SearchAndSort_TestCase.newList(RevSortedArray_3), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BCA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BCA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BCA), SearchAndSort_TestCase.newList(SortedArray_3));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (BCA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BCA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BCA), SearchAndSort_TestCase.newList(RevSortedArray_3), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (CAB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CAB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CAB), SearchAndSort_TestCase.newList(SortedArray_3));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (CAB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CAB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CAB), SearchAndSort_TestCase.newList(RevSortedArray_3), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (CBA). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CBA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CBA), SearchAndSort_TestCase.newList(SortedArray_3));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (CBA) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CBA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CBA), SearchAndSort_TestCase.newList(RevSortedArray_3), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (AAB) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_AAB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_AAB), SearchAndSort_TestCase.newList(SortedArray_3_DupA));

	}

	/**
	 * Test: sort() - sort a list with three elements (AAB) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_AAB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_AAB), SearchAndSort_TestCase.newList(RevSortedArray_3_DupA), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (ABA) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_ABA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_ABA), SearchAndSort_TestCase.newList(SortedArray_3_DupA));
	}

	/**
	 * Test: sort() - sort a list with three elements (ABA) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_ABA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_ABA), SearchAndSort_TestCase.newList(RevSortedArray_3_DupA), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BAA) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BAA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BAA), SearchAndSort_TestCase.newList(SortedArray_3_DupA));

	}
	
	/**
	 * Test: sort() - sort a list with three elements (BAA) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BAA()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BAA), SearchAndSort_TestCase.newList(RevSortedArray_3_DupA), comparator);
	}

	/**
	 * Test: sort() - sort a list with three elements (BAB) and repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BAB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BAB), SearchAndSort_TestCase.newList(SortedArray_3_DupB));

	}

	/**
	 * Test: sort() - sort a list with three elements (BAB) and repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BAB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BAB), SearchAndSort_TestCase.newList(RevSortedArray_3_DupB), comparator);
	}

	/**
	 * Test: sort() - sort a list with four elements (BDAC). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_BDAC()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BDAC), SearchAndSort_TestCase.newList(SortedArray_4));

	}

	/**
	 * Test: sort() - sort a list with four elements (BDAC) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_BDAC()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_BDAC), SearchAndSort_TestCase.newList(RevSortedArray_4), comparator);
	}

	/**
	 * Test: sort() - sort a list with four elements (CADB). 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CADB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CADB), SearchAndSort_TestCase.newList(SortedArray_4));

	}
	
	/**
	 * Test: sort() - sort a list with four elements (CADB) using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CADB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CADB), SearchAndSort_TestCase.newList(RevSortedArray_4), comparator);
	}
	
	/**
	 * Test: sort() - sort a list with four elements (CACB) with repeats. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSort_CACB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CACB), SearchAndSort_TestCase.newList(SortedArray_4_DupC));

	}
	
	/**
	 * Test: sort() - sort a list with four elements (CACB) with repeats using a Comparator. 
	 * Expected Result: No exceptions 
	 */
	@Test
	public void testSortComparator_CACB()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(Array_CACB), SearchAndSort_TestCase.newList(RevSortedArray_4_DupC), comparator);
	}
	
	/**
	 * Test: sort() - sort a big list with randomly generated elements
	 * and runs in less than 15 milliseconds 
	 * Expected Result: No exceptions. 
	 */
	@Test
	public void testSort_BigList()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(BIG_SIZE));		
	}
	
	/**
	 * Test: sort() - sort a big list with randomly generated elements using a Comparator
	 * and runs in less than 15 milliseconds. 
	 * Expected Result: No exceptions.  
	 */
	@Test
	public void testSortComparator_BigList()
	{
		SearchAndSort_TestCase.sort(SearchAndSort_TestCase.newList(BIG_SIZE), comparator);	
	}
	
	
	//********** Data Providers ***************************
	/**
	 * Data: index of an element, and the Integer element at that location,
	 * sorted in ascending order.
	 * 
	 * @return 2D array of indexes, Integer elements at that index 
	 */
	   @DataProvider
	   public static Object[][] indexedSortedElements() 
	   {
	      return INDEXED_SORTED_ELEMENTS; 
	   }
	
	/**
	 * Data: index of an element, and the Integer element at that location,
	 * sorted in descending order.
	 * 
	 * @return 2D array of indexes, Integer elements at that index 
	 */
	   @DataProvider
	   public static Object[][] indexedReverseSortedElements() 
	   {
	      return INDEXED_REVERSE_SORTED_ELEMENTS; 
	   }


}
