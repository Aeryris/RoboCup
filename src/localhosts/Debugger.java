package localhosts;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
 
/**
 * Debugger class for Java object.
 * 
 * @author <a href="mailto:robert.djokdja@gmail.com">Robertus Lilik Haryanto</a>
 * @version 1.0.0
 */
public class Debugger {
 
	// show info about an object on the console
	public static void object(Object ob, int depthWant, int playerId) throws Exception {
		int id = playerId;
		Class<?> c = ob.getClass();
		System.out.println("####################################################################################################################");
		System.out.println("####################################################################################################################");
		
		System.out.println("OBJECT INFO: PLAYER ID: " + id);
		
		System.out.println("Class = " + c);
 
                // get all fields
		Field[] fields = c.getDeclaredFields();
 
		System.out.println("Fields and values");
 
		for (int i = 0; i < fields.length; i++) {
			// in case they are private or protected
			fields[i].setAccessible(true);
 
			// show name and value
			System.out.println("- " + fields[i].getName() + " : "
					+ fields[i].get(ob));
		}
 
		// similarly for the superclass hierarchy
		Type type;
		for (int depth = 0; depth < depthWant; depth++) {
			type = c.getSuperclass();
			if (type == null)
				break; // in case we try to go above Object
			c = (Class<?>) type;
			System.out.println("SuperClass = " + c.getName());
			fields = c.getDeclaredFields();
 
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				System.out.println(fields[i].getName() + " : "
						+ fields[i].get(ob));
			}
		}
		
		System.out.println("####################################################################################################################");
		System.out.println("####################################################################################################################");
		
	}
}
