package bitter;

import bitter.*;

/**
 * Provides formatting for Bitter messages.
 */
public class MessageFormatter {

    /**
     * Formats the message into string suitable for printing to user.
     * Formats in the form:
     *      @user
     *      Day, yyyy.MM.dd at hh:mm TZ
     *      Contents of the message here
     *
     * @param message the message to be formatted
     * @return the formatted message
     */
    public static String toPrintableString(Message message) {
        String ps = "@" + message.getPoster().getName() + "\n";
        ps += message.getTime().toPrintableString() + "\n";
		ps += (message.isPrivate() ? "Private" : "Public") + " message: \n";
        ps += message.getBody();

        return ps;
    }

} // End class MessageFormatter
