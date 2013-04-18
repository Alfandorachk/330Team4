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
	
	private enum State { NORMAL, REGISTERING, LOGGING_IN };

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
    public BitterProtocol(MessageHandler mHandler,
			UserLookupTable lTable, UserHashMap uHash) {
        this.user = null;
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
		switch (state) {
		case REGISTERING: 
			state = State.NORMAL;
			response = (new RegisterStageTwo(terms, uHash, lTable,
						saveState)).doAction();
			saveState = "";
			return filter(response);
		case LOGGING_IN:
			state = State.NORMAL;
			response = (new LoginStageTwo(terms, uHash, saveState)).doAction();
			if (response.equals("Success")) {
				user = lTable.lookupUser(saveState);
				response += "\n";
				response += (new LoginPrint(mHandler, user)).doAction();
			}
			saveState = "";
			return filter(response);
		case NORMAL:
		default:
		}	

        switch (terms.get(0)) {
        case "post":
            response = (new PostMessage(user, mHandler, terms)).doAction();
            break;
        case "view":
			Action viewer = ViewerFactory.makeViewer(terms, mHandler, 
					lTable, user);
            response = viewer.doAction();
            break;
		case "register":
			response = (new RegisterStageOne(terms, uHash)).doAction();
			if (response.equals("Password: ")) {
				state = State.REGISTERING;
				saveState = terms.get(1);
			}
			break;
		case "login":
			response = (new Login(terms, uHash)).doAction();
			if (response.equals("Password: ")) {
				state = State.LOGGING_IN;
				saveState = terms.get(1);
			}
			break;
		case "logout":
			response = (new Logout()).doAction();
			if (response.equals("Logged out")) {
				user = null;
			}
			break;
		case "subscribe":
			response = (new Subscribe(terms, user, lTable)).doAction();
			break;
        default:
            response = "I do not understand command " + command;
            break;
        }

		// This is stupid
        return filter(response);
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

	private String filter(String str) {
        return str.replace("\n", "~~~~$$$$~~~~xxxx~~~~");
	}

} // End class BitterProtocol
