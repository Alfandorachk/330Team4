package list;

import java.util.NoSuchElementException;

/**
 * An ordered list of elements.
 * @author Dan Easterling
 *
 * @param <E> the type of element stored by this list
 */
public class List<E> implements Iterable<E> {

	private ListNode<E> head;
	private ListNode<E> tail;
	private int length;
	
	/**
	 * Creates an empty list.
	 */
	public List() {
		head = tail = null;
		length = 0;
	}
	
	/**
	 * Creates a list with the same elements and ordering as the passed-in array.
	 * @param array the array to convert to a list.
	 */
	public List(E[] array) {
		head = tail = null;
		length = 0;
		for (int i = 0; i < array.length; i++) {
			add(array[i]);
		}
	}
	
	/**
	 * Creates a list with the same elements and ordering as the passed-in list.
	 * @param list the list to copy
	 */
	public List(List<? extends E> list) {
		this();
		extend(list);
	}
	
	/**
	 * Add an element to the list.  Adds at the tail position.
	 * @param element
	 */
	public void add(E element) {
		add(element, length);
	}
	
	/**
	 * Adds an element at the given index.  Shifts the node at the given index and
	 * all indices after right one. 
	 * @param element the element to insert
	 * @param index the index at which to add the element
	 */
	public void add(E element, int index) {
		if (index > length)
			throw new IndexOutOfBoundsException();
		ListNode<E> toAdd = new ListNode<>(element);
		if (isEmpty())
			addToEmptyList(toAdd);
		else
			addToPopulatedList(toAdd, index);
		length++;
	}
	
	/**
	 * Tells whether or not the list is empty.
	 * @return true if the length == 0, false otherwise
	 */
	public boolean isEmpty() {
		return length == 0;
	}
	
	private void addToEmptyList(ListNode<E> toAdd) {
		head = tail = toAdd;
	}
	
	private void addToPopulatedList(ListNode<E> toAdd, int index) {
		if (index == 0)
			addToHead(toAdd);
		else if (index == length)
			addToTail(toAdd);
		else
			addToMiddle(toAdd, index);
	}
	
	private void addToHead(ListNode<E> toAdd) {
		toAdd.next = head;
		head.prev = toAdd;
		head = toAdd;
	}
	
	private void addToTail(ListNode<E> toAdd) {
		toAdd.prev = tail;
		tail.next = toAdd;
		tail = toAdd;
	}
	
	private void addToMiddle(ListNode<E> toAdd, int index) {
		ListNode<E> position = nodeAtIndex(index);
		toAdd.next = position;
		toAdd.prev = position.prev;
		position.prev.next = toAdd;
		position.prev = toAdd;
	}
	
	/**
	 * Extends the end of the list to include the elements in the introduced list.
	 * @param toAdds the list to append to the current list.
	 */
	public void extend(List<? extends E> toAdds) {
		for(E element : toAdds) {
			add(element);
		}
	}
	
	/**
	 * Removes the node at the given index.  Shifts all elements after to the
	 * left.
	 * @param index the index of the node to remove
	 * @return the node at the given index
	 */
	public E remove(int index) {
		ListNode<E> toRemove = nodeAtIndex(index);
		removeNode(toRemove);
		return toRemove.data;
	}
	
	private ListNode<E> nodeAtIndex(int index) {
		if (index >= length || index < 0)
			throw new IndexOutOfBoundsException();
		ListNode<E> node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
		}
		return node;
	}
	
	/**
	 * Removes the first instance of the given element from the list.
	 * @param element the element to remove
	 * @return true if the element was found and removed, false otherwise
	 */
	public boolean remove(E element) {
		for (ListNode<E> cur = head; cur != null; cur = cur.next) {
			if (cur.data.equals(element)) {
				removeNode(cur);
				return true;
			}
		}
		return false;
	}
	
	private void removeNode(ListNode<E> node) {
		if (length == 1)
			clear();
		else if (node == head)
			removeHeadNode();
		else if (node == tail)
			removeTailNode();
		else
			removeMiddleNode(node);
		length--;
	}
	
	private void removeHeadNode() {
		head = head.next;
		head.prev = null;
	}
	
	private void removeTailNode() {
		tail = tail.prev;
		tail.next = null;
	}
	
	private void removeMiddleNode(ListNode<E> node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	
	/**
	 * Empties the list.
	 */
	public void clear() {
		head = tail = null;
	}
	
	/**
	 * Returns the length of the list.
	 * @return the length of the list
	 */
	public int length() {
		return length;
	}
	
	/**
	 * Tells whether or not the list contains the given element.
	 * @param element the element to determine in-list existishness of
	 * @return true if the list contains the element, false otherwise
	 */
	public boolean contains(E element) {
		return indexOf(element) > -1;
	}
	
	/**
	 * Gets the index of the element in the list.
	 * @param element the element to find the index of
	 * @return the index of the element, or -1 if the list does not contain
	 * 			this element
	 */
	public int indexOf(E element) {
		int index = 0;
		for (ListNode<E> cur = head; cur != null; cur = cur.next) {
			if (cur.data.equals(element)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Get the element at the given index.
	 * @param index the index to get the element from
	 * @return the element at the given index
	 */
	public E get(int index) {
		if (length == 0)
			throw new NoSuchElementException();
		ListNode<E> cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.data;
	}
	
	/**
	 * Returns an iterator over the list.
	 * @return an iterator over the list
	 */
	public ListIterator<E> iterator() {
		return new ListIterator<E>(this);
	}
	
	/**
	 * Returns an Object array of the elements in the list, in order.
	 * @return an Object array of the elements in the list
	 */
	public Object[] toArray() {
		Object[] array = new Object[length];
		ListNode<E> cur = head;
		for (int i = 0; i < length; i++, cur=cur.next) {
			array[i] = cur.data;
		}
		return array;
	}
	
	/**
	 * Returns a shallow copy of the list with the same elements in the same order.
	 * @return a copy of the list with the same elements in the same order.
	 */
	public List<E> copy() {
		List<E> copy = new List<>(this);
		return copy;
	}
}