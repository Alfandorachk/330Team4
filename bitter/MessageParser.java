package bitter;

import java.util.regex.*;
import java.util.*;

/**
 * MessageParser parses Bitter-style messages to extract various elements.  All
 * members are static; there is no need to instantiate MessageParser.
 */
public class MessageParser {

    private static final String USER_REGEX = "@(\\w+)";
    private static final String TOPIC_REGEX = "#(\\w+)";
    private static final Pattern TOPIC_PATTERN = Pattern.compile(TOPIC_REGEX);
    private static final Pattern USER_PATTERN = Pattern.compile(USER_REGEX);

    /**
     * Extract the names of the users that this message is a reply to.
     * This extracts all words that immediately follow an '@'.
     *
     * @param message the message to be parsed
     * @return a list of the users responded to
     */
    public static LinkedList<String> extractAddressees(String message) {
       LinkedList<String> addressees = new LinkedList<String>();
       Matcher matcher = USER_PATTERN.matcher(message);

       while (matcher.find()) {
           addressees.add(matcher.group(1)); 
       }

       return addressees;
    }

    /**
     * Extract the topics of the message.  This extracts all words that
     * immediately follow a '#'.
     *
     * @param message the message to be parsed
     * @return a list of the topics of the message
     */
    public static LinkedList<String> extractTopics(String message) {
       LinkedList<String> topics = new LinkedList<String>();
       Matcher matcher = TOPIC_PATTERN.matcher(message);

       while (matcher.find()) {
           topics.add(matcher.group(1)); 
       }

       return topics;
    }

} // End class MessageParser
