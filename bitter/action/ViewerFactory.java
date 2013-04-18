package bitter.action;

import java.util.*;
import bitter.*;
import bitter.util.*;

public class ViewerFactory {

	public static Action makeViewer(List<String> terms,
			MessageHandler mHandler, UserLookupTable lookupTable) {
		if (terms.size() < 2 || terms.get(2).equals("user") ||
				terms.get(2).equals("topic")) {
			return new ViewMessage(terms, mHandler); 
		} else if (terms.get(2).equals("profile")) {
			return new ViewProfile(terms, lookupTable);
		} else {
			return new ViewMessage(terms, mHandler); 
		}
	}

} // End class ViewerFactory.java
