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

	Socket socket;
	PrintWriter out;
	BufferedReader in;
	String host;
	int port;

	/**
	 * Opens a connection to the given host on the given port.
	 * 
	 * @param host the unqualified name of the server to be connected to
	 * @param port the port at which the server is listening
	 */
	public BitterClient(String host, int port) {
		this.host = host;
		this.port = port;
		connectToServer();
		socket = new Socket(host, port);
		out = new PrintWriter(socket.getOutPutStream, true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	/**
	 * Closes the various connections and streams that have been opened.
	 */
	public closeConnections() {
		out.close();
		in.close();
		socket.close();
	}

} // End class BitterClient
