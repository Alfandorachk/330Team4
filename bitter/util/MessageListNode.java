package bitter.util;

import bitter.*;

/**
 * Simple Data Transfer Object message node.
 */
public class MessageListNode {
    public MessageListNode prev;
    public MessageListNode next;
    public Message message;

    public MessageListNode(Message message) {
        this.message = message;
        prev = next = null;
    }
}
