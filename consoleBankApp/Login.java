package consoleBankApp;

import java.io.EOFException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Login extends Register {
	
	private static User loginnedUser;

	
	public boolean checkUserLogin(String username,String password)  {
		
		boolean logined = false;
		ObjectInputStream ois = null;
		String inputFile = "resources1/users.db";
		 try {
			 ois = new ObjectInputStream(new FileInputStream(inputFile));
			 while(true) {
				 User user = (User) ois.readObject();
				  if(username.equals(user.getUsername()) && password.equals(user.getPassword())) {
					  //System.out.println(user.toString());
					 logined = true;
					 this.setLoginnedUser(user);
					 break;
				 }
			 }
		        if(logined) {
		        	System.out.println("Login Successful!!!");
		        	return logined;
		        }
		        else {
		        	 System.err.println("Invalid Credentials");
		        	 return logined;
		        }
		        
		        
		 }
		 catch(EOFException e) {
			 return logined;
		 }
		 catch(Exception e) {
			 e.printStackTrace();
			 return logined;
		 }
		 finally {
	        	try {
	        		if(ois != null) ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		
	}
	
	void displayLoginedChoice() {
		String choicesArr[] = {"Deposit.","Transfer.","Last 5 Transactions.","User Information.","Log out"};
		for (int i = 0; i < choicesArr.length; i++) {
			System.out.println((i+1)+"  "+choicesArr[i]);
		}
	}
	public static User getLoginnedUser() {
		return loginnedUser;
	}

	public static void setLoginnedUser(User loginnedUser) {
		Login.loginnedUser = loginnedUser;
	}
}