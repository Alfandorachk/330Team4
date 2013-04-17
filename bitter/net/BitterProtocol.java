package bitter.net;

import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import bitter.*;
import bitter.action.*;

/**
 * BitterProtocol is the protocol for the BitterClient/BitterServer
 * interactions.  This is a _very_ early version of the protocol,
 * implemented only to ease in testing other elements of the system.
 * <p>
 * This class is modeled on the KnockKnockProtocol class from the Oracle
 * tutorial on sockets, found at
 * http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockProtocol.java
 */
public class BitterProtocol {

    // Everything between quotes and single words outside quotes.
    private static final String PARSE_REGEX = 
            "((<=[^\"]*\").*(?=[^\"]*\")|\\w+)";
    private static final Pattern PARSE_PATTERN = Pattern.compile(PARSE_REGEX);
    private User user;
    private MessageHandler mHandler;

    public BitterProtocol(User user, MessageHandler mHandler) {
        this.user = user;
        this.mHandler = mHandler;
    }
    
    public String processCommand(String command) { 
        List<String> terms = parseCommand(command);
        switch (terms.get(0)) {
        case "post":
            return (new PostMessage(user, mHandler, terms)).doAction();
        default:
            return String.format("I do not understand command %s", command);
        }
    }

/*	
	public String processCommand(String command) {
		String output = null;
        if (command == null) 
            output = "Hello?\n";
        else
            output = new String(command);
		return output;
	}
*/
    private List<String> parseCommand(String command) {
        int arrayLength = 5;
        List<String> terms = new ArrayList<String>(arrayLength);
        Matcher matcher = PARSE_PATTERN.matcher(command);
        while (matcher.find()) {
            terms.add(matcher.group(1));
        }
        return terms;
    }
/*
    private Message bundleMessage(User user, String content,
            boolean isPrivate) {
        return new Message(user, content, isPrivate);
    }
*/        
		
} // End class BitterProtocol
