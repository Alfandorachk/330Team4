/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bitter.net;

import java.io.*;
import bitter.*;
import bitter.util.*;

/**
 *
 * @author ryanogorman
 */
public class ServerSave {

	public static void write(MessageHandler mHash, UserHashMap uHash,
			UserLookupTable lTable) {

		FileOutputStream f_out;
		ObjectOutputStream obj_out;

		try {
			f_out = new FileOutputStream("messagehandler.data");
			obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(mHash);

			f_out.close();
			obj_out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		try {
			f_out = new FileOutputStream("userhash.data");
			obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(uHash); 

			f_out.close();
			obj_out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	
		try {
			f_out = new FileOutputStream("lookuptable.data");
			obj_out = new ObjectOutputStream(f_out);
			obj_out.writeObject(lTable);

			f_out.close();
			obj_out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	
	}

	public static Object read(String file) 
	throws IOException, ClassNotFoundException {

		FileInputStream fIn = new FileInputStream(file);
		ObjectInputStream objIn = new ObjectInputStream(fIn);
		
		Object obj = objIn.readObject();
		return obj;
	}
/*
    FileOutputStream f_out = new FileOutputStream("myobject.data");
    
    ObjectOutput Stream obj_out = new ObjectOutputStream(f_out);
    
    obj_out.writeObject(myObject);
*/
}
