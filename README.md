# Тестовое задание для УЦ NeoStudy (Весна 2025)
## Приложение "Калькулятор отпускных" <br/> Микросервис на SpringBoot + Java 11 c одним API: <br/> - GET "/calculacte" <br>

### Минимальные требования: <br/> Приложение принимает твою среднюю зарплату за 12 месяцев и количество дней отпуска - отвечает суммой отпускных, которые придут сотруднику. 
### Доп. задание: <br/> При запросе также можно указать точные дни ухода в отпуск, тогда должен проводиться рассчет отпускных с учётом праздников и выходных.

### Пример запроса при заданных сумме отпускных и количестве дней отпуска: <br>
http://localhost:8080/calculate?averageSalary=100000&numberForVacationDays=14

### Пример запроса при заданных сумме отпускных и датах начала и окончания отпуска (в формате "yyyy-mm-dd"): <br>
http://localhost:8080/calculate?averageSalary=100000&startDay=2025-02-27&endDay=2025-02-23
