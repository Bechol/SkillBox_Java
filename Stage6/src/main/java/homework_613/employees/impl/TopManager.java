package homework_613.employees.impl;

import homework_613.Company;
import homework_613.employees.Employee;
import homework_613.options.BonusConfig;
import homework_613.options.PaymentRates;

/**
 * Класс TopManager.
 * зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы,
 * если доход компании более 10 млн рублей
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class TopManager implements Employee {

    private static final double INCOME_PART = 0.05;
    private Company company;
    private Double salary;

    public TopManager(Company company) {
        this.company = company;
        this.salary = getMonthSalary();
    }

    @Override
    public Double getSalary() {
        return this.salary;
    }

    /**
     * Метод double getMonthSalary().
     * Метод для расчета зарплаты топ-менеджера.
     *
     * @return зарплата топ-менеджера.
     */
    @Override
    public Double getMonthSalary() {
        if (company.getIncome() > 10000000.00)
            return PaymentRates.TOP_MANAGER.getRate() * getRealWorkTime() +
                    company.getIncome() * BonusConfig.TOP_MANAGER.getBonusSize();
        return PaymentRates.TOP_MANAGER.getRate() * getRealWorkTime();
    }
}
