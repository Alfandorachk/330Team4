package list;

import java.util.Iterator;

/**
 * Iterator for List.  Does not implement remove().
 * @author Dan Easterling
 *
 * @param <E> the type of element in the list
 */
public class ListIterator<E> implements Iterator<E> {
	
	int currentIndex;
	List<E> list;
	
	/**
	 * Create a new iterator.
	 * @param list the list to iterate over
	 */
	public ListIterator(List<E> list) {
		currentIndex = 0;
		this.list = list;
	}

	/**
	 * Tells whether there are more elements in the iteration.
	 * @return true if there are more elements, false otherwise
	 */
	@Override
	public boolean hasNext() {
		return currentIndex < list.length();
	}

	/**
	 * Returns the current element and advances the iterator.
	 * @return the element at the current position
	 */
	@Override
	public E next() {
		return list.get(currentIndex++);
	}

	/**
	 * Not implemented.
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
