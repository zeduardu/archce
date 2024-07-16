package dev.arch420x0.archce.infrastructure.configuration;

import dev.arch420x0.archce.infrastructure.shortbus.Mediator;
import dev.arch420x0.archce.infrastructure.shortbus.MediatorImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MediatorConfig {
  @Bean
  public Mediator getMediator(ApplicationContext ctx) {
    return new MediatorImpl(ctx);
  }
}
