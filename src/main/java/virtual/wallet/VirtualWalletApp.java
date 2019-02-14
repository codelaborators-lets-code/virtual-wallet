package virtual.wallet;

import java.util.Scanner;
import java.util.Timer;

public class VirtualWalletApp {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		Timer timer = new Timer();
		
		//update myWallet with the 3rd argument in the constructor for the password
		VirtualWallet myWallet = new VirtualWallet("Alan", 100);
		
		//do not allow the user to move into the menu unless the password has been verified

		System.out.println("Press 1 to check funds");
		System.out.println("Press 2 to add funds");
		System.out.println("Press 3 to remove funds");
		System.out.println("Press 4 to receive payment");
		System.out.println("Press 5 to schedule a payment");
		System.out.println("Press -1 to exit");
		String choice = input.nextLine();

		while (!choice.equals("-1")) {
			if (choice.equals("1")) {
				System.out.println("Current funds: " + myWallet.getFunds());
			} else if (choice.equals("2")) {
				System.out.println("How much are you adding?");
				int amtToAdd = input.nextInt();
				myWallet.addFunds(amtToAdd);
				input.nextLine(); // needed for spacing in the user menu
			} else if (choice.equals("3")) {
				System.out.println("How much are you removing?");
				int amtToRemove = input.nextInt();
				myWallet.removeFunds(amtToRemove);
				input.nextLine();
			} else if (choice.equals("4")) {
				System.out.println("How much are you getting paid?");
				int amtToAdd = input.nextInt();
				myWallet.receivePayment(amtToAdd);
				input.nextLine();
			} else if (choice.equals("5")) {
				System.out.println("How much do you want to schedule?");
				int amount = input.nextInt();
				input.nextLine();
				myWallet.setScheduledAmt(amount);
				timer.schedule(myWallet, 0, 2000);
				System.out.println("Payment of " + amount + " is scheduled.");
			} else {
				System.out.println("Sorry I don't recognize your input.");
			}

			System.out.println("What else would you like to do?");
			System.out.println("Press 1 to check funds");
			System.out.println("Press 2 to add funds");
			System.out.println("Press 3 to remove funds");
			System.out.println("Press 4 to receive payment");
			System.out.println("Press 5 to schedule a payment");
			System.out.println("Press -1 to exit");
			choice = input.nextLine();

		}
		System.out.println("Thanks for using the virtual wallet...goodbye!");
		input.close();
	}

}
