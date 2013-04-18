package bitter.action;

import bitter.util.*;
import bitter.*;
import java.util.*;

public class RegisterStageTwo implements Action {

	private static final int PASSWORD = 1;

	private String error;

	public RegisterStageTwo(List<String> terms, UserHashMap uHash,
			UserLookupTable lTable, String username) {
		error = "";
		if (terms.size() < 1) {
			error = "Need a password.  Aborting.";
		} else {
			String password = terms.get(PASSWORD);
			uHash.changePassword(username, password);
			User user = new User(username);
			lTable.addUser(user);
		}
	}

	@Override
	public String doAction() {
		if (!error.equals(""))
			return error;
		return "Registration successful!"; 
	}

} // End class RegisterStageTwo
