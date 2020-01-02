package homework_613;

import homework_613.employees.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Класс Company.
 * Реализация компании с сотрудниками и доходом.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public class Company {

    /*Прибыль компании*/
    private double income;
    /*Коллекция сотрудников компании. Штат.*/
    private List<Employee> employees = new ArrayList<>();

    public Company(double income) {
        this.income = income;
    }

    public Company(double income, List<Employee> employees) {
        this.income = income;
        this.employees = employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    /**
     * Метод boolean hire(Employee employee).
     * Найм одного сотрудника.
     *
     * @param employee соттудник.
     * @return true - если сотрудник нанят в копанию (успешно добавлен в коллекцию сотрудников).
     */
    public boolean hire(Employee employee) {
        if (employee != null)
            return this.employees.add(employee);
        return false;
    }

    /**
     * Метод boolean hireAll(List<Employee> candidates).
     * Найм списка сотрудников.
     *
     * @param candidates список кандидатов на устройство в компанию.
     * @return true - если все сотрудники из списка наняты в копанию (успешно добавлены в
     * коллекцию сотрудников из коллекции кандидатов).
     */
    public boolean hireAll(List<Employee> candidates) {
        return this.employees.addAll(candidates);
    }

    /**
     * Метод boolean fire(Employee badEmployee).
     * Увольнение сотрудника.
     *
     * @param badEmployee сотрудник, которого необходимо уволить.
     * @return true - если сотрудник уволен из компании (успешно удален из коллекции сотрудников)
     */
    public boolean fire(Employee badEmployee) {
        if (badEmployee != null)
            return employees.remove(badEmployee);
        return false;
    }

    /**
     * Метод List<Employee> getTopSalaryStaff(int count).
     * Создание коллекции [count] самых высоких зарплат компании.
     *
     * @param count количество самых высоких зарплат.
     * @return коллекция [count] самых высоких зарплат.
     */
    public List<Employee> getTopSalaryStaff(int count) {
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        List<Employee> result = new ArrayList<>();
        for (int i = 0; i < count; i++)
            result.add(employees.get(i));
        return result;
    }

    /**
     * Метод List<Employee> getTopSalaryStaff(int count).
     * Создание коллекции [count] самых низких зарплат компании.
     *
     * @param count количество самых низких зарплат.
     * @return коллекция [count] самых низких зарплат.
     */
    public List<Employee> getLowestSalaryStaff(int count) {
        employees.sort(Comparator.comparingDouble(Employee::getSalary));
        List<Employee> result = new ArrayList<>();
        for (int i = 0; i < count; i++)
            result.add(employees.get(i));
        return result;
    }
}
