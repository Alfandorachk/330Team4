package bitter.util;

import bitter.*;
import java.util.concurrent.*;

/**
 * UserLookupTable associates Users with their username.  This is primarily
 * for converting the text-based commands of the user in to Bitter system
 * objects.
 */
public class UserLookupTable {
	
	ConcurrentHashMap<String, User> userMap;
	
	/**
	 * Initializes UserLookupTable() to an empty state.
	 */
	public UserLookupTable() {
		userMap = new ConcurrentHashMap<String, User>();
	}

	/**
	 * Gets the User object with a given username.
	 *
	 * @param username the name of the user desired
	 * @return the user with the given username, or null if none is found
	 */
	public User lookupUser(String username) {
		return userMap.get(username);
	}

	/**
	 * Associates the username of the given user with that user in the
	 * lookup table.
	 *
	 * @param user the user to be entered into the table
	 * @return the previous user that was associated with that name, or null
	 * 			(This should always return null)
	 */
	public User addUser(User user) {
		String username = user.getName(); 
		User prevVal = userMap.get(username);
		if (!userMap.containsKey(username)) {
			userMap.putIfAbsent(username, user);
		}
		return prevVal;
	}

} // End class UserLookupTable
