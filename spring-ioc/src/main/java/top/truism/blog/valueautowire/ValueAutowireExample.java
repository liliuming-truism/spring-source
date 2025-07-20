package top.truism.blog.valueautowire;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("top.truism.blog.valueautowire")
@Slf4j
public class ValueAutowireExample {

    @Value("${demoValue:example}")
    private String value;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ValueAutowireExample.class);

        ValueAutowireExample valueAutowireExample = applicationContext.getBean(ValueAutowireExample.class);

        log.info("vale={}", valueAutowireExample.value);
    }

}
