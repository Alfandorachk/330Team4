/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bitter.net;

/**
 *
 * @author ryanogorman
 */
public class ServerSave {
    FileOutputStream f_out = new FileOutputStream("myobject.data");
    
    ObjectOutput Stream obj_out = new ObjectOutputStream(f_out);
    
    obj_out.writeObject(myObject);
}
