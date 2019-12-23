package homework_66;

/**
 * Юридическое лицо
 */
public class LegalEntity extends Client {

    public LegalEntity(int bankAccount) {
        super(bankAccount);
        System.out.println("Открыт счет для юридического лица. Баланс счета: " + getAmount());
    }

    @Override
    void withdraw(int sum) {
        if (sum > 0 && sum <= super.bankAccount) {
            super.bankAccount -= sum - super.bankAccount * 0.01;
            System.out.println("Расход по счету на " + sum + ". Остаток на счете: " + getAmount());
        } else {
            System.out.println("Расход не возможен. Некорректная сумма.");
        }
    }

    @Override
    void replenish(int sum) {
        if (sum > 0) {
            super.bankAccount += sum;
            System.out.println("Пополнение счета на " + sum + ". Остаток на счете: " + getAmount());
        } else {
            System.out.println("Пополнение счета невозможно. Некорректная сумма.");
        }
    }
}
