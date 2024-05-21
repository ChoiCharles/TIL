package ch06.verify.example19;

public class Account {
	int balance;
	static final int MIN_BALANCE = 0;
	static final int MAX_BALANCE = 1000000;
	
	void setBalance(int value) {
		if (MIN_BALANCE <= value && value <= MAX_BALANCE) {
			this.balance = value;
		}
	}
	
	int getBalance() {
		return balance;
	}
}
