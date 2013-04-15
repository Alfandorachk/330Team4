package bitter.util;

import bitter.*;
import java.util.Iterator;

/**
 * Iterator for MessageList.  Does not implement remove().
 * @author Dan Easterling
 */
public class MessageListIterator implements Iterator<Message> {
	
	int currentIndex;
	MessageList list;
	
	/**
	 * Create a new iterator.
	 * @param list the list to iterate over
	 */
	public MessageListIterator(MessageList list) {
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
	public Message next() {
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
