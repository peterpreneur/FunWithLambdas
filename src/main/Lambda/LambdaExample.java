package Lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.junit.Test;

public class LambdaExample {

	Long id = 0L;
	
	@Test
	public void test()
	{
		List<Transaction> transactions = new ArrayList<>();
		
		for (int i=0; i<10; i++) {
			transactions.add(buildTransaction());
		}
		
//		Collections.sort(transactions, new Comparator<Transaction> () {
//
//			@Override
//			public int compare(Transaction t1, Transaction t2) {
//				return t2.getDate().compareTo(t1.getDate());
//			}			
//		});
//		System.out.println(transactions);
//		
//		//Lambda
//		Collections.sort(transactions, (Transaction t1, Transaction t2) -> {return t1.getDate().compareTo(t2.getDate());});
//		System.out.println(transactions);
//
//		//Lambda - shorter by removing Transaction type
//		Collections.sort(transactions, (t1, t2) -> {return t2.getDate().compareTo(t1.getDate());});
//		System.out.println(transactions);
//		
//		//Lambda - even short by removing "return..."
//		Collections.sort(transactions, (t1, t2) -> t1.getDate().compareTo(t2.getDate()));
//		System.out.println(transactions);
		
		// A::B (A=class name, B=method name)
//		Collections.sort(transactions, Comparator.comparing(Transaction::getId));
//		System.out.println(transactions);
		
		//original forEach
//		for (Transaction transaction: transactions) {
//			System.out.println(transactions);	
//		}
		
		//forEach in Stream
//		transactions.stream()
//					.forEach((transaction) -> {System.out.println(transactions);});

		//forEach in Stream using Method Reference
//		transactions.stream()
//					.forEach(System.out::println);

		//Mapping
		long sumOfTxnIds = transactions.stream()
									.mapToLong(Transaction::getId)
									.sum();
		System.out.println(sumOfTxnIds + "\n");
		
		List<Transaction> txnsBiggerThan500 = transactions.stream()
															.filter(t -> t.getAmount() > 500.0)
															.collect(Collectors.toList());
		System.out.println(txnsBiggerThan500 + "\n");
		
		Optional<Transaction> firstTxnAfterTodayOpt = transactions.stream()
					.sorted(Comparator.comparing(Transaction::getDate))
					.filter(t -> t.getDate().after(new Date()))
					.findFirst();
		System.out.println(txnsBiggerThan500 + "\n");
		
		if(firstTxnAfterTodayOpt.isPresent()) {
			System.out.println(firstTxnAfterTodayOpt.get() + "\n");			
		}

					
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
