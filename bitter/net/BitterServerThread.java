package bitter.net;

import java.net.*;
import java.io.*;
import bitter.*;

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
    private User user;

	public BitterServerThread(Socket socket, MessageHandler mHandler) {
		super("BitterServerThread");
		this.socket = socket;
        this.mHandler = mHandler;
		System.out.print("Got here:  Thread constructor\n"); // REMOVE
	}

	public void run() {
		System.out.print("Got here: Thread run()\n"); //REMOVE
        user = getUser();
        protocol = new BitterProtocol(user, mHandler);
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
									new InputStreamReader(
									socket.getInputStream()));
			String inputLine, outputLine;
			System.out.printf("Sending greeting %s\n", GREETING);
			out.println(GREETING);

			while ((inputLine = in.readLine()) != null) {
				outputLine = protocol.processCommand(inputLine);
				System.out.printf("Responding with %s\n", outputLine); // REMOVE
				out.println(outputLine);
				if (outputLine.equals("exit"))
					break;
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
