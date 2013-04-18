package bitter.action;

import bitter.*;
import bitter.util.*;
import java.util.*;

public class ViewMessage implements Action {

    private static final int VIEW_TYPE = 1;
    private static final int VIEW_SUBJECT = 2;
    private static final String VIEW_COM_FORMAT =
            "view [user|topic] <$user|$topic>";

    private MessageHandler mHandler;
    private MessageList mList;
    private boolean error;
	private String searchBy;
	private String searchTerm;

    public ViewMessage(MessageHandler mHandler, List<String> terms) {
        this.mHandler = mHandler;
        mList = null;
        error = false;
		if (terms.size() < 3) {
			error = true;
		} else { 
			searchBy = terms.get(VIEW_TYPE).toLowerCase();
			searchTerm = terms.get(VIEW_SUBJECT);
			if (searchBy.equals("topic")) {
				mList = mHandler.getMessagesByTopic(searchTerm);
			} else if (searchBy.equals("user")) {
				mList = mHandler.getMessagesByUser(terms.get(VIEW_SUBJECT));
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

} // End class ViewMessage
