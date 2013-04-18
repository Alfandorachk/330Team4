package bitter.action;

/**
 * SubscribeUser is an action intended for the Bitter system; it searches for
 * the specified user to subscribe to and in doAction() subscribes to that user.
 */
import bitter.*;
import bitter.net.*;
import bitter.util.*;
import java.util.*;

public class Subscribe implements Action {
    private static int VIEW_TYPE = 1;
    private static int SUB_SUBJECT = 2;
    private static final String VIEW_COM_FORMAT =
            "subscribe user <$username>";
    private boolean error;
        private String searchBy; 
        private User subscriber;
        private User subscribeTo;
        private UserLookupTable users;
        private String subTo;
        
    /**
     * Searches for a user for the subscriber to subscribe to.
     * 
     * @param terms the list of terms the user entered as a command
     * @param subscriber the user doing the subscribing
     * @param users a table of all current users
     */
    public Subscribe(List<String> terms, User subscriber, UserLookupTable users){
        this.users = users;
        this.subscriber = subscriber;
        error = false;
        
        if (terms.size() < 3) {
		error = true;
	} else {
		searchBy = terms.get(VIEW_TYPE).toLowerCase();
		subTo = terms.get(SUB_SUBJECT);
                if (searchBy.equals("user")) {
                    subscribeTo = users.lookupUser(subTo); 
                } else {
                    error = true;
		}
	}   
        
    }
    
    /**
     * Adds the specified user to a list of users the subscriber is
     * subscribed to.
     * @return a string in human-readable form that indicates what happened
     */
    @Override
    public String doAction() {
        if (error) {
            return "View command format: " + VIEW_COM_FORMAT;
        } 
        
	if(subscribeTo == null){
		return String.format("No results for %s", searchBy);
        }
    
        return "Subscribed to user " + subTo;
    }
    
}
