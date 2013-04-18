package bitter.action;

import bitter.*;
import java.util.*;

public class ChangeProfile implements Action {

	private static final String CHANGE_FORMAT =
			"change [firstname|lastname|email|bio] \"new value\"";
	private static final int CHANGE_FIELD = 1;
	private static final int CHANGE_VALUE = 2;

	private String response;

	public ChangeProfile(List<String> terms, User user) {

		response = "";
		if (terms.size() < 3) {
			response = "Edit profile syntax: " + CHANGE_FORMAT;
		} else {
			String newVal = terms.get(CHANGE_VALUE);
			switch (terms.get(CHANGE_FIELD).toLowerCase()) {
			case "firstname":
				user.getProfile().firstname = newVal;
				response = "First name -> " + newVal;
				break;
			case "lastname":
				user.getProfile().lastname = newVal;
				response = "Last name -> " + newVal;
				break;
			case "email":
				user.getProfile().email = newVal;
				response = "E-mail -> " + newVal;
				break;
			case "bio":
				user.getProfile().bio = newVal;
				response = "Bio -> " + newVal;
				break;
			default:
				response = "Edit profile syntax: " + CHANGE_FORMAT;
			}
		}
	}

	@Override
	public String doAction() {
		return response;
	}

} // End class ChangeProfile
