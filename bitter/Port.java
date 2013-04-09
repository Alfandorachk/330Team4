package bitter;

/**
 * Port is a utility class for the bitter package.  It handles processing of
 * ports, namely, parsing a port from a string and determining whether it is a
 * valid port.
 */
public class Port {

    /** Default port. */
	public static final int DEFAULT_PORT = 4943;
	/** Minimum allowable port number. */
	public static final int MIN_PORT = 1025;
	/** Maximum allowable port number. */
	public static final int MAX_PORT = 65535;

    /**
	 * Parses a string into a port.
	 * 
	 * @param port_string the string to be parsed.
     * @exception IllegalArgumentException if the port is not valid for Bitter
     * @exception NumberFormatException if port_string cannot be parsed
	 */
	public static int parsePort(String port_string)
    throws IllegalArgumentException, NumberFormatException {
		int port = DEFAULT_PORT;
		try {
			if (!isValidPort(port = Integer.parseInt(port_string))) {
                throw new IllegalArgumentException("Invalid port.");
			}
		} catch (NumberFormatException e) {
            throw e;
		}
		return port;
	}

	/**
	 * Determines whether a given integer is a valid port number for 
	 * BitterServer to listen on.
	 *
	 * @param port the port to be listened to.
	 */
	public static boolean isValidPort(int port) {
		return port >= MIN_PORT && port <= MAX_PORT;
	}

} // End class Port
