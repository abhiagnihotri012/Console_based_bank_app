package consoleBankApp;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

public class Operations extends Register {
		String ch;
		User currentUser = null;
		boolean performOperations(String ch) {
			currentUser = Login.getLoginnedUser();
			Scanner input = new Scanner(System.in);
			switch (ch) {
			case "1":
				System.out.print("Enter Amount : ");
				double amt = input.nextDouble();
				Payments getBalances = new Payments();
				System.out.println("Current Bal : "+getBalances.getBalanceData());
				currentUser.setBalance(getBalances.getBalanceData()+amt);
				System.out.println("After Depositiong...\nBalance : "+currentUser.getBalance());
				Payments deposit = new Payments(currentUser.getUsername(), currentUser.getUsername(), amt, currentUser.getBalance());
				deposit.storePayments(deposit);
				return true;
//				break;
			case "2":
				System.out.print("Enter Payee username : ");
				String payeeName = input.next();
				System.out.print("Enter Amount : ");
				double transferAmt = input.nextDouble();
				
				
				if(findUser(currentUser.getUsername(), payeeName, transferAmt)) {
					try {
						System.out.println("Current Bal : "+currentUser.getBalance()+"  Amount : "+transferAmt);
						if(transferAmt > currentUser.getBalance()) {
							throw new BankExceptions("Insufficient Amount...");
						}
						currentUser.setBalance(currentUser.getBalance()-transferAmt);
						Payments transferFrom = new Payments(currentUser.getUsername(), payeeName, -transferAmt, currentUser.getBalance());
						transferFrom.storePayments(transferFrom);
						System.out.println("Transaction successful!!!");
					}catch(BankExceptions e) {
						System.err.println(e);
						break;
					}
					
				}
					
				break;
//			case "3":
//				Payments getBalance = new Payments();
//				System.out.println("Intial Deposit : "+ currentUser.getInitialDep());
//				System.out.println("Balance : "+getBalance.getBalanceData());
//				break;
			case "3":
//				System.out.println("Display User : "+currentUser.getUsername());
				currentUser.displayTransaction(currentUser.getUsername());
				return true;
			case "4":
				System.out.println("Accountholder name : "+currentUser.getName());
				System.out.println("Accountholder address : "+currentUser.getAddress());
				System.out.println("Accountholder contact : "+currentUser.getContact());
				return true;
			case "5":
				Login.setLoginnedUser(null);
				return false;
//			case "7":
//				Payments getPayments = new Payments();
//				getPayments.showPayments();
//				return true;
			default:
				break;
			}
			
			return true;
			
		}
		
		boolean findUser(String fromUser,String toUser,Double amt) {
			boolean userFound = false;
			ObjectInputStream ois = null;
			String inputFile = "resources1/users.db";
			 try {
				 ois = new ObjectInputStream(new FileInputStream(inputFile));
				 while(true) {
					 User user = (User) ois.readObject();
					 //System.out.println(user.toString());
				        if(toUser.equals(user.getUsername())) {
				        	if(toUser.equals(fromUser)) {
				        		throw new BankExceptions("That's your username...");
				        	}else {
				        		userFound = true;
				        	}
				        	break;
				        }
				 }
			        if(userFound) 
			        {
			        	return userFound;
			        }
			        
			 }
			 catch(BankExceptions e) {
				 System.err.println(e);
			 }
			 catch(EOFException e) {
				 System.err.println("Username doesn't exist.");
				 return false;
			 }
			 catch(Exception e) {
				 e.printStackTrace();
				 return userFound;
			 }
			 finally {
				 try {
					if(ois != null) ois.close();
//					if(oos != null) oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			return userFound;
		}
}