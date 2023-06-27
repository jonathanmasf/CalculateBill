package be.jm.calculate.counts.helper;

import java.math.BigDecimal;
import java.util.List;

import be.jm.calculate.counts.model.Count;
import be.jm.calculate.counts.model.Person;

public interface HowMuchMustPersonPay {
	BigDecimal calcul(List<Person> persons, List<Count> counts, Person personWhoMustPay);
	
	default BigDecimal calculateTheTotalOfAllBills(List<Count> counts) {
		return counts.stream()
						.map(Count::price)
						.reduce(BigDecimal::add)
						.orElse(BigDecimal.ZERO);
	}
	
	static BigDecimal calculatePersonHasPaid(List<Count> counts, Person personThatHasPaid) {
		return counts.stream()
				.filter(c -> personThatHasPaid.equals(c.paidBy()))
				.map(Count::price)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}
}
