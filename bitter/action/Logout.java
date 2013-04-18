package bitter.action;

import bitter.*;
import bitter.util.*;
import java.util.*;

public class Logout implements Action {

	private String response;

	public Logout() {
		response = "Logged out";
	}

	@Override
	public String doAction() {
		return response;
	}

} // End class Logout
