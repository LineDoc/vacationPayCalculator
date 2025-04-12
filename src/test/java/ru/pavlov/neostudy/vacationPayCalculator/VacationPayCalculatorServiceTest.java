package ru.pavlov.neostudy.vacationPayCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.pavlov.neostudy.vacationPayCalculator.services.VacationPayCalculatorService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


public class VacationPayCalculatorServiceTest {

    @Test
    public void calculationAmountVacationPayShouldWithoutDates() {

        VacationPayCalculatorService vacationPayCalculatorService = new VacationPayCalculatorService();

        BigDecimal testAverageSalary = new BigDecimal(85000);
        BigDecimal testNumberOfVacationDays = new BigDecimal(15);

        double actualAmountVacationPay = vacationPayCalculatorService
                .calculationAmountVacationPay(testAverageSalary, testNumberOfVacationDays)
                .setScale(2, RoundingMode.HALF_UP).doubleValue();

        Assertions.assertEquals(37858.31, actualAmountVacationPay, 1e-9);
    }

    @Test
    public void calculationAmountVacationPayShouldWithDates() {

        VacationPayCalculatorService vacationPayCalculatorService = new VacationPayCalculatorService();

        BigDecimal testAverageSalary = new BigDecimal(85000);

        double actualAmountVacationPay = vacationPayCalculatorService
                .calculationAmountVacationPay(testAverageSalary, LocalDate.of(2025, 02, 01), LocalDate.of(2025, 02, 23))
                .setScale(2, RoundingMode.HALF_UP).doubleValue();

        Assertions.assertEquals(37858.31, actualAmountVacationPay, 1e-9);
    }

    @Test
    public void calculationsShouldBeMatch() {
        VacationPayCalculatorService vacationPayCalculatorService = new VacationPayCalculatorService();

        Assertions.assertTrue(vacationPayCalculatorService
                .calculationAmountVacationPay(new BigDecimal(85000), new BigDecimal(16)).compareTo(
                vacationPayCalculatorService.calculationAmountVacationPay(new BigDecimal(85000), LocalDate.of(2025,02, 01), LocalDate.of(2025, 02, 24))) == 0);
    }

    @Test
    public void calculationVacationDaysShouldBeTrust() {
        VacationPayCalculatorService vacationPayCalculatorService = new VacationPayCalculatorService();
        Assertions.assertEquals(16, vacationPayCalculatorService.calculateVacationDays(LocalDate.of(2025, 02, 01), LocalDate.of(2025, 02, 24)));
    }
}
