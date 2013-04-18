package bitter.action;

import java.util.*;
import bitter.*;

public class ViewerFactory.java {

	public static Action makeViewer(List<String> terms,
			MessageHandler mHandler) {
		if (terms.size() < 2 || terms.get(2).equals("user") ||
				terms.get(2).equals("topic") {
			return new ViewMessage(terms, mHandler); 
		} else if (terms.get(2).equals("profile")) {
			return new ViewProfile(terms);
		} else {
			return new ViewMessage(terms, mHandler); 
		}
	}

} // End class ViewerFactory.java
