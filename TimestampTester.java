import java.io.*;
import bitter.util.Timestamp;

public class TimestampTester {

    public static void main(String[] args) throws IOException {

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(
                                                    System.in));
        Timestamp ts;
        Timestamp other_ts;

        other_ts = new Timestamp();
        ts = new Timestamp();

        int difference = other_ts.compareTo(ts);
        String diff;
        switch (difference) {
        case 0:
            diff = "the same as";
            break;
        case 1:
            diff = "greater than";
            break;
        case -1:
            diff = "less than";
            break;
        default:
            diff = "well, crap";
            break;
        }
       

        System.out.printf("other_ts is %s ts\n", diff); 

        System.out.print("Hit enter to get the time; ");
        System.out.print("or exit to quit.\n");

        while (!stdIn.readLine().equals("exit")) {
            ts = new Timestamp();
            System.out.printf("Time: %s\n", ts.toPrintableString());
        }

    }

}
            
