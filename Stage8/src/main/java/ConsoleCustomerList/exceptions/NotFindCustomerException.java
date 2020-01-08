package ConsoleCustomerList.exceptions;

public class NotFindCustomerException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Покупатель не найден в коллекции или введенное имя не соответствует формату команды. \n" +
                "Введите команду help для получения справки";
    }
}
