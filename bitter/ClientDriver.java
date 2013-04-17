package bitter;

import bitter.net.*;
import java.net.*;
import java.io.*;

public class ClientDriver {

    /**
	 * Creates a new BitterClient that attempts to connect to a server, then
	 * follows the protocol of that server.
	 * @param args the first argument must be of the form hostname:port
	 * 			   further arguments are ignored
	 */
    public static void main(String[] args) throws IOException {
        //TODO: Refactor this puppy
		String host = null;
		int port = Port.DEFAULT_PORT;
		
		// Pull host and port from command line
        try {
            host = extractHost(args[0]);
            port = Port.parsePort(extractPort(args[0]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.print("BitterClient must be called with first" +
							 " argument of form hostname:port.\n");
            System.exit(1);
        } catch (NumberFormatException e) {
            System.err.print("Could not parse port.\n");
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.err.print("Port is not valid.\n");
            System.exit(1);
        } catch (Exception e) {
            System.err.print("Crap, unknown exception\n");
            e.printStackTrace();
            System.exit(1);
        }

		BitterClient client = null;
		client = new BitterClient(host, port);

		try {
			client.connect();
		} catch (UnknownHostException e) {
			System.err.printf("Unknown host: %s.\n", host);
			System.exit(1);
		} catch (IOException e) {
			System.err.printf("Could not open IO for connection to %s.\n", host);
			System.err.print("Make sure the right port was specified.\n");
			System.exit(1);
		}

        BufferedReader stdIn = new BufferedReader(
                              new InputStreamReader(System.in));
        String fromServer;
        String fromUser;

		// Program loop
        while ((fromServer = client.getServerResponse()) != null) {
            System.out.printf("%s\n>> ", fromServer);
            fromUser = stdIn.readLine();
            if (fromUser != null) {
                client.sendCommunication(fromUser);
            }
            if (fromUser.equals("exit")) {
			   	break;
			}
        }

		stdIn.close();
		client.close(); 
    }

    private static String extractPort(String hostAndPort) {
        return hostAndPort.split(":")[1];
    }

    private static String extractHost(String hostAndPort) {
        return hostAndPort.split(":")[0];
    }

} // End class ClientDriver
