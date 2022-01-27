package Lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

public class LambdaExample {

	Long id = 0L;
	
	@Test
	public void test()
	{
		List<Transaction> transactions = new ArrayList<>();
		
		for (int i=0; i<200; i++) {
			transactions.add(buildTransaction());
		}
		
		System.out.println(transactions);
		//Collections.sort(transactions);
	}

	private Transaction buildTransaction() {
		id ++;
		
		Transaction transaction = new Transaction();
		transaction.setId(id);
		transaction.setAmount(generateRandomAmount());
		
		long millis = ThreadLocalRandom.current().nextLong(1400000000000L, 1500000000000L);
		transaction.setDate(new Date(millis));
		
		return transaction;
	}
	
	private double generateRandomAmount() {
		double amount = ThreadLocalRandom.current().nextDouble() * 1000;
		amount = amount * 100.0;
		amount = Math.round(amount);
		return amount;
	}
}
