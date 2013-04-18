package bitter.action;

import bitter.*;
import bitter.util.*;
import java.util.*;

public class RegisterStageOne implements Action {

	private static final String REGISTER_FORMAT =
		"register <$desired_name>";
	private static final int DESIRED_NAME = 1;

	private String username;
	private UserHashMap hashMap;
	private String error;

	public RegisterStageOne(List<String> terms, UserHashMap uHash) {
		username = "";
		if (terms.size() < 2) {
			error = "Register format: " + REGISTER_FORMAT;
		} else { 
			username = terms.get(DESIRED_NAME);
			if (uHash.containsKey(username)) {
				error = "Already user " + username;
			} else {
				uHash.putUser(username, "");
			}
		}
	}

	@Override
	public String doAction() {
		if (!error.equals("")) {
			return error;
		}
		return "Password: ";
	}

} // End Class Register
