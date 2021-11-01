package lv.andris.rest.countries.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "lv.andris.rest.countries.core")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
@EntityScan(basePackages = "lv.andris.rest.countries.core.domain")
@EnableJpaRepositories(value = "lv.andris.rest.countries.core.database")
public class SpringCoreConfiguration {


    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/db/changelog/changelog-master.xml");
        liquibase.setShouldRun(true);
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }



}