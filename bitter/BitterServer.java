package bitter;

import java.io.*;
import java.net.*;

/**
 * BitterServer listens for incoming connections from BitterClient.  Once a
 * connection has been accepted, it passes that connection off to an instance
 * of BitterServerThread. 
 * 
 * This class is largely modeled after KKMultiServer.java from the Java sockets
 * tutorial, 
 * http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KKMultiServer.java
 */
public class BitterServer {

	public static final int DEFAULT_PORT = 4444;

	/**
	 * Listens for connections from BitterClient, and passes them off to
	 * BitterServerThread.  BitterServer listens to the port passed in from the
	 * command line.  If no port is specified, BitterServer listens at the
	 * default port.  If a passed-in port is invalid for any reason,
	 * BitterServer terminates immediately.
	 *
	 * @param args 	The first argument is the port to be listened to.  Any
	 *				further arguments are simply discarded.
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		boolean listening = true;
		int port;

		if (args.length > 0) 
			port = setPort(args[0]);
		else
			port = DEFAULT_PORT;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.print("Could not listen on port: %d\n", port):
			System.exit(1);
		}

		while (listening)
			new	BitterServerThread(serverSocket.accept()).start();

		serverSocket.close();
	}

	/**
	 * Parses the port argument passed in from the command line.
	 * 
	 * @param port the first argument from the command line.
	 */
	private static int setPort(String port) {
		try {
			if (!isValidPort(port = Integer.parseInt(args[0]))) {
				System.err.print("Invalid port selected. Aborting.\n");
				System.exit(1);
			}
		} catch (NumberFormatException e) {
			System.err.print("Port must be an integer. Aborting.\n");
			System.exit(1);
		}
	}

	/**
	 * NOT YET IMPLEMENTED. Determines whether a given integer is a valid
	 * port number for BitterServer to listen on.
	 *
	 * @param port the port to be listened to.
	 */
	private static boolean isValidPort(int port) {
		//TODO: implement this method
		return true;
	}
} // End class BitterServer
