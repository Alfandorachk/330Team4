package bitter.util;

import java.util.concurrent;

class UserHashMap {

	private ConcurrentHashMap<String, String> hashMap;

	public RegistrationHashMap() {
		hashMap = new ConcurrentHashMap<String,String>();
	}

	public void putRegistrant(String UN, String pass) {
		hashMap.put(UN, pass);
	}

	public void getRegistrants () {
		hashMap.elements();
	}
}
