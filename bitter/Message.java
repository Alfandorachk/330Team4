package bitter;

import bitter.util.*;

public class Message {

    private String body;
    private User poster;
    private Timestamp timestamp;
    private boolean isPrivate;

    /**
     * Creates a new message with the given poster and message body.
     *
     * @param poster the user who posted this message
     * @param body the contents of the message body
     */
    public Message(User poster, String body, boolean isPrivate) {
        this.poster = poster;
        this.body = body;
        this.isPrivate = isPrivate;
        timestamp = new Timestamp();
    }

    /**
     * Returns the body of the message.
     *
     * @return the body of the message.
     */
    public String getBody() {
        return body;
    }

    /**
     * Returns the user who posted this message.
     *
     * @return the poster
     */
    public User getPoster() {
        return poster;
    }

    /**
     * Returns whether or not this message is private.
     *
     * @return true if this message is private, false otherwise
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Returns the time the message was stamped with.
     * @return the message's timestamp
     */
    public Timestamp getTime() {
        return timestamp;
    }

} // End class Message
