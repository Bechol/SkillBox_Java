package homework_613.employees;

import homework_613.Company;
import homework_613.Employee;
import homework_613.options.BonusConfig;
import homework_613.options.PaymentRates;

/**
 * Класс Operartor.
 * Зарплата складывается только из фиксированной части.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class Operator implements Employee {

    private Company company;
    private Double salary;

    public Operator(Company company) {
        this.company = company;
        this.salary = getMonthSalary();
    }

    @Override
    public Double getSalary() {
        return this.salary;
    }

    /**
     * Метод double getMonthSalary().
     * Метод для расчета зарплаты оператора.
     *
     * @return зарплата оператора.
     */
    @Override
    public Double getMonthSalary() {
        return PaymentRates.OPERATOR.getRate() * getRealWorkTime() +
                company.getIncome() * BonusConfig.OPERATOR.getBonusSize();
    }
}
