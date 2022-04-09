package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({ SwaggerConfig.class })
@SpringBootApplication
public class BiBilsemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiBilsemApplication.class, args);
	}

/*	@Bean(name = "localDataSource")
	public DataSource dataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("com.mysql.jdbc.Driver");
		dataSourceBuilder.url("jdbc:mysql://127.0.0.1:3306/bibilsem_schema");
		dataSourceBuilder.username("root");
		dataSourceBuilder.password("admin123");
		return dataSourceBuilder.build();
	}

	@Bean(name = "localConnection")
	public Connection connection() {
		Connection connection = null;
		try {
			connection = dataSource().getConnection();
		} catch (SQLException e) {

		}
		return connection;
	}*/

}
