package se.comhem.web.test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackages = {"se.comhem.web.test.domain.*"})
@EnableJpaRepositories(basePackages = {"se.comhem.web.test.repositories.*"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}