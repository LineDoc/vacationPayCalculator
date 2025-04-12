package ru.pavlov.neostudy.vacationPayCalculator.services;

import org.springframework.stereotype.Service;
import ru.pavlov.neostudy.vacationPayCalculator.exceptions.IncorrectDateException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;

import static ru.pavlov.neostudy.vacationPayCalculator.util.PublicHolidaysSet.publicHolidaysSet;

@Service
public class VacationPayCalculatorService {

    private static final BigDecimal AVERAGE_DAYS_IN_MONTH = new BigDecimal(29.3);
    private static final BigDecimal NDFL_TAX_RATE = new BigDecimal(0.87);

    public BigDecimal calculationAmountVacationPay (BigDecimal averageSalary, BigDecimal numberOfVacationDays) {

        BigDecimal amountVacationPay = averageSalary
                .divide(AVERAGE_DAYS_IN_MONTH, 2, RoundingMode.HALF_UP)
                .multiply(numberOfVacationDays)
                .multiply(NDFL_TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        return amountVacationPay;
    }

    public BigDecimal calculationAmountVacationPay (BigDecimal averageSalary, LocalDate startDay, LocalDate endDay) {

        int numberPaidDays = calculateVacationDays(startDay, endDay);

        BigDecimal amountVacationPay = averageSalary
                .divide(AVERAGE_DAYS_IN_MONTH, 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(numberPaidDays))
                .multiply(NDFL_TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
        return amountVacationPay;
    }

    public int calculateVacationDays (LocalDate startDay, LocalDate endDay) throws IncorrectDateException {

        if(startDay == null || endDay == null || startDay.isAfter(endDay)) {
            throw new IncorrectDateException("Incorrect date");
        }

        int numberPaidDays = 0;
        while (!startDay.isAfter(endDay)) {
            if (!(startDay.getDayOfWeek()
                    .equals(DayOfWeek.SATURDAY) || startDay.getDayOfWeek()
                    .equals(DayOfWeek.SUNDAY)) && !publicHolidaysSet
                    .equals(MonthDay.from(startDay)))
            {
                numberPaidDays++;
            }
            startDay = startDay.plusDays(1);
        }
        return numberPaidDays;
    }

}
