package homework_61;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class GamePaymentAccount extends PaymentAccount {

    private LocalDate lastExpenceDate;

    public GamePaymentAccount(double amount) {
        super(amount);
        lastExpenceDate = LocalDate.of(2019, Month.NOVEMBER, 15);
    }

    public LocalDate getLastExpenceDate() {
        return lastExpenceDate;
    }

    @Override
    void startMessage() {
        System.out.println("Открыт игровой расчетный " + CURRENCY + " счет № " + getAccountId() +
                ". Баланс: " + getAmount() + CURRENCY);
    }

    @Override
    public void replenish(double sum) {
        super.replenish(sum);
    }

    @Override
    public void withdraw(double sum) {
        if (sum > 0 && getAmount() >= sum) {
            setAmount(getAmount() - sum - getAmount() * 0.01);
            System.out.println("Баланс счета № " + getAccountId() + " уменьшен на " + sum + CURRENCY +
                    ". Остаток средств после операции: " + getAmount() + CURRENCY);
        } else {
            System.out.println("Неудалось уменьшить баланс на счете № " +
                    getAccountId() + ". Недействительная сумма.");
        }

    }

    @Override
    public String toString() {
        return "Остаток на счете " + getAccountId() +
                " составляет " + getAmount() + CURRENCY;
    }
}
