package bitter;

import java.nio.file.*;

public class IOTester {

	public static void main(String[] args) {
		FileIO testMe = new FileIO();
		
		String blabbity = new String("BlabbityMcBlahBlah");
		String test2 = new String("Testing, testing");
		Path fileName = Paths.get("./src/messages.txt");
		
		testMe.writeToFile(blabbity, fileName);
		testMe.readFromFile(fileName);
		testMe.writeToFile(test2, fileName);
	}

}
