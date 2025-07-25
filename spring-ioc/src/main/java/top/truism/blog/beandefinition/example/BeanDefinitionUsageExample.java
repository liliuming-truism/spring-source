package top.truism.blog.beandefinition.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.truism.blog.beandefinition.BeanDefinitionUserService;

@Slf4j
@Configuration
@ComponentScan("top.truism.blog.beandefinition")
public class BeanDefinitionUsageExample {

    public static void main(String[] args) {
        // 设置环境变量
        System.setProperty("app.env", "prod");

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BeanDefinitionUsageExample.class);

        // 测试动态注册的Bean
        try {
            BeanDefinitionUserService dynamicService = context.getBean("dynamicUserService", BeanDefinitionUserService.class);
            // Hello from Dynamic UserService
            dynamicService.sayHello();

            // UserService initialized: Generic UserService
            BeanDefinitionUserService genericService = context.getBean("genericUserService", BeanDefinitionUserService.class);
            // Hello from Generic UserService
            genericService.sayHello();

            // 测试批量注册的Bean
            // Hello from Service-1
            // Hello from Service-2
            // Hello from Service-3
            for (int i = 1; i <= 3; i++) {
                BeanDefinitionUserService service = context.getBean("beanDefinitionUserService" + i, BeanDefinitionUserService.class);
                service.sayHello();
            }

            // 测试条件性注册的Bean
            if (context.containsBean("prodUserService")) {
                BeanDefinitionUserService prodService = context.getBean("prodUserService", BeanDefinitionUserService.class);
                // Hello from Production UserService
                prodService.sayHello();
            }

        } catch (Exception e) {
            log.error("Error occurred while retrieving beans: ", e);
        } finally {
            context.close();
        }
    }
}
