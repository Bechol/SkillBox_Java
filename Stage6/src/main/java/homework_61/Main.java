package homework_61;

public class Main {

    public static void main(String[] args) {
        PaymentAccount account = new PaymentAccount(160000.60);
        System.out.println(account);
        account.replenish(1534.54);
        account.withdraw(24000.45);
        DepositPaymentAccount dpa = new DepositPaymentAccount(150000.35);
        dpa.replenish(555.00);
        dpa.withdraw(14000);
        GamePaymentAccount gpa = new GamePaymentAccount(4100.00);
        gpa.replenish(800.12);
        gpa.withdraw(574.24);

    }
}
