package dev.arch420x0.archce;

import jakarta.annotation.PostConstruct;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = { "dev.arch420x0.archce.*" })
@EntityScan(basePackages = "dev.arch420x0.archce.model")
@EnableJpaRepositories(basePackages = { "dev.arch420x0.archce.repository" })
@EnableTransactionManagement
@EnableWebMvc
public class ArchceApplication implements WebMvcConfigurer {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(ArchceApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.setOrder(Ordered.LOWEST_PRECEDENCE);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
		return Server.createTcpServer(
				"-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}

	@PostConstruct
	private void initDb() {
		System.out.println(String.format("****** Inserting user data in the table %s ******", "USUARIO"));
		String sqlStatements[] = {
						"INSERT INTO USUARIO(id, login, senha) VALUES (1, 'defaultuser', '$2a$12$vR9g0VGCyjEHwlvKNB6q.ubk0affCNSD0.5t70fHW02stMUJYO.sO')", //d3f@uLtuser
		};

		Arrays.asList(sqlStatements).forEach(sql -> {
			System.out.println(sql);
			jdbcTemplate.execute(sql);
		});
	}
}
