package homework_66;

abstract class Client {

    int bankAccount;

    public Client(int bankAccount) {
        this.bankAccount = bankAccount;
    }

   int getAmount() {
        return this.bankAccount;
   };

    abstract void withdraw(int sum);

    abstract void replenish(int sum);
}
