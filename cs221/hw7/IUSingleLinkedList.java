import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IUSingleLinkedList<T> implements IndexedUnorderedList<T> {


	private int count;
	SLLNode<T> first; 
	private int modCount;

	public IUSingleLinkedList(){
		count = 0;
		first = null;
		modCount = 0;

	}

	@Override
	public boolean isEmpty() {

		return (count == 0);
	}

	@Override
	public int size() {

		return count;
	}

	@Override
	public void set(int index, T element) {
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList - set");
		}

		SLLNode<T> current = first;

		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		current.setElement(element);
		modCount++;

	}

	@Override
	public boolean contains(T target) {
		SLLNode<T> current = first;

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


	@Override
	public T first() {

		if(isEmpty()){
			throw new IllegalStateException("IUSingleLinkedList - first");
		}

		SLLNode<T> current = first;
		return current.getElement();
	}

	@Override
	public T last() {

		if(isEmpty()){
			throw new IllegalStateException("IUSingleLinkedList - last");
		}

		return get(size()-1);

	}

	@Override
	public T get(int index) {
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList - get");
		}

		SLLNode<T> current = first;

		for(int i = 0; i < index; i++)
		{
			current = current.getNext();
		}
		return current.getElement();
	}

	@Override
	public int indexOf(T element) {
		boolean found = false;
		int index = 0;
		SLLNode<T> current = first;
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
	public T remove(int index) {
		SLLNode<T> current = first;
		SLLNode<T> previous = null;
		SLLNode<T> previosPrev = null;
		SLLNode<T> returnVal = null;
		boolean flag = false;

		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList - renive(index)");
		}
		if (isEmpty()){
			throw new IndexOutOfBoundsException("IUSingleLinkedList - renive(index)");
		}
		if (size() == 1){
			returnVal = first;
			first = null;
			count--;
			modCount++;
			return returnVal.getElement();

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
		if(index == 0){
			first = current;
		}
		returnVal = previous;

		if(previous.getNext()!= null && flag)
			previosPrev.setNext(previous.getNext());

		previous.setNext(null);
		previous = null;
		//	current.setNext(null);
		count --; 
		modCount++;

		return returnVal.getElement();
	}


	@Override
	public T remove(T element) {
		SLLNode<T> current = first;
		SLLNode<T> previous = null;

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
			throw new NoSuchElementException("IUSingleLinkedList - remove");
		}
		if(previous != null)
		{
			previous.setNext(current.getNext());
		}
		else
		{
			first = current.getNext();
		}
		current.setNext(null);
		count--;
		modCount++;
		return element; 
	}

	@Override
	public T removeFirst() {
		SLLNode<T> current = first;
		SLLNode<T> previous = null;
		SLLNode<T> returnVal = null;

		if(isEmpty()){
			throw new IllegalStateException("IUSingleLinkedList - removeFirst()");
		}
		if(size() == 1){
			returnVal = first;
			first = null;

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
		SLLNode<T> current = first;
		SLLNode<T> returnVal = null;
		int index = 0;

		if(isEmpty()){
			throw new IllegalStateException("IUSingleLinkedList - removeLast()");
		}
		if(size() == 1){
			returnVal = first;
			first = null;

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
	public void add(T element) {
		addToRear(element);
	}

	@Override
	public void add(int index, T element) {


		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("IUSingleLinkedList - add(index)");
		}

		if(isEmpty()){
			add(element);
		}

		else {
			SLLNode<T> current = first;
			for(int i = 1; i < index; i++)
			{
				current = current.getNext();
			}
			if(index == 0)
			{
				addToFront(element);
			}
			else
			{
				SLLNode<T> newNode = new SLLNode<T>(element);
				SLLNode<T> next = current.getNext();

				newNode.setNext(next);
				current.setNext(newNode);
				count++; 
				modCount++;
			}
		}

	}


	@Override
	public void addToFront(T element) {

		SLLNode<T> newNode = new SLLNode<T>(element);
		newNode.setNext(first);

		first = newNode;
		count ++; 
		modCount++;

	}

	@Override
	public void addToRear(T element) {
		SLLNode<T> newNode = new SLLNode<T>(element);
		int index = 0;

		if (isEmpty()){
			first = newNode;
			count++;
			modCount++;
		}
		else{
			SLLNode<T> current = first;
			while(index < (size()-1))
			{
				index++; 
				current = current.getNext();
			}

			if (index == (size()-1)){
				if (isEmpty()){
					current = newNode;
				}
				current.setNext(newNode);
				count++;
				modCount++;
			}
		}


	}

	@Override
	public void addAfter(T element, T target) {
		SLLNode<T> newNode = new SLLNode<T>(element);
		SLLNode<T> targetNode = new SLLNode<T>(target);


		boolean found = false;
		int index = 0;
		SLLNode<T> current = first;

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
			throw new NoSuchElementException("IUSingleLinkedList - addAfter(T, T)");
		}


	}


	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T>
	{
		private int itrModCount;
		private SLLNode<T> current, oneBefore, twoBefore; 
		private boolean canRemove; 

		public ListIterator()
		{
			itrModCount = modCount;
			current = first; 
			oneBefore = null;
			twoBefore = null; 
			canRemove = false; 
		}

		@Override
		public boolean hasNext()
		{
			if(itrModCount != modCount)
			{
				throw new ConcurrentModificationException("List2Iterator - hasNext"); 
			}
			
			return (current != null);
		}

		@Override
		public T next()
		{
			T temp; 
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUSingleLinkedListIterator - next");
			}
			if(!hasNext())
			{
				throw new NoSuchElementException("IUSingleLinkedListIterator - next");
			}
			temp = current.getElement();
			twoBefore = oneBefore; 
			oneBefore = current; 
			current = current.getNext(); 
			canRemove = true; 
			return temp;
		}

		public void remove()
		{
			if(modCount != itrModCount)
			{
				throw new ConcurrentModificationException("IUSingleLinkedListIterator - remove");
			}
			if(!canRemove)
			{
				throw new IllegalStateException("IUSingleLinkedListIterator - remove");
			}
			if(twoBefore != null)
			{
				twoBefore.setNext(current);
			}
			else
			{
				first = current; 
			}
			oneBefore.setNext(null);
			oneBefore = twoBefore; 
			count--;
			modCount++;
			itrModCount++;

			canRemove = false; 
		}
	}

}
