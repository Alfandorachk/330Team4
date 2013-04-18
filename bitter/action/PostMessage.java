package bitter.action;

import bitter.*;
import bitter.net.*;
import bitter.util.*;
import java.util.*;

/**
 * PostMessage is an action intended for the Bitter system.  It bundles a
 * message and, when doAction() is called, it adds it to the MessageHandler.
 * If all goes well, it returns a message informing the user of when it was
 * posted.
 */
public class PostMessage implements Action {

    private static int TYPE_TERM = 1;
    private static int CONTENT_TERM = 2;

    private Message message;
    private MessageHandler mHandler;
	private String error;

    /**
     * Bundles a message to be sent when doAction() is called.
     *
     * @param user the user who wishes to post the message
     * @param mHandler the MessageHandler that handles messages
     * @param commandTerms the list of terms the user entered as a command
     */
    public PostMessage(User user, MessageHandler mHandler,
            List<String> commandTerms) {
		error = "";
        this.mHandler = mHandler;
		if (user == null) {
			error = "Need to be logged in.";
		} else {
			if (commandTerms.get(TYPE_TERM).toLowerCase().equals("private")) {
				message = Message.bundlePrivateMessage(
						user, commandTerms.get(CONTENT_TERM));
			} else {
				message = Message.bundlePublicMessage(
						user, commandTerms.get(CONTENT_TERM));
			}
        }
    }

    /**
     * Adds the message to the MessageHandler.
     *
     * @return a string in human-readable form that indicates when the
     *         message was posted
     */
    @Override
    public String doAction() {
		if (!error.equals("")) {
			return error;
		}
        mHandler.addMessage(message);
        return String.format("Message posted on %s",
                message.getTime().toPrintableString()); 
    }

} // End class PostMessage
