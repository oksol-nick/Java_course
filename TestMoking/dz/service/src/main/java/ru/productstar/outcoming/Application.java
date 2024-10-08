package ru.productstar.outcoming;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class Application {

	@Bean
	public DataSource h2DataSource() {
		JdbcDataSource jdbcDataSource = new JdbcDataSource();
		jdbcDataSource.setURL("jdbc:h2:./db");
		jdbcDataSource.setUser("user");
		jdbcDataSource.setPassword("password");
		return jdbcDataSource;
	}

	@Bean
	public CommandLineRunner cmd(DataSource dataSource) {

		return args -> {

			try (InputStream inputStream = this.getClass().getResource("/initial.sql").openStream()) {
				String sql = new String(inputStream.readAllBytes());
				try (
						Connection connection = dataSource.getConnection();
						Statement statement = connection.createStatement();) {
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
