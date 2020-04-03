package pl.honestit.coderslab.workshops.spring_mvc_rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackageClasses = AppConfig.class)
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

}
