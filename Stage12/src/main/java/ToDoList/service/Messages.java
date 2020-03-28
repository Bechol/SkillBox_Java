package ToDoList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Класс для выдачи сообщений, в зависимости от локали
 *
 * @author konovalovps97 created on 03.10.2019.
 */
@Component
public class Messages {

    /**
     * Класс для обращения к файлам локли по имени
     */
    private ResourceBundleMessageSource messageSource;

    /**
     * Локаль приложения
     */
    @Value("${spring.mvc.locale}")
    private String localeFromProperties;

    @Autowired
    public Messages(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * @param id id параметра в пропертях
     * @return сообщение, хранимое в пропертях
     */
    public String getMessage(String id) {
        Locale locale = new Locale(localeFromProperties);
        return messageSource.getMessage(id, null, locale);
    }

    /**
     * @param id    id параметра в пропертях
     * @param param Список параметров, которые участвуют в отображении
     * @return сообщение, хранимое в пропертях
     */
    public String getMessage(String id, Object... param) {
        Locale locale = new Locale(localeFromProperties);
        return messageSource.getMessage(id, param, locale);
    }
}
