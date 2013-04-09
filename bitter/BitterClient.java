package bitter;

import java.net.*;
import java.io.*;

/**
 * BitterClient connects to a server that uses the Bitter protocol and acts as
 * a liason between that server and a user interface.
 *
 * BitterClient is modeled on the KnockKnockClient found in Oracle's sockets
 * tutorial at
 * http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockClient.java
 */
public class BitterClient {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String host = null;
        int port = Port.DEFAULT_PORT;
        try {
            host = getHost(args[0]);
            port = Port.parsePort(getPort(args[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("BitterClient must be called with first arugment" +
                             " of form hostname:port\n");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.print("Could not parse port\n");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.print("Port is not valid\n");
            System.exit(1);
        } catch (Exception e) {
            System.err.print("Crap, unknown exception\n");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.printf("Unknown host: %s\n", host);
            System.exit(1);
        } catch (IOException e) {
            System.err.printf("Couldn't get I/O for %s\n", host);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(
                              new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

        while ((fromServer = in.readLine()) != null) {
            System.out.println(fromServer);
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                out.println(fromUser);
            }
            if (fromUser.equals("exit"))
                break;
        }

        out.close();
        in.close();
        stdIn.close();
        socket.close();
    }

    private static String getPort(String hostAndPort) {
        return hostAndPort.split(":")[1];
    }

    private static String getHost(String hostAndPort) {
        return hostAndPort.split(":")[0];
    }

} // End class BitterClient
