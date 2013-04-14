package bitter.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Timestamp implements Comparable<Timestamp> {

    private static final String format = "EEE, yyyy.MM.dd 'at' HH:mm z";
    private static final DateFormat formatter = new SimpleDateFormat(format);
    
    private Date time;

    public Timestamp() {
        time = new Date();
    }

    public Timestamp(Date date) {
        time = date;
    }

    public Date getStamp() {
        return time;
    }

    public String toPrintableString() {
        return formatter.format(time);
    }

    public int compareTo(Timestamp ts) {
        return this.time.compareTo(ts.time);
    }

} // End class Timestamp
