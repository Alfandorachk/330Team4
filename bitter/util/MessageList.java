package bitter.util;

import bitter.*;
import java.util.*;

/**
 * Message List is a very specialized doubly-linked, sorted list.
 * Sorted insertion is done based on comparing the timestamp of each element.
 * MessageLists are sorted in most-recent-first order.  Since the list is kept
 * sorted and incoming messages by nature should be very near the front,
 * insertion time is kept to a minimum.
 */
public class MessageList implements Iterable<Message>  {

    private MessageListNode head;
    private MessageListNode tail;
    private int length;

    /**
     * Creates an empty MessageList.
     */
    public MessageList() {
        head = tail = null;
        length = 0;
    }

    /**
     * Adds a message to the list.  This method is synchronized, so everything
     * should be hunky-dory.
     *
     * @param message the message to be added
     */
    public synchronized void add(Message message) {
        MessageListNode toAdd = new MessageListNode(message);
        if (isEmpty()) {
            addNodeToEmptyList(toAdd);
        } else {
            addNodeToPopulatedList(toAdd);
        }
        length++;
    }

    private void addNodeToEmptyList(MessageListNode toAdd) {
        head = tail = toAdd;
    }

    private void addNodeToPopulatedList(MessageListNode toAdd) {
        MessageListNode cur; 
        Timestamp toAddTime = toAdd.message.getTime();
        for(cur = head; 
            cur.message.getTime().compareTo(toAddTime) < 0 && cur != null;
            cur = cur.next)
            ;
        if (cur == head) {
            addNodeToHead(toAdd);
        } else if (cur == null) {
            addNodeToTail(toAdd);
        } else {
            addNodeToMiddle(toAdd, cur);
        } 
    }

    private void addNodeToHead(MessageListNode toAdd) {
        toAdd.next = head;
        head.prev = toAdd;
        head = toAdd;
    }

    private void addNodeToTail(MessageListNode toAdd) {
        toAdd.prev = tail;
        tail.next = toAdd;
        tail = toAdd;
    }

    private void addNodeToMiddle(MessageListNode toAdd, MessageListNode spot) {
        toAdd.prev = spot.prev;
        toAdd.next = spot;
        spot.prev = toAdd;
        toAdd.prev.next = toAdd;
    }

    /**
     * Tells whether or not the list is empty.
     *
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list
     */
    public int length() {
        return length;
    }

    /**
     * Return an iterator over this list.
     *
     * @return an iterator over this message list
     */
    @Override
    public MessageListIterator iterator() {
        return new MessageListIterator(this);
    }

	/**
	 * Get the element at the given index.
	 * @param index the index to get the element from
	 * @return the element at the given index
	 */
	public Message get(int index) {
		if (length <= index) {
			throw new NoSuchElementException();
        }
		MessageListNode cur = head;
		for (int i = 0; i < index; i++) {
			cur = cur.next;
		}
		return cur.message;
	}
    
} // End class MessageList
