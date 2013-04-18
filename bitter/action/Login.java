package bitter.action;

import bitter.util.*;
import bitter.*;
import java.util.*;

public class Login implements Action {

	private static final int USERNAME = 1;
	private static final String LOGIN_FORMAT = 
			"login <$username>";

	private String response; 

	public Login(List<String> terms, UserHashMap uHash) {
		response = "";
		if (terms.size() < 2) {
			response = "Login format: " + LOGIN_FORMAT;
		} else {
			String username = terms.get(USERNAME);	
			if (!uHash.containsKey(username)) {
				response = "No user: " + username;
			} else {
				response = "Password: ";
			}
		}
	}

	@Override
	public String doAction() {
		return response; 
	}

} // End class Login
