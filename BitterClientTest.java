import bitter.*;

import java.net.*;
import java.io.*;

/**
 * Test for the BitterClient class.  Must have BitterServerTest running for this
 * test to succeed.
 */

public class BitterClientTest {

	public static void main(String[] args) {
		BitterClient client;
		String host, userIn, serverIn, out;
		int port;
		if (args.length < 1) {
			System.err.print("Must be called with hostname:port\n");
			System.exit(1);
		}
		host = getHost(args[0]);
		port = getPort(args[0]);
		try {
			client = new BitterClient(host, port);
		} catch (UnknownHostException e) {
			System.err.print("Unknown host: %s\n", host);
			System.exit(1);
		} catch (IOException e) {
			System.err.print("Could not get I/O for %s\n", host);
		}
		
		//TODO: finish implementing test
	}

	private static String getHost(String arg) {
		return arg.split(":")[0];
	}

	private static int getPort(String arg) {
		return Integer.parseInt(arg.split(":")[1]);
	}

} // End class BitterClientTest
