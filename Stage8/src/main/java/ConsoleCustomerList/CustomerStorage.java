package ConsoleCustomerList;

import ConsoleCustomerList.exceptions.EmptyDataException;
import ConsoleCustomerList.exceptions.EmptyListException;
import ConsoleCustomerList.exceptions.NotFindCustomerException;

import java.util.HashMap;

/**
 * Класс CustomerStorage.
 * Реализация хранилища для покупателей.
 *
 * @author SkillBox.
 * @student Oleg Bech
 * @email oleg071984@gmail.com
 */
public class CustomerStorage {
    private HashMap<String, Customer> storage; //коллекция покупателей.

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    /**
     * Метод void addCustomer(String data).
     * Добавление покупателя в коллекцю storage.
     *
     * @param data - входные данные.
     * @see ConsoleCustomerList.exceptions.EmptyDataException
     */
    public void addCustomer(String data) {
        if (data != null && !data.isEmpty()) {
            try {
                String[] components = data.split("\\s+");
                String name = components[0] + " " + components[1];
                storage.put(name, new Customer(name, components[3], components[2]));
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Входные данные неполные или не соответствуют формату команды. \n" +
                        "Для справки введите команду help");
            }
        } else {
            throw new EmptyDataException();
        }
    }

    /**
     * Метод void listCustomers().
     * Вывод в консоль списка покупателей.
     * При пустой коллекции выбрасывает EmptyListException.
     *
     * @see ConsoleCustomerList.exceptions.EmptyListException
     */
    public void listCustomers() {
        if (storage.values().size() != 0) {
            storage.values().forEach(System.out::println);
        } else {
            throw new EmptyListException();
        }
    }

    /**
     * Метод void removeCustomer(String name).
     * Удаляет покупателя.
     *
     * @param name - имя покупателя.
     * @see ConsoleCustomerList.exceptions.NotFindCustomerException
     * @see ConsoleCustomerList.exceptions.EmptyDataException
     */
    public void removeCustomer(String name) {
        if (name != null && !name.isEmpty()) {
            Customer tmpCustomer = new Customer(name, null, null);
            if (storage.containsValue(tmpCustomer)) {
                storage.remove(name);
                tmpCustomer = null;
            } else {
                tmpCustomer = null;
                throw new NotFindCustomerException();
            }
        } else {
            throw new EmptyDataException();
        }
    }

    /**
     * Метод int getCount().
     * Возвращает общее количество покупателей в коллекции.
     *
     * @return общее количество покупателей в коллекции.
     */
    public int getCount() {
        return storage.size();
    }
}