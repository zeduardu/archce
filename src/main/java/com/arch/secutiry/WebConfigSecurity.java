package com.arch.secutiry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;

	@Override // configura as solicitacoes de acesso por http
	protected void configure(HttpSecurity https) throws Exception {
		https.csrf().disable() // desativa as configurações padrão de memória
				.authorizeRequests() // permitir retringir acessos
				.antMatchers(HttpMethod.GET, "/**").permitAll() // qualquer usuario
				// .antMatchers(HttpMethod.GET, "/cadastrostakeholder").hasAnyRole("ADMIN") //só
				// quem é admin

				.anyRequest().authenticated().and().formLogin().permitAll() // permite qualquer usuario
				.loginPage("/login").defaultSuccessUrl("/welcome").failureUrl("/login?error=true").and().logout()
				.logoutSuccessUrl("/login") // Mapeia URL de Logout e invalida usuario autenticado
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	@Override // Cria autenticação do usuário com banco de dados ou em memória
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());

		/**
		 * auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
		 * .withUser("arch")
		 * .password("$2a$10$fDNyB.9kuQ1rZ8EjBjfx2u8HwoferltbJT/L4bN/KiGLb3oJMC10a")
		 * .roles("ADMIN");
		 **/
	}

	@Override // Ignora url específicas
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/materialize/**");

	}
	

	
}
