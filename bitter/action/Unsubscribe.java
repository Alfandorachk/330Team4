package bitter.action;

/**
 * Unsubscribe is an action intended for the Bitter system; it searches for
 * the specified user to unsubscribe from and unsubscribes them 
 * and in doAction() tells the user what happened.
 */
import bitter.*;
import bitter.net.*;
import bitter.util.*;
import java.util.*;

public class Unsubscribe implements Action {
    private static int VIEW_TYPE = 1;
    private static int SUB_SUBJECT = 2;
    private static final String VIEW_COM_FORMAT =
            "unsubscribe user <$username>";
    private boolean error;
        private String searchBy; 
        private User subscriber;
        private User unsubscribeFrom;
        private ArrayList<User> users;
        private String unsubTo;
        private boolean remove;
        private UserLookupTable look;
        
    /**
     * Searches for a user for the subscriber to unsubscribe from and
     * unsubscribes them. 
     * 
     * @param terms the list of terms the user entered as a command
     * @param subscriber the user doing the subscribing
     * @param look a table of all current users
     */
    public Unsubscribe(List<String> terms, User subscriber, UserLookupTable look){
        this.users = users;
        this.subscriber = subscriber;
        this.look = look;
        error = false;
        
        if (terms.size() < 3) {
		error = true;
	} else {
		searchBy = terms.get(VIEW_TYPE).toLowerCase();
		unsubTo = terms.get(SUB_SUBJECT);
                if (searchBy.equals("user")) {
                    unsubscribeFrom = look.lookupUser(unsubTo);
                    remove = subscriber.removeSub(unsubscribeFrom);
                } else {
                    error = true;
		}
	}   
        
    }
    
    /**
     * Tells the user if the unsubscription was successful.
     * @return a string in human-readable form that indicates what happened
     */
    @Override
    public String doAction() {
        if(remove == true){
            return "Unsubscribed to user " + unsubTo;
        }
        return "You aren't subscribed to user " + unsubTo;
    }
}
