package bitter.util;

import bitter.*;

/**
 * Simple Data Transfer Object message node.
 */
public class MessageListNode {
    public ListNode prev;
    public ListNode next;
    public Message message;

    public MessageListNode(Message message) {
        this.message = message;
        prev = next = null;
    }
}
