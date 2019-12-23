package homework_66;

public class Individual extends Client {

    public Individual(int bankAccount) {
        super(bankAccount);
        System.out.println("Открыт счет для физического лица. Баланс счета: " + getAmount());
    }

    @Override
    void withdraw(int sum) {
        if (sum > 0  && sum < getAmount()) {
            super.bankAccount -= sum;
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
