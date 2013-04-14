import java.io.*;
import bitter.util.Timestamp;

public class TimestampTester {

    public static void main(String[] args) throws IOException {

        BufferedReader stdIn = new BufferedReader( new InputStreamReader(
                                                    System.in));
        Timestamp ts;

        System.out.print("Hit enter to get the time; ");
        System.out.print("or exit to quit.\n");

        while (!stdIn.readLine().equals("exit")) {
            ts = new Timestamp();
            System.out.printf("Time: %s\n", ts.toPrintableString());
        }

    }

}
            
