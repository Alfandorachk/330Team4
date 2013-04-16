package bitter;

import java.nio.file.*;

public class IOTester {

	public static void main(String[] args) {
		FileIO testMe = new FileIO();
		
		String blabbity = new String("BlabbityMcBlahBlah");
		Path fileName = Paths.get("./messages.txt");
		
		testMe.writeToFile(blabbity, fileName);
		testMe.readFromFile(fileName);
	}

}
