package demo.config;

import demo.service.DemoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.remoting.rmi.RmiServiceExporter;

import javax.sql.DataSource;

@Configuration
public class DataConfig {

    @Bean
    public JdbcOperations jdbcTemplate(DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Profile("development")
    @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
    }

    // not used
    //@Bean
    public RmiServiceExporter rmiServiceExporter(DemoService demoService) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setService(demoService);
        exporter.setServiceInterface(DemoService.class);
        exporter.setServiceName("demoService");
        //exporter.setRegistryPort("rmi.demo.com");
        //exporter.setRegistryPort(1199); //default is 1099
        return exporter;
    }
}
