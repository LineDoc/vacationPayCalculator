package ru.pavlov.neostudy.vacationPayCalculator.controllers;

import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import ru.pavlov.neostudy.vacationPayCalculator.exceptions.IncorrectDateException;
import ru.pavlov.neostudy.vacationPayCalculator.services.VacationPayCalculatorService;


import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationPayCalculatorController {

    private final VacationPayCalculatorService vacationPayCalculatorService;

    @Autowired
    public VacationPayCalculatorController(VacationPayCalculatorService vacationPayCalculatorService) {
        this.vacationPayCalculatorService = vacationPayCalculatorService;
    }

    @GetMapping()
    public ResponseEntity<BigDecimal> getAmountVacationPay(@RequestParam @Min(value = 0) BigDecimal averageSalary,
                                                           @RequestParam(required = false) @Min(value = 0)BigDecimal numberForVacationDays,
                                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                                           @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDay) {

        try {
                return startDay == null & endDay == null ?
                        ResponseEntity.ok(vacationPayCalculatorService.calculationAmountVacationPay(averageSalary, numberForVacationDays)) :
                        ResponseEntity.ok(vacationPayCalculatorService.calculationAmountVacationPay(averageSalary, startDay, endDay));
            } catch (IncorrectDateException dataException) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect date.", dataException);
        }
    }
}
