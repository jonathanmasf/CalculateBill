package be.jm.calculate.counts.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import be.jm.calculate.counts.model.Count;
import be.jm.calculate.counts.model.Person;

public class HowMuchMustPersonPayTotalDivideEvenParts implements HowMuchMustPersonPay {

	@Override
	public BigDecimal calcul(List<Person> persons, List<Count> counts, Person personWhoMustPay) {
		BigDecimal totalAmountOfAllBills = calculateTheTotalOfAllBills(counts);
		BigDecimal amountOfDividedEvenParts = totalAmountOfAllBills.divide(BigDecimal.valueOf(persons.size()), 2, RoundingMode.HALF_UP);
		BigDecimal amountPersonHasPaid = HowMuchMustPersonPay.calculatePersonHasPaid(counts, personWhoMustPay);
		int comparePaidAndEvenPartsAmounts = amountPersonHasPaid.compareTo(amountOfDividedEvenParts);
		
		if(comparePaidAndEvenPartsAmounts >= 0) {
			return BigDecimal.ZERO;
		} else if (comparePaidAndEvenPartsAmounts < 0) {
			return amountOfDividedEvenParts.subtract(amountPersonHasPaid);
		}
		
		return null;
	}
	
	

}
