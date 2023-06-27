package be.jm.calculate.counts.main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.jm.calculate.counts.model.CalculType;
import be.jm.calculate.counts.model.Count;
import be.jm.calculate.counts.model.Person;

public class Main {
	public static void main(String[] args) {
		Person w = new Person("w", "W", "M");
		Person l = new Person("l", "L", "D");
		Person b = new Person("b", "B", "N");
		Person j = new Person("j", "J", "M");
		
		List<Count> bills = new ArrayList<>();
		
		//l
		//bills.add(new Count(LocalDate.of(2023, 4, 26), "voorschot aan J", new BigDecimal("300"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 20), "eten", new BigDecimal("27.2"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 22), "eten", new BigDecimal("14"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 22), "eten", new BigDecimal("50.3"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 23), "eten", new BigDecimal("33"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 23), "eten", new BigDecimal("14.8"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 24), "peage", new BigDecimal("5"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 24), "tanken Total", new BigDecimal("25"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 24), "peage", new BigDecimal("10"), l, null));
		bills.add(new Count(LocalDate.of(2023, 6, 24), "peage", new BigDecimal("5"), l, null));
		
		//b
		//bills.add(new Count(LocalDate.of(2023, 6, 20), "voorschot aan J", new BigDecimal("505"), b, null));
		bills.add(new Count(LocalDate.of(2023, 6, 19), "eten/drinken", new BigDecimal("48.8"), b, null));
		bills.add(new Count(LocalDate.of(2023, 6, 20), "eten/drinken", new BigDecimal("31.6"), b, null));
		bills.add(new Count(LocalDate.of(2023, 6, 22), "eten/drinken", new BigDecimal("14"), b, null));
		bills.add(new Count(LocalDate.of(2023, 6, 23), "eten/drinken", new BigDecimal("17"), b, null));
		
		//j
		bills.add(new Count(LocalDate.of(2023, 4, 5), "voorschot hotel", new BigDecimal("600"), j, null));
		bills.add(new Count(LocalDate.of(2023, 6, 24), "afrekening", new BigDecimal("1673.6"), j, null));
		bills.add(new Count(LocalDate.of(2023, 6, 18), "eten/drinken Stanislasplein", new BigDecimal("33.2"), j, null));
		bills.add(new Count(LocalDate.of(2023, 6, 20), "Patisserie Willy, Munster", new BigDecimal("10.8"), j, null));
		bills.add(new Count(LocalDate.of(2023, 6, 21), "Eten, drinken La Vue des Alpes, Munster", new BigDecimal("11"), j, null));
		bills.add(new Count(LocalDate.of(2023, 6, 21), "drinken, dessert, Le Markstein", new BigDecimal("33.3"), j, null));
		bills.add(new Count(LocalDate.of(2023, 6, 22), "eten/drinken Auberge du Ballon d'Alsace", new BigDecimal("71"), j, null));
		
		CalculateBill calc = new CalculateBill(CalculType.TOTAL_DIVIDE_EVEN_PARTS,
								Arrays.asList(w,l, b, j),
								bills);

		System.out.println("Het totaal van alle rekeningen: "+calc.calculateTheTotalOfBills());
		System.out.println("Het bedrag per persoon te betalen: "+calc.calculateTheTotalOfBills().divide(new BigDecimal(4)));
		System.out.println();
		
		System.out.println("W heeft betaald: "+calc.calculatePersonHasPaid(w));
		System.out.println("L heeft betaald(zonder 300 voorschot) "+calc.calculatePersonHasPaid(l));
		System.out.println("B heeft betaald(zonder 505 voorschot): "+calc.calculatePersonHasPaid(b));
		System.out.println("J heeft betaald: "+calc.calculatePersonHasPaid(j));
		System.out.println();

		System.out.println("Welk bedrag moet W nog betalen: "+calc.howMuchMustPersonPay(w));
		System.out.println("Welk bedrag moet L nog betalen: "+calc.howMuchMustPersonPay(l).subtract(new BigDecimal("300")));
		System.out.println("Welk bedrag moet B nog betalen: "+calc.howMuchMustPersonPay(b).subtract(new BigDecimal("505")));
		System.out.println("Welk bedrag moet J nog betalen: "+calc.howMuchMustPersonPay(j));
		System.out.println();
	}

}
