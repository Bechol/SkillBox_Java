package ConsoleCustomerList.exceptions;

public class EmptyDataException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Пустые входные данные. Невозможно выполнить команду.";
    }
}
