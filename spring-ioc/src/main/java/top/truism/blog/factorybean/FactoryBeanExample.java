package top.truism.blog.factorybean;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("top.truism.blog.factorybean")
@Slf4j
public class FactoryBeanExample {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(FactoryBeanExample.class);

        PersonFactoryBean personFactoryBean = (PersonFactoryBean) applicationContext.getBean("&personFactoryBean");
        log.info("get factory bean : {}", personFactoryBean);

        Person person = (Person) applicationContext.getBean("personFactoryBean");
        log.info("get factory bean target:{}", person);

    }

}
