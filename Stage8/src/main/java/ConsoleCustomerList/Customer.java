package ConsoleCustomerList;

/**
 * Класс Customer.
 * Реализация покупателя.
 * @author SkillBox.
 */
public class Customer
{
    private String name; //имя
    private String phone; //телефон
    private String eMail; //адрес почтового ящика

    public Customer(String name, String phone, String eMail)
    {
        this.name = name;
        this.phone = phone;
        this.eMail = eMail;
    }

    public String toString()
    {
        return name + " - " + eMail + " - " + phone;
    }
}
