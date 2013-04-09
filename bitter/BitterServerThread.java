package bitter;

import java.net.*;
import java.io.*;

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

	public BitterServerThread(Socket socket) {
		super("BitterServerThread");
		this.socket = socket;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(
									new InputStreamReader(
									socket.getInputStream()));
			String inputLine, outputLine;
			BitterProtocol protocol = new BitterProtocol();
			out.println(GREETING);

			while ((inputLine = in.readLine()) != null) {
				outputLine = protocol.processCommand(inputLine);
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

} // End class BitterServerThread
