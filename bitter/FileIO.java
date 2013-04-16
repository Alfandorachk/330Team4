package bitter;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;

public class FileIO {
	final public Charset CHARSET;
	public static StandardOpenOption NLINE = StandardOpenOption.APPEND;
	public static StandardOpenOption MAKE = StandardOpenOption.CREATE;
	public static StandardOpenOption WRITE = StandardOpenOption.WRITE;
	
	public FileIO() {
		CHARSET = Charset.forName("US-ASCII");
	}
	public void writeToFile (String text, Path file) {
		try (BufferedWriter writer = Files.newBufferedWriter(file, CHARSET, NLINE, MAKE, WRITE)) {
		    writer.write(text, 0, text.length());
		    writer.newLine();
		    writer.close();
		   
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
	public void readFromFile (Path file) {
		try (BufferedReader reader = Files.newBufferedReader(file, CHARSET)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        System.out.println(line);
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
	}
}
