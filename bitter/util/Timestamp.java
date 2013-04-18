package bitter.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * Provides an easy-to-use timestamp with a standard output format.
 */
public class Timestamp implements Comparable<Timestamp>, java.io.Serializable {

    private static final String format = "EEE, yyyy.MM.dd 'at' HH:mm z";
    private static final DateFormat formatter = new SimpleDateFormat(format);
    
    private Date time;

    /**
     * Create a new timestamp based on the current time.
     */
    public Timestamp() {
        time = new Date();
    }

    /**
     * Create a new timestamp based on the given Date.
     */
    public Timestamp(Date date) {
        time = date;
    }

    /**
     * Returns a Date object with the same time value as the Timestamp.
     * @return a Date object with the same value as the timestamp
     */
    public Date toDate() {
        return time;
    }

    /**
     * Returns a pretty string representing the time of the timestamp.
     * @return a print-worthy string representing the time of the timestamp
     */
    public String toPrintableString() {
        return formatter.format(time);
    }

    /**
     * Compares this timestamp to another.
     * @param ts the timestamp to be compared to
     * @return -1, 0, or 1 if this timestamp represents a time earlier than,
     *          equal to, or greater than the given time, respectively
     * @see java.util.Date#compareTo
     */
    @Override
    public int compareTo(Timestamp ts) {
        return this.time.compareTo(ts.time);
    }

} // End class Timestamp
