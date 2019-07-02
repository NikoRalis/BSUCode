
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IUArrayList<T> implements IndexedUnorderedList<T>{
	
	private int size;
	private int modCount;
	private int capacity; 
	private T[] list; 
	
	public IUArrayList(){
		size = 0;
		modCount =0;
		capacity = 500;
		list = (T[]) new Object[capacity];
		
	}
	

	private void expand()
	{
		capacity = (int)(capacity * 1.5); 
		Arrays.copyOf(list, capacity); 
	}
	
	@Override
	public T first() {
		if (isEmpty() == true){
			throw new IllegalStateException("IUArrayList - first");
		}
		
		return list[0];
	}

	@Override
	public T last() {
		if (isEmpty() == true){
			throw new IllegalStateException("IUArrayList - first");
		}
		return list[size -1];
	}

	@Override
	public boolean contains(T target) {
		boolean found = false;
		int index = 0;
		while(!found && index < size)
		{
			if(list[index] == target)
			{
				found = true;
			}
			else
			{
				index++; 
			}
		}
		return found;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	@Override
	public void set(int index, T element) {
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUArrayList - set");
		}
		else
		{
		
			list[index] = element;
		}
		modCount++;
	}


	@Override
	public T get(int index) {
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUArrayList - get");
		}
		
		return list[index];
	}

	@Override
	public int indexOf(T element) {
		boolean found = false;
		int index = 0;
		while(!found && index < size)
		{
			if(list[index] == element)
			{
				found = true;
			}
			else
			{
				index++; 
			}
			
		}
		if (found != true){
			index = -1;
			

		}

		
		return index;
	}

	@Override
	public void add(int index, T element) {
		if(size == capacity)
		{
			expand(); 
		}
	
		if(index < 0 || index > size())
		{
			throw new IndexOutOfBoundsException("IUArrayList - add(index)");
		}
		
		for(int i = size; i > index; i--)
		{
			list[i] = list[i - 1];
		}
		list[index] = element; 
		size++; 
		modCount++;
		
	}

	@Override
	public void add(T element) {
		if(size == capacity)
		{
			expand(); 
		}
		list[size] = element;
		size++;
		modCount++;
		
	}

	@Override
	public void addToFront(T element) {
		int index = 0;
		if(size == capacity)
		{
			expand(); 
		}
		
		for(int i = size; i > index; i--)
		{
			list[i] = list[i - 1];
		}
		list[0] = element; 
		size++; 
		modCount++;
	}

	@Override
	public void addToRear(T element) {
		if(isEmpty()){
			list[0] = element;
			size++;
			modCount++;
		}
		else if (size()==1){
			list[1] = element;
			size++;
			modCount++;
		}
		else{
			int i = 0;
			while (list[i] != null){
				i++;
			}
			list[i]= element;
			size++;
			modCount++;
		}
		
	}

	@Override
	public void addAfter(T element, T target) {
		
		boolean found = false;
		int counter = 0;
		T curr = list[counter];
		
		while (counter <= size() && found == false){
			if (curr == target){
				add(element);
				found = true;
			}
			else{
				if(counter+1 < size()){
					curr = list[counter+1];
					counter++;
				}
				else
				{
					throw new NoSuchElementException("IUArrayList - addAfter(T element, T target)");
				}
					
			}
		}
		if (found == false){
			throw new NoSuchElementException("IUArrayList - addAfter(T element, T target)");
		}
		
		size++; 
		modCount++;
		
	}

	@Override
	public T remove(int index) {
		
		if(index < 0 || index >= size())
		{
			throw new IndexOutOfBoundsException("IUArrayList - add(index)");
		}
		
		T target = list[index];
		
		for (int i = index; i < size() - 1; i++){
			list[i] = list[i + 1];
		}
		
		list[size()-1] = null;
		size--;
		modCount++;
		return target;
	}

	@Override
	public T remove(T element) {
		boolean found = false;
		int index = 0;
		while(!found && index < size)
		{
			if(list[index] == element)
			{
				found = true;
			}
			else
			{
				index++; 
			}
		}
		
		if(!found)
		{
			throw new NoSuchElementException("IUArrayList - indexOf");
		}
		
		for(int i = index; i < size; i++)
		{
			list[i] = list[i + 1]; 
		}
		size--;
		modCount++;
		return element;
	}

	@Override
	public T removeFirst() {

		int index = 0;
		
		if (isEmpty()){
			throw new IllegalStateException("IUArrayList - removeFirst");
		}
		T target = list[index];
		
		for (int i = index; i < size() - 1; i++){
			list[i] = list[i + 1];
		}
		
		list[size()-1] = null;
		size--;
		modCount++;
		return target;
	}

	@Override
	public T removeLast() {

		if (isEmpty()){
			throw new IllegalStateException("IUArrayList - removeLast");
		}
		if(size() == 1)
		{
			T target = list[0];
			list[0] = null;
			size--;
			modCount++;
			return target;
		}
		else
		{
			T target = list[size()-1];
			list[size()-1] = null;
			size--;
			modCount++;
			return target;
		}
		
	}
	
	private class ListIterator implements Iterator<T>
	{
		private int next; 
		private int itrModCount; 
		private boolean canRemove; 
		
		public ListIterator()
		{
			next =0;
			itrModCount = modCount;
			canRemove = false; 
		}
		
		@Override
		public boolean hasNext()
		{
			if(itrModCount != modCount)
			{
				throw new ConcurrentModificationException("ListIterator - hasNext");  
			}
			
			return (next < size);
		}

		@Override
		public T next()
		{
			
			if(!hasNext())
			{
				throw new NoSuchElementException("ListIterator - next"); 
			}
			
			T element = list[next]; 
			next++; 
			canRemove = true; 
			return element;
		}
		
		@Override
		public void remove()
		{
			if(itrModCount != modCount)
			{
				throw new ConcurrentModificationException("ListIterator - hasNext");  
			}
			if(!canRemove)
			{
				throw new IllegalStateException("ListIterator - remove"); 
			}
			for(int i = next - 1; i < size - 1; i++)
			{
				list[i] = list[i + 1]; 
			}
			
			list[size() - 1] = null; 
			itrModCount++;
			modCount++;
			size--;
			next--;
			canRemove = false; 
		}
	}

}
