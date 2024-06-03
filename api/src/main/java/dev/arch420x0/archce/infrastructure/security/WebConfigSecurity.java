package dev.arch420x0.archce.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

  @Bean // Cria autenticação do usuário com banco de dados ou em memória
  protected AuthenticationManager authenticationManager(HttpSecurity http, ImplementacaoUserDetailsService implementacaoUserDetailsService) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(implementacaoUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder())
        .and()
        .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    //MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);

    http.csrf(AbstractHttpConfigurer::disable);
    http.headers(AbstractHttpConfigurer::disable);

//    http.authorizeHttpRequests(auth -> auth
//        .requestMatchers(mvc.pattern(HttpMethod.GET, "/**")).permitAll()
//        .anyRequest().authenticated()
//    );
//    http.formLogin(login -> login
//        .loginPage("/login")
//        .defaultSuccessUrl("/welcome")
//        .failureUrl("/login?error=true")
//    );
//    http.logout(logout -> logout
//        .logoutSuccessUrl("/login")
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//    );
    http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
    return http.build();
  }

  @Bean // Ignora url específicas
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers("/materialize/**");
  }
}
