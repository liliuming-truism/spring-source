package top.truism.blog.createbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("top.truism.blog.createbean")
public class BeanCreationExample {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanCreationExample.class);

        UserService userService = applicationContext.getBean(UserService.class);

        applicationContext.close();
    }

}
