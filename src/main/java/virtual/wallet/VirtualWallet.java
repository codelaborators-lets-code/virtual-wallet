package virtual.wallet;

import java.util.TimerTask;

public class VirtualWallet extends TimerTask {

	private int funds;
	private String userName;
	private int numTransactions;
	private int scheduledAmt;

	public int getFunds() {
		return funds;
	}

	public String getUserName() {
		return userName;
	}

	public void setScheduledAmt(int scheduledAmt) {
		this.scheduledAmt = scheduledAmt;
	}

	public VirtualWallet(String userName, int funds) {
		this.funds = funds;
		this.userName = userName;

		this.numTransactions = 0; // default value
		this.scheduledAmt = 0; // default value
	}

	public void addFunds(int amtToAdd) {
		funds += amtToAdd;
		numTransactions++;

		isTransactionTotal10();
	}

	public void removeFunds(int amtToRemove) {
		funds -= amtToRemove;
		numTransactions++;

		isTransactionTotal10();
	}

	public void schedulePayment(int numPayments, int amtToRemove) {
		for (int i = 0; i < numPayments; i++) {
			funds -= amtToRemove;
			numTransactions++;

			isTransactionTotal10();

		}

	}

	public void receivePayment(int amtToAdd) {
		funds += (amtToAdd - 2);
		numTransactions++;

		isTransactionTotal10();
	}

	private void isTransactionTotal10() {
		if (numTransactions == 10) {
			funds += 1;
			numTransactions = 0;
		}
	}

	@Override
	public void run() {
		this.removeFunds(scheduledAmt);
		if (this.getFunds() < 50) {
			System.out.println("Add more funds! Funds are low!");
		}

		if (this.getFunds() <= 0) {
			System.out.println("Out of funds!");
			System.exit(0);
		}

	}

}
