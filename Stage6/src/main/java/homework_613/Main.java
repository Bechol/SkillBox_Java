package homework_613;

import homework_613.employees.Manager;
import homework_613.employees.Operator;
import homework_613.employees.TopManager;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Домашняя работа 6.13
 *
 * @autor Oleg Bech
 * @email oleg071984@gmail.com
 * @see package-info.java
 */
public class Main {
    public static void main(String[] args) {
        Company bmw = new Company(2000000.00);
        //нанимаем 180 операторов
        List<Employee> empList = new ArrayList<>();
        for (int i = 0; i < 180; i++)
            empList.add(new Operator(bmw));
        //нанимаем 80 менеджеров
        for (int i = 0; i < 80; i++)
            empList.add(new Manager(bmw));
        //нанимаем 10 топменеджеров
        for (int i = 0; i < 10; i++)
            empList.add(new TopManager(bmw));

        bmw.hireAll(empList);

        NumberFormat df = NumberFormat.getInstance(Locale.getDefault());

        System.out.println("===Cписок состоящий из 13 самых высоких зарплат в компании===");
        bmw.getTopSalaryStaff(13).forEach(i -> System.out.println(String.format("%,.2f руб.", i.getSalary())));
        System.out.println("===Cписок состоящий из 13 самых низких зарплат в компании===");
        bmw.getLowestSalaryStaff(13).forEach(i -> System.out.println(String.format("%,.2f руб.", i.getSalary())));
        System.out.println("===Увольняем 50% сотрудников===");
        System.out.println("Колчество сотрудников в компании до увольнения: " + bmw.getEmployees().size());
        for (int i = 0; i < empList.size() / 2; i++)
            bmw.fire(empList.get(i));
        System.out.println("Колчество сотрудников в компании после увольнения: " + bmw.getEmployees().size());
        System.out.println("===Cписок состоящий из 13 самых высоких зарплат в компании===");
        bmw.getTopSalaryStaff(13).forEach(i -> System.out.println(String.format("%,.2f руб.", i.getSalary())));
        System.out.println("===Cписок состоящий из 30 самых низких зарплат в компании===");
        bmw.getLowestSalaryStaff(30).forEach(i -> System.out.println(String.format("%,.2f руб.", i.getSalary())));

    }
}
