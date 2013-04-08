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

	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String host;
	private int port;

	/**
	 * Opens a connection to the given host on the given port.
	 * 
	 * @param host the unqualified name of the server to be connected to
	 * @param port the port at which the server is listening
	 * @throws IOException if an I/O connection cannot be made with the server
	 */
	public BitterClient(String host, int port) throws IOException {
		this.host = host;
		this.port = port;
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * Closes the various connections and streams that have been opened.
	 */
	public void closeConnections() throws IOException {
		out.close();
		in.close();
		socket.close();
	}

} // End class BitterClient
