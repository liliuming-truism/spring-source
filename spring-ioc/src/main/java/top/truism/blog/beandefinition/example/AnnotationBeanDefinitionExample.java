package top.truism.blog.beandefinition.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import top.truism.blog.beandefinition.AnnotatedUserService;

@Configuration
@ComponentScan(basePackages = "top.truism.blog.beandefinition")
@ImportResource("beanDefinition.xml")
public class AnnotationBeanDefinitionExample {

    public static void main(String[] args) {

        // 1. 创建 AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 2. 注册配置类
        context.register(AnnotationBeanDefinitionExample.class);

        // 3. 启动上下文
        context.refresh();

        // 4. 获取 UserService Bean
        AnnotatedUserService userService = context.getBean(AnnotatedUserService.class);

        // 5. 调用方法
        userService.processUser();

        // 6. 关闭上下文
        context.close();
    }

}
