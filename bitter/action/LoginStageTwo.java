package bitter.action;

import bitter.*;
import bitter.util.*;
import java.util.*;

public class LoginStageTwo implements Action {

	private static final int PASSWORD = 0;
	
	private String response;

	public LoginStageTwo(List<String> terms, UserHashMap uHash,
			String username) {
		response = "";
		if (terms.size() < 1) {
			response = "Password needed.  Aborting.";
		} else {
			String password = terms.get(PASSWORD);
			if (uHash.getPassword(username).equals(password)) {
				response = "Success";
			} else {
				response = "Incorrect username/password";
			}
		}
	}

	@Override
	public String doAction() {
		return response;
	}

} // End class LoginStageTwo
