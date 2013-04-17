package bitter.action;

import bitter.*;
import bitter.util.*;

public class ViewMessage implements Action {

    private static final int VIEW_TYPE = 1;
    private static final int VIEW_SUBJECT = 2;
    private static final String VIEW_COM_FORMAT =
            "view [user|topic] <$user|$topic>"

    private MessageHandler mHandler;
    private MessageList mList;
    private error;

    public ViewMessage(MessageHandler mHandler, List<String> terms) {
        this.mHandler = mHandler;
        mList = null;
        error = false;
        if (terms.get(VIEW_TYPE).toLowerCase.equals("topic") {
            mList = mHandler.getMessagesByTopic(terms.get(VIEW_SUBJECT);
        } else if (terms.get(VIEW_TYPE).toLowerCase.equals("user") {
            mList = mHandler.getMessagesByUser(terms.get(VIEW_SUBJECT);
        } else {
            error = true;
        }
    }

    @Override
    public String doAction() {
        if (error) {
            return "View command format: " + VIEW_COM_FORMAT;
        } 
        String messages = "";
        for (Message message : mList) {
            messages += MessageFormatter.toPrintableString(message);
            messages += "\n\n";
        }

        return messages;
    }

} // End class ViewMessage
