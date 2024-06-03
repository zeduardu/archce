package dev.arch420x0.archce.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration.
 */
@Configuration
@ComponentScan("com.thymeleafexamples.select")
public class SelectWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addFormatter(new ConcernFormatter());
        formatterRegistry.addFormatter(new StakeholderFormatter());
    }
}


