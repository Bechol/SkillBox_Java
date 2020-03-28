package ToDoList.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Конфиг для Swagger.
 * @see <a href="http://localhost:8080/v2/api-docs">Api docs</a>
 * @see <a href="http://localhost:8080/swagger-ui.html">SwaggerUI</a>
 * @author Oleg Bech
 * @email oleg071984@gmail.com
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiEndPointsInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ToDoList.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Spring Boot REST API")
                .description("ToDo Management REST API")
                .contact(new Contact("Oleg Bech", "", "oleg071984@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }

}