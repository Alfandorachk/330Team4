package bitter.action;

import bitter.util.*;
import bitter.*;
import java.util.*;

public class RegisterStageTwo implements Action {

	private static final int PASSWORD = 0;

	private String response;

	public RegisterStageTwo(List<String> terms, UserHashMap uHash,
			UserLookupTable lTable, String username) {
		response = "";
		if (terms.size() < 1) {
			response = "Need a password.  Aborting.";
		} else {
			String password = terms.get(PASSWORD);
			uHash.changePassword(username, password);
			System.out.print("Added password " + password + "\n");		// REMOVE
			User user = new User(username);
			lTable.addUser(user);
			response = "Registration successful!"; 
		}
	}

	@Override
	public String doAction() {
		return response;
	}

} // End class RegisterStageTwo
