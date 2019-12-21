package homework_61;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class DepositPaymentAccount extends PaymentAccount {

    private LocalDate lastExpenceDate;

    public DepositPaymentAccount(double amount) {
        super(amount);
        lastExpenceDate = LocalDate.of(2019, Month.DECEMBER, 1);
    }

    public LocalDate getLastExpenceDate() {
        return lastExpenceDate;
    }

    @Override
    void startMessage() {
        System.out.println("Открыт депозитарный расчетный " + CURRENCY + " счет № " + getAccountId() +
                ". Баланс: " + getAmount() + CURRENCY);
    }

    @Override
    public void replenish(double sum) {
        super.replenish(sum);
    }

    @Override
    public void withdraw(double sum) {
        if (!(Period.between(lastExpenceDate, LocalDate.now()).getMonths() > 1)) {
            super.withdraw(sum);
            lastExpenceDate = LocalDate.now();
        } else {
            System.out.println("Операция отменена. С момента последнего расхода средств прошло меньше месяца.");
        }
    }

    @Override
    public String toString() {
        return "Остаток на счете " + getAccountId() +
                " составляет " + getAmount() + CURRENCY;
    }
}
