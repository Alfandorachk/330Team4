package bitter.action;

import bitter.util.*;

public class RegisterStageTwo {

	private String error;

	public RegisterStageTwo(List<String> terms, UserHashMap uHash,
			UserLookupTable lTable) {
		error = "";
		if (terms.size() < 1) {
			error = "Need a password.  Aborting.";
		} else {
			uhash.changePassword(
		}
	}

	@Override
	public String doStuff() {

	}

} // End class RegisterStageTwo
