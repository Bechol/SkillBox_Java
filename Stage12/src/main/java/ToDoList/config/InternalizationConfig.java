package ToDoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Конфиг для локализации
 *
 * @author konovalovps97 created on 03.10.2019.
 */
@Configuration
public class InternalizationConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames("messages");
        return resourceBundleMessageSource;
    }
}