package bitter.action;

/**
 * SubscribeUser is an action intended for the Bitter system;
 */
import bitter.*;
import bitter.net.*;
import bitter.util.*;
import java.util.*;

public class Subscribe implements Action {
    private static int VIEW_TYPE = 1;
    private static int VIEW_SUBJECT = 2;
    private static final String VIEW_COM_FORMAT =
            "subscribe user <$username>";
    private boolean error;
        private String searchBy;
	private String searchTerm;
        
        
    private User subscriber;
    private User subscribeTo;
    public Subscribe(User subscriber, List<String> terms){
        this.subscriber = subscriber;
        this.subscribeTo = subscribeTo;
        error = false;
        if (terms.size() < 3) {
		error = true;
	} else { 
		searchBy = terms.get(VIEW_TYPE).toLowerCase();
		searchTerm = terms.get(VIEW_SUBJECT);
		if (searchBy.equals("user")) {
                    //wait on Dan. Find the user.
                } else {
			error = true;
		}
	}
    
        
        
    }
    
    @Override
    public String doAction() {
        if (error) {
            return "View command format: " + VIEW_COM_FORMAT;
        } 
        String messages = "";
		if (mList == null) {
			return String.format("No results for %s %s", searchBy, searchTerm);

		}
        for (Message message : mList) {
            messages += MessageFormatter.toPrintableString(message);
            messages += "\n";
        }

        return messages;
    }
    
}
