package bitter.net;

import java.net.*;
import java.io.*;
import bitter.*;
import bitter.util.*;

/**
 * BitterServerThread handles a single connection from a client for
 * BitterServer.
 *
 * BitterServerThread is based on KKMultiServerThread.java from the Oracle
 * tutorial on sockets, found at
 * http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KKMultiServerThread.java
 */
public class BitterServerThread extends Thread {

	public static final String GREETING = "Enter a command to continue.";
	private Socket socket = null;
    private MessageHandler mHandler;
    private BitterProtocol protocol;
	private UserLookupTable lTable;
	private UserHashMap uHash;
	private BitterServer server;

	public BitterServerThread(Socket socket, MessageHandler mHandler,
			UserLookupTable lTable, UserHashMap uHash, BitterServer server) {
		super("BitterServerThread");
		this.socket = socket;
        this.mHandler = mHandler;
		this.lTable = lTable;
		this.uHash = uHash;
		this.server = server;
	}

	public void run() {
        protocol = new BitterProtocol(mHandler, lTable, uHash);
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
									new InputStreamReader(
									socket.getInputStream()));
			String inputLine, outputLine;
			out.println(GREETING);

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("exit")) {
					server.writeServer();
					break;
				}
				outputLine = protocol.processCommand(inputLine);
				server.writeServer();
				out.println(outputLine);
			} 

            out.close();
            in.close();
            socket.close();
		} catch (IOException e) {
			e.printStackTrace();
        }
	}

} // End class BitterServerThread
