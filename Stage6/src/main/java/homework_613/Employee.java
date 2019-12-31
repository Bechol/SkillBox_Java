package homework_613;

/**
 * Интерфейс Employee.
 * Содержит общие методы для классов сотрудников.
 *
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
public interface Employee {

    /**
     * Метод double getRealWorkTime().
     * Возвращает реальное количество часов, которое отработал сотрудник с учетом отгулов, больничных и т.п.
     *
     * @return реальное количество часов.
     */
    default double getRealWorkTime() {
        return 160 * Math.random();
    }

    /**
     * Метод getMonthSalary().
     * Расчитывает зарплату сотрудников.
     *
     * @return зарплата сотрудника.
     */
    Double getMonthSalary();

    /**
     * Метод getSalary().
     * Возвращает значение поля.
     *
     * @return зарплата сотрудника.
     */
    Double getSalary();
}
