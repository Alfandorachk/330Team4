package bitter.net;

import java.io.*;
import java.net.*;
import bitter.*;
import bitter.util.*;

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


	public MessageHandler mHandler;
	public UserLookupTable lTable;
	public UserHashMap uHash;
	private boolean listening;

	public BitterServer() {
		try {
			mHandler = (MessageHandler) ServerSave.read("messagehandler.data");
		} catch (IOException e) {
			System.out.println(
					"Could not read MessageHandler file, defaulting.");
			mHandler = new MessageHandler();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: MessageHandler, defaulting.");
			mHandler = new MessageHandler();
		}
		try {
			lTable = (UserLookupTable) ServerSave.read("lookuptable.data");
		} catch (IOException e) {
			System.out.println("Could not read UserLookup file, defaulting.");
			lTable = new UserLookupTable();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: UserLookupTable, defaulting.");
			lTable = new UserLookupTable();
		}
		try {
			uHash = (UserHashMap) ServerSave.read("userhash.data");
		} catch (IOException e) {
			System.out.println("Could not read UserHash file, defaulting.");
			uHash = new UserHashMap();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: UserHashMap, defaulting.");
			uHash = new UserHashMap();
		}
		listening = true;
	}

	public synchronized void writeServer() {
		ServerSave.write(mHandler, uHash, lTable);
	}

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
		int port = Port.DEFAULT_PORT;
		BitterServer server = new BitterServer();
/*        MessageHandler mHandler = new MessageHandler();
		UserLookupTable lTable = new UserLookupTable();
		UserHashMap uHash = new UserHashMap();
*/
		if (args.length > 0) {
            String port_arg = args[0];
            try {
                port = Port.parsePort(port_arg);
            } catch (NumberFormatException e) {
                System.err.printf("Could not parse %s\n", port_arg);
                System.exit(1);
            } catch (IllegalArgumentException e) {
                System.err.printf("%s is not a valid port\n", port_arg);
                System.exit(1);
            } catch (Exception e) {
				System.err.printf("I have no idea what happened.\n");
				e.printStackTrace();
				System.exit(1);
			}
        }

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.err.printf("Could not listen on port: %d\n", port);
			System.exit(1);
		}

		System.out.printf("Server started; listening on port %d\n", port);

		while (server.listening)
			new	BitterServerThread(serverSocket.accept(), server.mHandler,
					server.lTable, server.uHash, server).start();

		serverSocket.close();
	}

} // End class BitterServer
