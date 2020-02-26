package ToDoList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

/**
 * Класс DefaultController.
 * Контроллер для вывода текущей даты, времени и случайного числа на стартовой странице.
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@RestController
public class DefaultController {
    @RequestMapping("/")
    public String index() {
        Random random = new Random();
        return "Date: " + (new Date()).toString() + ". Random: " + random.nextLong();
    }
}
