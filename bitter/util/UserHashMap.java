package bitter.util;

import java.util.concurrent.*;

public class UserHashMap {

	private ConcurrentHashMap<String, String> hashMap;

	public UserHashMap() {
		hashMap = new ConcurrentHashMap<String,String>();
	}

	public boolean containsKey(String username) {
		return hashMap.containsKey(username);
	}

	public void putUser(String username, String password) {
		hashMap.putIfAbsent(username, password);
	}

	public void changePassword(String username, String password) {
		hashMap.put(username, password);
	}

	public String getPassword(String username) {
		return hashMap.get(username);
	}

} // End class UserHashMap
