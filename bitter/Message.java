package bitter;

import bitter.util;

public class Message {

    private String body;
    private User poster;
    private Timestamp timestamp;

    /**
     * Creates a new message with the given poster and message body.
     *
     * @param poster the user who posted this message
     * @param body the contents of the message body
     */
    public Message(User poster, String body) {
        this.poster = poster;
        this.body = contents;
        timestamp = new Timestamp();
    }

    /**
     * Returns the body of the message.
     *
     * @return the body of the message.
     */
    public getBody() {
        return body;
    }

    /**
     * Returns the user who posted this message.
     *
     * @return the poster
     */
    public getPoster() {
        return poster;
    }

    /**
     * Returns the time the message was stamped with.
     * @return the message's timestamp
     */
    public Timestamp getTime() {
        return timestamp;
    }

} // End class Message
