package bitter.action;

import bitter.*;
import bitter.util.*;
import java.util.*;

public class ViewProfile implements Action {

	private static final int VIEW_SUBJECT = 2;
	private static String FORMAT = "view profile <$username>";

	private Profile profile;
	private String error;
	private String name;
	
	public ViewProfile(List<String> terms, UserLookupTable lookupTable) {
		error = "";
		if (terms.size() < 3) {
			error = "View profile format: " + FORMAT;
		} else {
			name = terms.get(VIEW_SUBJECT);
			User user = lookupTable.lookupUser(name);
			if (user == null) {
				error = "No user: " + name;
			} else {
				profile = user.getProfile();
			}
		}
	} 

	@Override
	public String doAction() {
		if (!error.equals(""))
			return error;
		String results = "";
		results += "Name: " + profile.firstname + profile.lastname + "\n";
		results += "E-mail: " + profile.email + "\n";
		results += "Bio: " + profile.bio + "\n";	

		return results;
	}

} // End class ViewProfile
