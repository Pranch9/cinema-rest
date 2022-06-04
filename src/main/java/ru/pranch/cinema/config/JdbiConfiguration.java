package ru.pranch.cinema.config;

import javax.sql.DataSource;
import org.jdbi.v3.core.Jdbi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

@Configuration
public class JdbiConfiguration {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource driverManagerDataSource() {
    return new DriverManagerDataSource();
  }

  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource);
    return dataSourceTransactionManager;
  }

  @Bean
  public Jdbi jdbi(DataSource dataSource) {
    return Jdbi.create(new TransactionAwareDataSourceProxy(dataSource));
  }
}
