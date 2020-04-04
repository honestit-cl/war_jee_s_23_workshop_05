package pl.honestit.coderslab.workshops.spring_mvc_rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // Włącza beany do obsługi Swaggera
public class SwaggerConfig {

    @Bean
    public Docket api() {
        // Budujemy opis naszej aplikacji do wykorzystania w dokumentacji
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Spring MVC REST")
                .description("Moja pierwsza aplikacja w stylu REST ... wowwww")
                .build();

        // Budujemy konfigurację dokumentacji
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) // Określamy rodzaj klas do wyszukania
                .paths(PathSelectors.ant("/api/**")) // Określamy warunki ścieżek, które uwzględniamy w dokumentacji
                .build()
                .apiInfo(apiInfo);

        return docket;
    }
}
