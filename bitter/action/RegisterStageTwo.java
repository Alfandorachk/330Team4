package bitter.action;

import bitter.util.*;

public class RegisterStageTwo {

	private static final int PASSWORD = 1;

	private String error;

	public RegisterStageTwo(List<String> terms, UserHashMap uHash,
			UserLookupTable lTable, String username) {
		error = "";
		if (terms.size() < 1) {
			error = "Need a password.  Aborting.";
		} else {
			String password = terms.get(PASSWORD);
			uhash.changePassword(username, password);
			User user = new User(username);
			lTable.addUser(username, user);
		}
	}

	@Override
	public String doStuff() {
		if (!error.equals(""))
			return error;
		return "Registration successful!"; 
	}

} // End class RegisterStageTwo
