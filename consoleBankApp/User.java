package consoleBankApp;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected String address;
	protected String contact;
	protected String username;
	protected String password;
	protected String InitialDep;
	protected double balance;
	HashMap<Double, Integer> transactions = new HashMap<Double, Integer>();
	static int countUsers = 0;
	Date date = new Date(); 
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	String initDepDate;
	int transid=1;


	public User(String name, String address, String contact, String username, String password, String initialDep,double balance) {
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.username = username;
		this.password = password;
		this.InitialDep = initialDep;
		this.balance = balance;
		countUsers++;
		this.initDepDate = String.format(dateFormat.format(date));
	}


	@Override
	public String toString() {
		return "User {Name : " + name + " , Address : " + address + " , Contact : " + contact + " , UserName : " + username
				+ " , Password : " + password + " , Initial Deposit : " + InitialDep + " , Balance=" + balance + "}";
	}



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getInitialDep() {
		return InitialDep;
	}


	public void setInitialDep(String initialDep) {
		InitialDep = initialDep;
	}


	public static int getCountUsers() {
		return countUsers;
	}


	public static void setCountUsers(int countUsers) {
		User.countUsers = countUsers;
	}


	public double getBalance() {
		return this.balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public void addTransaction(double amount,int transno) {
		transactions.put(amount,transno);
	}

	public void displayTransaction(String user) {
		System.out.println(String.format("Intital Deposit - Rs "+this.InitialDep+" as on "+this.initDepDate));
    	try {
    		Payments getPayment = new Payments();
    		getPayment.getPayments(user);
    		
    	}catch(NullPointerException e) {
    		System.out.println("No Transaction Done!!! Only initial deposited");

    	}
		
	}
	//long accountNo = (long) Math.floor(Math.random() * 9000000000L) + 1000000000L;

}