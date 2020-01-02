package homework_613.employees.impl;

import homework_613.Company;
import homework_613.employees.Employee;
import homework_613.options.BonusConfig;
import homework_613.options.PaymentRates;

/**
 * Класс Manager.
 * Зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных денег компании.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class Manager implements Employee {

    private Company company;
    private Double salary;

    public Manager(Company company) {
        this.company = company;
        this.salary = getMonthSalary();
    }

    @Override
    public Double getSalary() {
        return salary;
    }

    /**
     * Метод double getMonthSalary().
     * Метод для расчета зарплаты менеджера.
     *
     * @return зарплата менеджера.
     */
    @Override
    public Double getMonthSalary() {
        return PaymentRates.MANAGER.getRate() * getRealWorkTime() +
                company.getIncome() * BonusConfig.MANAGER.getBonusSize();
    }
}
