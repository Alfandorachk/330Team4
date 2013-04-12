package bitter;

import java.net.*;
import java.io.*;

/**
 * BitterClient connects to a server that uses the Bitter protocol and acts
 * as a liason between that server and a user interface.
 *
 * BitterClient is modeled on the KnockKnockClient found in Oracle's sockets
 * tutorial at
 * http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockClient.java
 */
public class BitterClient {

        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String host;
        private int port;

	/**
	 * Creates a new BitterClient that will connect to the given host and
	 * port.
	 * @param host the host's IP address or FQDN
	 * @param port the port at which to connect
	 */
	public BitterClient(String host, int port) {
		this.host = host;
		this.port = port;
		socket = null;
		out = null;
		in = null;
	}

	/**
	 * Connects to the server.
	 *
	 * @throws IOException if the client could not open I/O on the socket
	 * @throws UnkownHostException if the host could not be contacted
	 */
	public void connect() throws IOException, UnknownHostException {
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
	}

	/**
	 * Closes all connections formed in connecting to server.
	 */
	public void close() throws IOException {
        out.close();
        in.close();
        socket.close();
	}

	/**
	 * Gets the next communication from the server.
	 * @return the next communication from the server
	 */
	public String getServerResponse() throws IOException {
	   return in.readLine();
	}

	/**
	 * Sends a communication to the server.
	 * @param the communication to the server
	 */
	public void sendCommunication(String message) {
		out.println(message);
	}

	/**
	 * Creates a new BitterClient that attempts to connect to a server, then
	 * follows the protocol of that server.
	 * @param args the first argument must be of the form hostname:port
	 * 			   further arguments are ignored
	 */
    public static void main(String[] args) throws IOException {
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
            System.out.println(fromServer);
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

} // End class BitterClient
