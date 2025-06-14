package top.truism.blog.beandefinition.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import top.truism.blog.beandefinition.UserService;

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
            UserService dynamicService = context.getBean("dynamicUserService", UserService.class);
            // Hello from Dynamic UserService
            dynamicService.sayHello();

            // UserService initialized: Generic UserService
            UserService genericService = context.getBean("genericUserService", UserService.class);
            // Hello from Generic UserService
            genericService.sayHello();

            // 测试批量注册的Bean
            // Hello from Service-1
            // Hello from Service-2
            // Hello from Service-3
            for (int i = 1; i <= 3; i++) {
                UserService service = context.getBean("userService" + i, UserService.class);
                service.sayHello();
            }

            // 测试条件性注册的Bean
            if (context.containsBean("prodUserService")) {
                UserService prodService = context.getBean("prodUserService", UserService.class);
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
