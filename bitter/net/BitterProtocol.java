package bitter.net;

import java.net.*;
import java.io.*;
import java.util.regex.*;
import java.util.*;
import bitter.*;
import bitter.action.*;
import bitter.util.*;

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
	
	private enum State { NORMAL, REGISTERING };

    // Everything between quotes and single words outside quotes.
    //private static final String PARSE_REGEX = 
    //        "((<=\"[^\"]*).*(?=[^\"]*\")|\\w+)";
	private static final String PARSE_REGEX =
			"(\"[^\"]*\"|\\w+)";
    private static final Pattern PARSE_PATTERN = Pattern.compile(PARSE_REGEX);

    private User user;
    private MessageHandler mHandler;
	private UserLookupTable lTable;
	private UserHashMap uHash;
	private State state; 
	private String saveState;

    /**
     * Constructs a new BitterProtocol instance associated with the given
     * User and MessageHandler.
     *
     * @param user the user at the other end of the connection
     * @param mHandler the server's MessageHandler
     */
    public BitterProtocol(User user, MessageHandler mHandler,
			UserLookupTable lTable, UserHashMap uHash) {
        this.user = user;
        this.mHandler = mHandler;
		this.lTable = lTable;
		this.uHash = uHash;
		state = State.NORMAL;
		saveState = "";
    }
    
    public String processCommand(String command) { 
        List<String> terms = parseCommand(command);
        String response = "";
        //for (String term: terms) System.out.println(term);  // REMOVE
		if (terms.isEmpty()) {
			return "Need a command";
		}
		if (state == State.REGISTERING) {
			state = State.NORMAL;
			response = (new RegisterStageTwo(terms, uHash, lTable,
						saveState)).doAction();
			saveState = "";
			return response;
		}
        switch (terms.get(0)) {
        case "post":
            response = (new PostMessage(user, mHandler, terms)).doAction();
            break;
        case "view":
			Action viewer = ViewerFactory.makeViewer(terms, mHandler, lTable);
            response = viewer.doAction();
            break;
		case "register":
			response = (new RegisterStageOne(terms, uHash)).doAction();
			if (response.equals("Enter Password: ")) {
				state = State.REGISTERING;
				saveState = terms.get(1);
			}
			break;
        default:
            response = "I do not understand command " + command;
            break;
        }

		// This is stupid
        return response.replace("\n", "~~~~$$$$~~~~xxxx~~~~");
    }

    private List<String> parseCommand(String command) {
        int arrayLength = 5;
        List<String> terms = new ArrayList<String>(arrayLength);
        Matcher matcher = PARSE_PATTERN.matcher(command);
        while (matcher.find()) {
            terms.add(matcher.group(1));
        }
        return terms;
    }

} // End class BitterProtocol
