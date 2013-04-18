package bitter;

import bitter.util.*;
import java.util.*;

public class MessageHandler {
    
    public static final String TOPIC_PREFIX = "#";
    public static final String USER_PREFIX = "@";

    private MessageListHash messageHash;

    public MessageHandler() {
        messageHash = new MessageListHash();
    }

    public MessageList getMessagesByTopic(String topic) {
        String t = TOPIC_PREFIX + topic;
        return messageHash.getMessageList(t);
    }

    public MessageList getMessagesByUser(String user) {
        String u = USER_PREFIX + user;
        return messageHash.getMessageList(u);
    }

    public void addMessage(Message message) {
        String contents = message.getBody();
        LinkedList<String> keys;
	    //keys = MessageParser.extractAddressees(contents);
       // for (String key : keys) {
        //    messageHash.putMessage(USER_PREFIX + key, message);
       // }
        keys = MessageParser.extractTopics(contents);
        for (String key : keys) {
            messageHash.putMessage(TOPIC_PREFIX + key, message);
        }
		messageHash.putMessage(USER_PREFIX + message.getPoster().getName(), 
				message);
    }

} // End class MessageHandler
