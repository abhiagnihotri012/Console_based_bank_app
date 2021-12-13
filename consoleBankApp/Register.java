package consoleBankApp;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;

import java.io.ObjectOutputStream;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register {

	String name;
	String address;
	String contact;
	protected String username;
	protected String password;
	String InitialDep;
	private static File f = new File("resources1/users.db");

	public boolean Registeration() throws IOException {
		BufferedReader brInp = new BufferedReader(new InputStreamReader(System.in));
		List<User> users = new ArrayList<User>();	
		System.out.print("Enter Name : ");
		name = brInp.readLine();

		System.out.print("Enter address : ");
		address = brInp.readLine();

		System.out.print("Enter Contact number : ");
		contact = brInp.readLine();

		System.out.print("Set Username : ");
		username = brInp.readLine();

		System.out.print("Set Password : ");
//		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*_]])(?=\\\\\\\\S+$).{8,12}$";
		password = brInp.readLine();
		boolean verify = passwordValidation(password);
		while (verify != true) {
			System.out.print("Set a password with minimum 8 chars, 1 digit, 1 lowercase, 1 uppercase and 1 special character[!@#$%^&*_])\nSet Again : ");
			password = brInp.readLine();
			verify = passwordValidation(password);
		}

		System.out.print("Enter Initial Deposit : ");
		InitialDep = brInp.readLine();
		String outputFile = "resources1/users.db";
		try {
			if (f.length() == 0) {
				ObjectOutputStream oos = null;
				oos = new ObjectOutputStream(new FileOutputStream(outputFile, true));
				users.add(
						new User(name, address, contact, username, password, InitialDep, Integer.parseInt(InitialDep)));
				for (User user : users)
					oos.writeObject(user);
				oos.close();
			} else {
				MyObjectOutputStream oos = null;
				oos = new MyObjectOutputStream(new FileOutputStream(outputFile, true));
				users.add(
						new User(name, address, contact, username, password, InitialDep, Integer.parseInt(InitialDep)));
				for (User user : users) {
					System.out.println(user.toString());
					oos.writeObject(user);
				}
				oos.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	boolean passwordValidation(String password) {
		String regex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[!@#$%^&*_])" + "(?=\\S+$).{8,20}$";
		Pattern p = Pattern.compile(regex);
		try {

			if (password == "") {
				throw new BankExceptions("Must Enter Password");
			} else {
				Matcher matcher = p.matcher(password);
				if (!matcher.matches()) {
					throw new BankExceptions("Invalid Password Condition");
				}
			}
		} catch (BankExceptions e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
}

