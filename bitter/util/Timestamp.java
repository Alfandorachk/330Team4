package bitter.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Timestamp {

    private static final String format = "EEE, yyyy.MM.dd 'at' HH:mm z";
    private static final DateFormat formatter = new SimpleDateFormat(format);
    
    private Date time;

    public Timestamp() {
        time = new Date();
    }

    public Date getStamp() {
        return time;
    }

    public String toPrintableString() {
        return formatter.format(time);
    }

} // End class Timestamp
