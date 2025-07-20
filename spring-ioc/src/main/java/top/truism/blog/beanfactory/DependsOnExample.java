package top.truism.blog.beanfactory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
@Slf4j
public class DependsOnExample {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DependsOnExample.class);

        applicationContext.close();

    }

    @Bean
    @DependsOn(value = {"dataSourceInitializer"})
    public DataSource dataSource() {
        log.info("Current create bean dataSource");
        return new DataSource();
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        log.info("Current create bean dataSourceInitializer");
        return new DataSourceInitializer();
    }

    private static class DataSource {

    }

    private static class DataSourceInitializer {

    }


}
