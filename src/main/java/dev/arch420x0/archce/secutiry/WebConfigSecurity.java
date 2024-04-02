package dev.arch420x0.archce.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity {

  private static final String[] SWAGGER_WHITELIST = {
    "swagger-ui/**",
    "/v3/api-docs/**",
    "swagger-resouces/**",
    "/swagger-resources"
  };

  @Autowired
  private ImplementacaoUserDetailsService implementacaoUserDetailsService;

  @Bean // Cria autenticação do usuário com banco de dados ou em memória
  protected AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    return http.getSharedObject(AuthenticationManagerBuilder.class)
        .userDetailsService(implementacaoUserDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder())
        .and()
        .build();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http.csrf(csrf -> csrf.disable())
    //   .authorizeHttpRequests(authorize -> authorize
    //     .requestMatchers(HttpMethod.GET,
    //   "/**"
    //   ).permitAll())
    // .formLogin(login -> login
    //   .loginPage("/login")
    //   .defaultSuccessUrl("/welcome")
    //   .failureUrl("/login?error=true"))
    // .logout(logout -> logout
    //   .logoutSuccessUrl("/login")
    //   .logoutRequestMatcher(new AntPathRequestMatcher("/logout")));
    http.csrf(csrf -> csrf.disable()).authorizeHttpRequests().anyRequest().permitAll();

    return http.build();
  }

  @Bean // Ignora url específicas
  public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
    return web -> web.ignoring().requestMatchers("/materialize/**");
  }

}
