import java.util.Iterator;
import java.util.ListIterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * @author nmorales
 *
 * @param <T>
 */
public class IUDoubleLinkedList<T> implements IUListWithListIterator<T>{
	private int count;
	DLLNode<T> first, end; 
	private int modCount;

	public IUDoubleLinkedList()
	{
		count = 0;
		modCount = 0; 
		end = null;
		first = end; 
	}




	@Override
	public void add(T element)
	{
		DLLNode<T> newNode = new DLLNode<T>(element);
		int index = 0;

		if(isEmpty()){
			first = newNode;
			end = newNode;
		}
		else {
			DLLNode<T> current = first;
			while(index < (size()-1))
			{
				index++; 
				current = current.getNext();
			}

			if (index == (size()-1)){
				current.setNext(newNode);
				current.setPrevious(null);
				newNode.setPrevious(current);
			}
		}
		count ++; 
		modCount++;
	}

	@Override
	public void add(int index, T element)
	{
		DLLNode<T> current = first;
		DLLNode<T> newNode = new DLLNode<T>(element);

		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("IUDoubleLinkedList - add(index)");
		}

		if(index == 0 || isEmpty())
		{
			add(element);
		}
		else
		{
			DLLNode<T> previous = current.getPrevious();

			for(int i = 0; i < index; i++)
			{
				previous = current;
				current = current.getNext();
			}
			newNode.setNext(current);
			newNode.setPrevious(previous);

			previous.setNext(newNode);
			count++; 
			modCount++;
		}
	}

	@Override
	public void addToFront(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);

		if(size()==0){
			first = newNode;
			end = newNode;
		}
		else {
			newNode.setNext(first);
			first = newNode;	
		}

		count ++; 
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		int index = 0;

		if (isEmpty()){
			first = newNode;
			end = newNode;
		}
		else{
			DLLNode<T> current = first;
			while(index < (size()-1))
			{
				index++; 
				current = current.getNext();
			}

			if (index == (size()-1)){
				current.setNext(newNode);
				newNode = end;

			}
		}

		count++;
		modCount++;

	}

	@Override
	public void addAfter(T element, T target) {
		DLLNode<T> newNode = new DLLNode<T>(element);
		DLLNode<T> targetNode = new DLLNode<T>(target);
		DLLNode<T> current = first;

		boolean found = false;
		int index = 0;


		while(!found && index < (size()))
		{
			if(current.getElement() == target)
			{
				found = true;
				targetNode.setNext(newNode);
				count ++;
				modCount++;
			}
			else
			{
				index++; 
				current = current.getNext();
			}
		}

		if (index == (size()) && found == false){
			throw new NoSuchElementException("IUDoubleLinkedList - addAfter(T, T)");
		}
	}

	@Override
	public T remove(T element)
	{
		DLLNode<T> current = first;
		DLLNode<T> previous, next;


		boolean found = false;
		while(!found && current != null)
		{
			if(current.getElement() == element)
			{
				found = true;
			}
			else
			{
				previous = current;
				current = current.getNext();
			}
		}
		if(!found)
		{
			throw new NoSuchElementException("IUDoubleLinkedList - remove");
		}

		if (size() == 1){
			first = null;
			end = null;
		}

		previous = current.getPrevious();
		next = current.getNext();

		current.setNext(null);
		current.setPrevious(null);


		if(previous != null)
		{
			previous.setNext(next);
		}
		else
		{
			first = next;
		}

		count--;
		modCount++; 
		return element;
	}

	@Override
	public T remove(int index) {
		DLLNode<T> current = first;
		DLLNode<T> previous = null;
		DLLNode<T> previosPrev = null;
		DLLNode<T> returnVal = null;
		boolean flag = false;

		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUDoubleLinkedList - renive(index)");
		}
		if (isEmpty()){
			throw new IndexOutOfBoundsException("IUDounleLinkedList - renive(index)");
		}
		if (size() == 1){
			returnVal = first;
			first = null;
			end = null;
		}

		for(int i = 0; i <= index; i++)
		{
			if(i != 0){
				flag = true;
				previosPrev = previous;
			}
			previous = current;
			current = current.getNext();
		}
		if(index == 0)
		{
			first = current;
		}
		returnVal = previous;

		if(previous.getNext()!= null && flag)
		{
			previosPrev.setNext(previous.getNext());
		}
		previous.setNext(null);
		previous = null;
		count --; 
		modCount++;

		return returnVal.getElement();
	}

	@Override
	public T removeFirst() {
		DLLNode<T> current = first;
		DLLNode<T> previous = null;
		DLLNode<T> returnVal = null;

		if(isEmpty()){
			throw new IllegalStateException("IUDoubleLinkedList - removeFirst()");
		}
		if(size() == 1){
			returnVal = first;
			first = null;
			end = null;
		}

		previous = current; 
		returnVal = previous;
		current = current.getNext();
		previous = null;
		first = current;
		count --; 
		modCount++;

		return returnVal.getElement();
	}

	@Override
	public T removeLast() {
		DLLNode<T> current = first;
		DLLNode<T> returnVal = null;
		int index = 0;

		if(isEmpty()){
			throw new IllegalStateException("IUDoubleLinkedList - removeLast()");
		}
		if(size() == 1){
			returnVal = first;
			first = null;
			end = null;

		}

		else{
			while(index < count)
			{
				index++; 
				if (index == count){
					returnVal = current;
				}
				current = current.getNext();
			}
			current = null;

		}
		count --; 
		modCount++;
		return returnVal.getElement();

	}

	@Override
	public int indexOf(T element)
	{
		boolean found = false;
		int index = 0;
		DLLNode<T> current = first;
		while(!found && index < size())
		{
			if(current.getElement() == element)
			{
				found = true;
			}
			else
			{
				index++; 
				current = current.getNext();
			}
		}

		if(!found)
		{
			index = -1;
		}

		return index;	
	}

	@Override
	public T get(int index)
	{
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUDoubleLinkedList - get");
		}

		DLLNode<T> current = first;

		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public void set(int index, T element)
	{
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUDoubleLinkedList - set");
		}

		DLLNode<T> current = first;

		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		current.setElement(element);
		modCount++;
	}

	@Override
	public int size()
	{
		return count;
	}

	@Override
	public boolean isEmpty()
	{
		return (count == 0);
	}

	@Override
	public T first() {
		if(isEmpty()){
			throw new IllegalStateException("IUDoubleLinkedList - first");
		}

		DLLNode<T> current = first;
		return current.getElement();
	}

	@Override
	public T last() {
		if(isEmpty()){
			throw new IllegalStateException("IUDoubleLinkedList - last");
		}

		return get(size()-1);
	}

	@Override
	public boolean contains(T target) {
		DLLNode<T> current = first;

		boolean found = false;
		while(!found && current != null)
		{
			if(current.getElement() == target)
			{
				found = true;
			}
			else
			{ 
				current = current.getNext();
			}
		}

		return found;
	}

	public ListIterator<T> listIterator()
	{
		return new IUDoubleLinkedListIterator();
	}

	@Override
	public Iterator<T> iterator() {

		return new IUDoubleLinkedListIterator();
	}

	/**
	 * @author nmorales
	 *
	 */
	private class IUDoubleLinkedListIterator implements ListIterator<T>
	{
		private int itrModCount, nextI, prevI;
		private DLLNode<T> next, prev, tail; 
		private boolean canModify, didNext, didPrev;

		public IUDoubleLinkedListIterator()
		{
			itrModCount = modCount; 
			nextI = 0;
			prevI = -1;
			next = first;
			tail = end;
			prev = null;
			canModify = false;
			didNext = false;
			didPrev = false;
		}

		@Override
		public boolean hasNext()
		{
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - hasNext");
			}

			return (next != null);
		}

		@Override
		public T next()
		{
			if(!hasNext()){
				throw new NoSuchElementException("IUDoubleLinkedListIterator - next");
			}
			T retVal = next.getElement();

			prev = next;
			next = next.getNext();
			canModify = true;
			didNext = true;
			nextI++;
			prevI++;

			return retVal;
		}

		@Override
		public boolean hasPrevious() {
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - hasPrevious");
			}
			return next == first;
		}

		@Override
		public T previous() {
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - previous");
			}

			if(!hasPrevious()){
				throw new NoSuchElementException("IUDoubleLinkedListIterator - next");
			}
			if (next == tail){

			}
			T retVal = prev.getElement();
			canModify = true;
			didPrev = true;
			nextI++;
			prevI++;

			return retVal;
		}

		@Override
		public int nextIndex() {
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - nextIndex");
			}
			return nextI;
		}

		@Override
		public int previousIndex() {
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - prevIndex");
			}

			return prevI;
		}

		@Override
		public void set(T e) {
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - set");
			}
			if (!canModify){
				throw new IllegalStateException("IUDoubleLinkedListIterator - set");	
			}
			if (didNext){
				prev.setElement(e);
			}
			if (didPrev){
				next.setElement(e);
			}
		}

		@Override
		public void add(T e) {
			if(!canModify)
			{
				throw new IllegalStateException("IUDoubleLinkedListIterator - add"); 
			}
			DLLNode<T> newNode = new DLLNode<T>(e);
			
			newNode.setNext(next);
			newNode.setPrevious(prev);
			 if (prev != null){
				 prev.setNext(newNode);
			 }
			 if (next != null){
				 next.setPrevious(newNode);
			 }
			if (!hasNext()){
				end = newNode;
			}
			if (!hasPrevious()){
				first = newNode;
			}
			count++; 
			canModify = false; 
			nextI++;
			prevI++;

		}

		@Override
		public void remove() {

			if(itrModCount != modCount)
			{
				throw new ConcurrentModificationException("IUDoubleLinkedListIterator - remove"); 
			}
			if(!canModify)
			{
				throw new IllegalStateException("IUDoubleLinkedListIterator - remove"); 
			}
			/*DLLNode<T> bitch;
			if (didNext){
				bitch = prev;

				prev.getPrevious().setNext(next);
				next.setPrevious(prev.getPrevious());
				bitch = null;

			}
			if (didPrev){
				bitch = next;

				prev.setNext(next.getNext());
				next.getNext().setPrevious(prev);
				bitch = null;


			}*/

			if(prev.getPrevious() == null)
			{
				first = next; 
			}
			else
			{
				prev.getPrevious().setNext(next);
			}

			prev.setNext(null);
			prev = prev.getPrevious();
			
			if(next == null)
			{
				tail = prev; 
			}
			if (first == end){
				first = end = null;
			}
			count--; 
			canModify = false; 
			nextI--;
			prevI--;

		}

	}


}