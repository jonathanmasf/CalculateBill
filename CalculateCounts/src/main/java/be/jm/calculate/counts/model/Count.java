package be.jm.calculate.counts.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Count(LocalDate date, String description, BigDecimal price, Person paidBy, Person whoMustPay) {

}
