package be.jm.calculate.counts.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import be.jm.calculate.counts.model.Count;
import be.jm.calculate.counts.model.Person;

public class HowMuchMustPersonPayTotalDivideEvenPartsTest {
	
private LocalDate date = LocalDate.now();
	
	private Person a = new Person("A", "Jan", "Jaap");
	private Person b = new Person("B", "An", "De Bakker");
	private Person c = new Person("C", "Piet", "Knalpot");
	
	@Test
	public void calcul_onePersonHasPaid() {
		Count c1 = new Count(date, "eten", new BigDecimal("123.45"), a, null);
		
		HowMuchMustPersonPayTotalDivideEvenParts service = new HowMuchMustPersonPayTotalDivideEvenParts();
		BigDecimal amountToPay = service.calcul(Arrays.asList(a,b,c),
												Arrays.asList(c1),
												a);
		assertEquals(BigDecimal.ZERO, amountToPay);
		
		amountToPay = service.calcul(Arrays.asList(a,b,c),
									Arrays.asList(c1),
									b);
		assertEquals(new BigDecimal("41.15"), amountToPay);
		
		amountToPay = service.calcul(Arrays.asList(a,b,c),
									Arrays.asList(c1),
									c);
		assertEquals(new BigDecimal("41.15"), amountToPay);
	}
	
	@Test
	public void calcul_twoPersonsHasPaid() {
		Count c1 = new Count(date, "eten1", new BigDecimal("123.45"), a, null);
		Count c2 = new Count(date, "eten2", new BigDecimal("50"), b, null);
		
		HowMuchMustPersonPayTotalDivideEvenParts service = new HowMuchMustPersonPayTotalDivideEvenParts();
		BigDecimal amountToPay = service.calcul(Arrays.asList(a,b,c),
												Arrays.asList(c1, c2),
												a);
		assertEquals(BigDecimal.ZERO, amountToPay);//(123.45+50)/3=57.82 -> 123.45>57.82=0
		
		amountToPay = service.calcul(Arrays.asList(a,b,c),
									Arrays.asList(c1, c2),
									b);
		assertEquals(new BigDecimal("7.82"), amountToPay);//57.82-50=7.82
		
		amountToPay = service.calcul(Arrays.asList(a,b,c),
									Arrays.asList(c1, c2),
									c);
		assertEquals(new BigDecimal("57.82"), amountToPay);//57.82-0=57.82
	}

}
