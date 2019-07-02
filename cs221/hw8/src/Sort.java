import java.util.Comparator;
import java.util.ListIterator;

/**
 * Class for sorting lists that implement the IUListWithListIterator interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Quicksort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IUListWithListIterator interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IUListWithListIterator interface
	 */
	private static <T> IUListWithListIterator<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}

	/**
	 * Sorts a list that implements the IUListWithListIterator interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator interface 
	 * @see IUListWithListIterator 
	 */
	public static <T extends Comparable<T>> void sort(IUListWithListIterator<T> list) 
	{
		quicksort(list);
	}

	/**
	 * Sorts a list that implements the IUListWithListIterator interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator interface 
	 * @param c
	 *            The Comparator used
	 * @see IUListWithListIterator 
	 */
	public static <T> void sort(IUListWithListIterator <T> list, Comparator<T> c) 
	{
		quicksort(list, c);
	}

	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IUListWithListIterator interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator interface 
	 */
	private static <T extends Comparable<T>> void quicksort(IUListWithListIterator<T> list)
	{
		// TODO: Implement recursive quicksort algorithm 
		if(list.size()< 2){
			return;
		}

		

		IUListWithListIterator<T> low = newList();
		IUListWithListIterator<T> high = newList();

		T p = list.last();
		list.remove(p);

		while(!list.isEmpty()){
			if(list.first().compareTo(p) < 0){
				low.add(list.removeFirst());
			}
			else if(list.first().compareTo(p) > 0){
				high.add(list.removeFirst());
			}
			else{
				low.addToFront(list.removeFirst());
			}
		}

		high.addToFront(p);

		quicksort(low);
		quicksort(high);
		
		
		while(low.size() != 0){
			list.add(low.removeFirst());
		}
		while(high.size()!= 0){
			list.add(high.removeFirst());
		}
		
		
	}

	/**
	 * Quicksort algorithm to sort objects in a list 
	 * that implements the IUListWithListIterator interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IUListWithListIterator interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void quicksort(IUListWithListIterator<T> list, Comparator<T> c)
	{

		// TODO: Implement recursive quicksort algorithm 
		if(list.size()< 2){
			return;
		}

		

		IUListWithListIterator<T> low = newList();
		IUListWithListIterator<T> high = newList();

		T p = list.last();
		list.remove(p);

		while(!list.isEmpty()){
			if(c.compare(list.first(), p)<0){
				low.add(list.removeFirst());
			}
			else if(c.compare(list.first(), p)>0){
				high.add(list.removeFirst());
			}
			else{
				low.addToFront(list.removeFirst());
			}
		}

		high.addToFront(p);

		quicksort(low, c);
		quicksort(high, c);
		
		try{
		while(low.size() != 0){
			list.add(low.removeFirst());
		}
		while(high.size()!= 0){
			list.add(high.removeFirst());
		}
		}
		catch(IllegalStateException e){
			System.err.println("");
		}
		
		
	

	}

}
