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
    private User user;

	public BitterServerThread(Socket socket, MessageHandler mHandler,
			UserLookupTable lTable) {
		super("BitterServerThread");
		this.socket = socket;
        this.mHandler = mHandler;
		this.lTable = lTable;
	}

	public void run() {
        user = getUser();   // FOR TESTING PHASE ONLY
		lTable.addUser(user);	// FOR TESTING PHASE ONLY
        protocol = new BitterProtocol(user, mHandler, lTable);
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
									new InputStreamReader(
									socket.getInputStream()));
			String inputLine, outputLine;
			out.println(GREETING);

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("exit"))
					break;
				outputLine = protocol.processCommand(inputLine);
				out.println(outputLine);
			} 

            out.close();
            in.close();
            socket.close();
		} catch (IOException e) {
			e.printStackTrace();
        }
	}

    private User getUser() {
        return new User("guest");
    }

} // End class BitterServerThread
