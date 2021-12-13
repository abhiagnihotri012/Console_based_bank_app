package consoleBankApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {

	static void displayHeader(String name) {
		System.out.println("----------------------");
		System.out.println(name);
		System.out.println("----------------------");
	}

	public static void main(String[] args) throws IOException {

		MyBank bank = new MyBank("My Bank");
		App.displayHeader(bank.bankName);

		BufferedReader brInp = new BufferedReader(new InputStreamReader(System.in));
		Scanner input = new Scanner(System.in);
		
		Register reg = new Register();
		Login login = new Login();
		Operations ops = new Operations();
		DisplayAccounts accounts = new DisplayAccounts();
		
		String choice = "0";

		while (choice != "5") {
			bank.displayChoice();
			System.out.print("Enter your choice : ");
			choice = brInp.readLine();
			try {
				if(!Pattern.matches("[1-5]{1}", choice)) {
					throw new BankExceptions("Invalid Choice!!!    Enter Above Choices only [1-5]");
				}
			}catch(BankExceptions e) {
				System.err.println(e);
			}
			switch (choice) {
			case "1":
				if (reg.Registeration()) {
					App.displayHeader("Registered Successfully!!");
				}
				break;
			case "2":

				System.out.print("Enter username : ");
				String username = brInp.readLine();

				System.out.print("Enter Password : ");
				String password = brInp.readLine();
				boolean auth = login.checkUserLogin(username, password);
				if (auth) {
					App.displayHeader("W E L C O M E");
					login.displayLoginedChoice();
					System.out.print("Enter your choice : ");
					ops.ch = brInp.readLine();
					boolean notLogOut = ops.performOperations(ops.ch);
					while (notLogOut != false) {
						login.displayLoginedChoice();
						System.out.print("Enter your choice : ");
						ops.ch = brInp.readLine();
						notLogOut = ops.performOperations(ops.ch);
					}
				} else {
					System.err.println("Invalid Credentials");
					App.displayHeader("Login Again");
				}

				break;
//			case "3":
//				Update update = new Update();
//				update.Registeration();
//				break;
			case "3":
				App.displayHeader("All Registered Accounts");
				accounts.displayAccounts();
				break;
			case "4":
				App.displayHeader("THANK YOU");
				break;
			default:
				break;
			}
			
		}
		input.close();
	}

}
