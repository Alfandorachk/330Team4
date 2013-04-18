package bitter.net;

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
       String response = "";
	   String line;
       while ((line = in.readLine()) != null) {
		   System.out.printf("LINE IS %s\n", line);
		   if (line.contains("END TRANSMISSION")) {
			   System.out.print("IT CONTAINS IT BLARG\n");
			   break;
		   }
           response += line + "\n";
       }

	   return response;
	}

	/**
	 * Sends a communication to the server.
	 * @param the communication to the server
	 */
	public void sendCommunication(String message) {
		out.println(message);
	}

} // End class BitterClient
