package bitter;


public class Message {

    private String body;
    private User poster;
    private Date timestamp;

    /**
     * Creates a new message with the given poster and message body.
     *
     * @param poster the user who posted this message
     * @param body the contents of the message body
     */
    public Message(User poster, String body) {
        this.poster = poster;
        this.body = contents;
        timestamp = new Date();
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

} // End class Message
