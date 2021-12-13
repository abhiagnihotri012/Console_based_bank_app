package consoleBankApp;

public class MyBank {
	public String bankName;

	public MyBank(String bankName) {
		this.bankName = bankName;
	}
	
	void displayChoice() {
		String choicesArr[] = {"Register account.","Login.","Show Accounts.","Exit."};
		for (int i = 0; i < choicesArr.length; i++) {
			System.out.println((i+1)+"  "+choicesArr[i]);
		}
	}
	
}