package be.jm.calculate.counts.main;

import java.math.BigDecimal;
import java.util.List;

import be.jm.calculate.counts.model.CalculType;
import be.jm.calculate.counts.model.Count;
import be.jm.calculate.counts.model.Person;

public class CalculateBill {
	
	private CalculType calculType;
	private List<Person> persons;
	private List<Count> counts;
	
	public CalculateBill(CalculType calculType, List<Person> persons, List<Count> counts) {
		this.calculType = calculType;
		this.persons = persons;
		this.counts = counts;
	}
	
	public BigDecimal calculatePersonHasPaid(Person person) {
		return counts.stream()
				.filter(c -> person.equals(c.paidBy()))
				.map(Count::price)
				.reduce(BigDecimal::add)
				.orElse(BigDecimal.ZERO);
	}

}
