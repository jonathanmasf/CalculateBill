package be.jm.calculate.counts.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import be.jm.calculate.counts.model.CalculType;
import be.jm.calculate.counts.model.Count;
import be.jm.calculate.counts.model.Person;

public class CalculateBillTest {
	
	@Test
	public void hasPaid() {
		LocalDate date = LocalDate.now();
		
		Person a = new Person("A", "Jan", "Jaap");
		Person b = new Person("B", "An", "De Bakker");
		Person c = new Person("C", "Piet", "Knalpot");
		
		Count c1 = new Count(date, "eten", new BigDecimal("123.45"), a, null);
		
		CalculateBill calc = new CalculateBill(CalculType.TOTAL_DIVIDE_EVEN_PARTS, 
				Arrays.asList(a,b,c),
				Arrays.asList(c1));
		assertEquals(new BigDecimal("123.45"), calc.calculatePersonHasPaid(a));
		assertEquals(BigDecimal.ZERO, calc.calculatePersonHasPaid(b));
		assertEquals(BigDecimal.ZERO, calc.calculatePersonHasPaid(c));
	}

}
