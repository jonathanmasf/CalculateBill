package be.jm.calculate.counts.main;

import java.math.BigDecimal;
import java.util.List;

import be.jm.calculate.counts.helper.HowMuchMustPersonPay;
import be.jm.calculate.counts.helper.HowMuchMustPersonPayEveryonePaysOwnBill;
import be.jm.calculate.counts.helper.HowMuchMustPersonPayTotalDivideEvenParts;
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
		return HowMuchMustPersonPay.calculatePersonHasPaid(counts, person);
	}

	public BigDecimal howMuchMustPersonPay(Person person) {
		switch(calculType) {
		case EVERYONE_PAYS_OWN_BILL:
			return new HowMuchMustPersonPayEveryonePaysOwnBill().calcul(persons, counts, person);
		case TOTAL_DIVIDE_EVEN_PARTS:
			return new HowMuchMustPersonPayTotalDivideEvenParts().calcul(persons, counts, person);
		}
		return null;
	}
	
	public BigDecimal calculateTheTotalOfBills() {
		return new HowMuchMustPersonPayEveryonePaysOwnBill().calculateTheTotalOfAllBills(counts);
	}
	
}
