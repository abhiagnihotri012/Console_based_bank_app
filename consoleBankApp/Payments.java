package consoleBankApp;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class Payments implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private String from;
	private String to;
	private double amount;
	private double balance;
	List<Payments> payments = new ArrayList<Payments>();
	private static File f = new File("resources1/payments.db");
	Date date = new Date(); 
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	String paymentDate;
	
	ObjectInputStream oispayment = null;
	ObjectInputStream oisuser = null;
	String paymentFile = "resources1/payments.db";
	String userFile = "resources1/users.db";
	
	public Payments() {
		
	}
	
	public Payments(String from, String to, double amount, double balance) {
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.balance = balance;
		this.paymentDate = String.format(dateFormat.format(date));
	}
	

	@Override
	public String toString() {
		return "Payments [from=" + from + ", to=" + to + ", amount=" + amount + ", balance=" + balance + ", date="
				+ date + "]";
	}

	void storePayments(Payments p) {
		String outputFile = "resources1/payments.db";
		try {
			if (f.length() == 0) {
				ObjectOutputStream oos = null;
				oos = new ObjectOutputStream(new FileOutputStream(outputFile, true));
				payments.add(p);
				for (Payments payment : payments)
					oos.writeObject(payment);
				oos.close();
			} else {
				MyObjectOutputStream oos = null;
				oos = new MyObjectOutputStream(new FileOutputStream(outputFile, true));
				payments.add(p);
				for (Payments payment : payments)
					oos.writeObject(payment);
				oos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void getPayments(String username) {
		User loginuser = Login.getLoginnedUser();
		 try {
			 oispayment = new ObjectInputStream(new FileInputStream(paymentFile));
			 oisuser = new ObjectInputStream(new FileInputStream(userFile));
			 while(true) {
				 User user = (User) oisuser.readObject();
				 if(username.equals(user.username)) {
				while(true) {
					 Payments payment = (Payments) oispayment.readObject();
						 if(loginuser.getUsername().equals(payment.from) || loginuser.getUsername().equals(payment.to)) {
							 if(payment.amount >= 0 && loginuser.getUsername().equals(payment.to)) {
								 System.out.println("Rs."+payment.amount+" credited to your account. Balance - Rs. "+(payment.balance)+" as on "+payment.paymentDate);
							 }
							 if(payment.amount <0) {
								 if(loginuser.getUsername().equals(payment.to)) {
									 System.out.println("Rs."+Math.abs(payment.amount)+" credited to your account. Balance - Rs. "+loginuser.getBalance()+" as on "+payment.paymentDate);
									 
								 }else {
									 System.out.println("Rs."+Math.abs(payment.amount)+" debited from your account. Balance - Rs. "+payment.balance+" as on "+payment.paymentDate);
								 }
								 
								 
							 }							 
						 }
						
					 }
				}
			 }    
		 }
		 catch(EOFException e) {
			 System.out.println("Last 5 transactions...");
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 finally {
	        	try {
	        		if(oispayment != null) oispayment.close();
	        		if(oisuser != null) oisuser.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		 
	}
	
	double getBalanceData() {
		LinkedList<Double> getbal = new LinkedList<Double>();
		
		User loginUser = Login.getLoginnedUser();
		double balance = loginUser.getBalance();
		 try {
			 oispayment = new ObjectInputStream(new FileInputStream(paymentFile));
			 oisuser = new ObjectInputStream(new FileInputStream(userFile));
			 while(true) {
					Payments payment = (Payments) oispayment.readObject();
					 
					 if(loginUser.getUsername().equals(payment.from) || loginUser.getUsername().equals(payment.to)){
						 getbal.addFirst(payment.balance);
						 System.out.println(getbal);
					 }
			 }
			 
		 }
		 catch(EOFException e) {
			 System.out.println("");
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 finally {
	        	try {
	        		if(oispayment != null) oispayment.close();
	        		if(oisuser != null) oisuser.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
		 try {
			 double upd_bal = getbal.getFirst();
			 return upd_bal;
		 }catch(NoSuchElementException e) {
			 return balance;
		 }
		
	}
	
	
	void showPayments() {
		 try {
			 oispayment = new ObjectInputStream(new FileInputStream(paymentFile));
			 while(true) {
				 Payments payment = (Payments) oispayment.readObject();
				 System.out.println(payment.toString());
			 }
			 
		 }
		 catch(EOFException e) {
			 System.out.println("");
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 finally {
	        	try {
	        		if(oispayment != null) oispayment.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	}
	
}
