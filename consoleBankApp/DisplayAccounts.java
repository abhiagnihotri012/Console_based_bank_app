package consoleBankApp;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DisplayAccounts {
	User user = null;
	public boolean displayAccounts() {
		ObjectInputStream ois = null;
		String inputFile = "resources1/users.db";
		 try {
			 ois = new ObjectInputStream(new FileInputStream(inputFile));
			 int i=1;
			 while(true) {
				 user = (User) ois.readObject();
				 System.out.print((i++)+") ");
				 System.out.println("Username   : "+user.getUsername()+" ");
				 System.out.println("   Contact    : "+user.getContact()+" ");
				 System.out.println("   Address    : "+user.getAddress()+" ");
				 System.out.println();
			 }
		 }
		 
		 catch(EOFException e) {
			 System.out.println("End of Records...\n");		 
			 return false;
		 }
		 catch(Exception e) {
			 return false;
		 }
		 finally {
			 try {
				if(ois != null) ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
	}

}