package homework_66;

/**
 * Юридическое лицо
 */
public class IndividualEntrepreneur extends Client {

    public IndividualEntrepreneur(int bankAccount) {
        super(bankAccount);
        System.out.println("Открыт счет для индифидуального предпринимателя. Баланс счета: " + getAmount());
    }

    @Override
    void withdraw(int sum) {
        if (sum > 0 && sum < getAmount()) {
            super.bankAccount -= sum;
            System.out.println("Расход по счету на " + sum + ". Остаток на счете: " + getAmount());
        } else {
            System.out.println("Расход не возможен. Некорректная сумма.");
        }

    }

    @Override
    void replenish(int sum) {
        if (sum > 0 && sum < 1000) {
            super.bankAccount += sum - sum * 0.01;
            System.out.println("Пополнение счета на " + sum + ". Остаток на счете: " + getAmount() + ". Комиссия 1%: " +
                    sum * 0.01);
        } else if (sum>=1000) {
            super.bankAccount += sum - sum * 0.005;
            System.out.println("Пополнение счета на " + sum + ". Остаток на счете: " + getAmount() + ". Комиссия 0.5%: " +
                    sum * 0.005);
        }
    }
    }
