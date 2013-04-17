package bitter.util;

import bitter.*;

/**
 * Simple Data Transfer Object message node.
 */
public class MessageListNode {
    public MessageListNode prev;
    public MessageListNode next;
    public Message message;

	/**
	 * Constructor.
	 *
	 * @param message the message this contains
	 */
    public MessageListNode(Message message) {
        this.message = message;
        prev = next = null;
    }
} // End class MessageListNode
