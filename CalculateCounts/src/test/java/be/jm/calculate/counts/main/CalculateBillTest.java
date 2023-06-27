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
	
	private LocalDate date = LocalDate.now();
	
	private Person a = new Person("A", "Jan", "Jaap");
	private Person b = new Person("B", "An", "De Bakker");
	private Person c = new Person("C", "Piet", "Knalpot");
	
	
	@Test
	public void hasPaid_OnePersonPaysEverything() {
		Count c1 = new Count(date, "eten", new BigDecimal("123.45"), a, null);
		
		CalculateBill calc = new CalculateBill(CalculType.TOTAL_DIVIDE_EVEN_PARTS, 
				Arrays.asList(a,b,c),
				Arrays.asList(c1));
		assertEquals(new BigDecimal("123.45"), calc.calculatePersonHasPaid(a));
		assertEquals(BigDecimal.ZERO, calc.calculatePersonHasPaid(b));
		assertEquals(BigDecimal.ZERO, calc.calculatePersonHasPaid(c));
	}
	
	@Test
	public void hasPaid_TwoPersonsPaysTheBill() {
		Count c1 = new Count(date, "eten1", new BigDecimal("123.45"), a, null);
		Count c2 = new Count(date, "eten2", new BigDecimal("50"), b, null);
		
		CalculateBill calc = new CalculateBill(CalculType.TOTAL_DIVIDE_EVEN_PARTS, 
				Arrays.asList(a,b,c),
				Arrays.asList(c1, c2));
		assertEquals(new BigDecimal("123.45"), calc.calculatePersonHasPaid(a));
		assertEquals(new BigDecimal("50"), calc.calculatePersonHasPaid(b));
		assertEquals(BigDecimal.ZERO, calc.calculatePersonHasPaid(c));
	}
	
	@Test
	public void hasPaid_OnePersonPaysMoreThanOneBill() {
		Count c1 = new Count(date, "eten1", new BigDecimal("123.45"), a, null);
		Count c2 = new Count(date, "eten2", new BigDecimal("50"), a, null);
		
		CalculateBill calc = new CalculateBill(CalculType.TOTAL_DIVIDE_EVEN_PARTS, 
				Arrays.asList(a,b,c),
				Arrays.asList(c1, c2));
		assertEquals(new BigDecimal("173.45"), calc.calculatePersonHasPaid(a));
		assertEquals(BigDecimal.ZERO, calc.calculatePersonHasPaid(b));
		assertEquals(BigDecimal.ZERO, calc.calculatePersonHasPaid(c));
	}
	
	//@Test
	public void whatMustToBePaid() {
		Count c1 = new Count(date, "eten", new BigDecimal("123.45"), a, null);
		
		CalculateBill calc = new CalculateBill(CalculType.TOTAL_DIVIDE_EVEN_PARTS, 
				Arrays.asList(a,b,c),
				Arrays.asList(c1));
		//calc.personMustPay(a);
	}

}
