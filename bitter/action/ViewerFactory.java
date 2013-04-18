package bitter.action;

import java.util.*;
import bitter.*;
import bitter.util.*;

public class ViewerFactory {

	private static final int VIEW_TYPE = 1;

	public static Action makeViewer(List<String> terms,
			MessageHandler mHandler, UserLookupTable lookupTable) {
		if (terms.size() < 2 || terms.get(VIEW_TYPE).equals("user") ||
				terms.get(VIEW_TYPE).equals("topic")) {
			return new ViewMessage(terms, mHandler); 
		} else if (terms.get(VIEW_TYPE).equals("profile")) {
			return new ViewProfile(terms, lookupTable);
		} else {
			return new ViewMessage(terms, mHandler); 
		}
	}

} // End class ViewerFactory.java
