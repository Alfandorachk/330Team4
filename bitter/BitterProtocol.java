package bitter;

import java.net.*;
import java.io.*;

/**
 * BitterProtocol is the protocol for the BitterClient/BitterServer
 * interactions.  This is a _very_ early version of the protocol,
 * implemented only to ease in testing other elements of the system.
 * <p>
 * This class is modeled on the KnockKnockProtocol class from the Oracle
 * tutorial on sockets, found at
 * http://docs.oracle.com/javase/tutorial/networking/sockets/examples/KnockKnockProtocol.java
 */
public class BitterProtocol {
	
	public String processCommand(String command) {
		String output = null;
        if (command == null) 
            output = "Hello?\n";
        else
            output = new String(command);
		return output;
	}
		
} // End class BitterProtocol
