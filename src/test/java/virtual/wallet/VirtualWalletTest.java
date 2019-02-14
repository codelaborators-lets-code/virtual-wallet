package virtual.wallet;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Timer;

import org.junit.jupiter.api.Test;

public class VirtualWalletTest {

	VirtualWallet underTest = new VirtualWallet("user name", 200);

	@Test
	public void shouldStartWithFundsOf200() {
		int initialValue = underTest.getFunds();
		assertThat(initialValue, is(200));
	}

	@Test
	public void shouldReturnUserName() {
		String checkName = underTest.getUserName();
		assertThat(checkName, is("user name"));
	}

	@Test
	public void shouldIncreaseFundsFrom200To210WhenAddingFundsOf10() {
		underTest.addFunds(10);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(210));
	}

	@Test
	public void shouldIncreaseFundsFrom200To220WhenAddingFundsOf20() {
		underTest.addFunds(20);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(220));
	}

	@Test
	public void shouldDecreaseFundsFrom200To190WhenRemovingFundsOf10() {
		underTest.removeFunds(10);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(190));
	}

	@Test
	public void shouldDecreaseFundsFrom200To180WhenRemovingFundsOf20() {
		underTest.removeFunds(20);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(180));
	}

	@Test
	public void shouldDecreaseFundsFrom200To150WhenRemoving10For5Intervals() {
		underTest.schedulePayment(5, 10);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(150));
	}

	@Test
	public void shouldIncreaseFundsFrom200To208WhenRecievingAPaymentOf10() {
		underTest.receivePayment(10);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(208));
	}

	@Test
	public void shouldIncreaseFundsFrom200To218WhenRecievingAPaymentOf20() {
		underTest.receivePayment(20);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(218));
	}

	@Test
	public void shouldDecreaseFundsFrom200To101WhenScheduling10PaymentsOf10() {
		underTest.schedulePayment(10, 10);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(101));
	}

	@Test
	public void shouldDecreaseFundsFrom200To51WhenScheduling15PaymentsOf10() {
		underTest.schedulePayment(15, 10);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(51));
	}

	@Test
	public void shouldDecreaseFundsFrom200To2WhenScheduling20PaymentsOf10() {
		underTest.schedulePayment(20, 10);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(2));
	}
	
	@Test
	public void shouldAdd1ToFundsWith10TransactionsForATotalOf141() {
		underTest.schedulePayment(7, 10);  //-70
		underTest.addFunds(10); //+10
		underTest.removeFunds(10); //-10
		underTest.receivePayment(12); //+10
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(141));
	}
	
	@Test
	public void shouldDecreaseFundsBy10After1Second() throws InterruptedException  {
		Timer timer = new Timer();
		underTest.setScheduledAmt(10);
		timer.schedule(underTest, 0, 1000);
		Thread.sleep(1000);
		int updatedValue = underTest.getFunds();
		assertThat(updatedValue, is(190));
	}
	
	@Test
	public void shouldReturnFalseWithIncorrectPassword() {
		//update constructor to take in a password (hard code this for now as 1111)
		//on underTest call checkPassword(2222)
		//run an assertion that this would come back as false
	}
	
	@Test
	public void shouldReturnTrueWithCorrectPassword() {
		//update constructor to take in a password (hard code this for now as 1111)
		//on underTest call checkPassword(1111)
		//run an assertion that this would come back as true
	}

}
