package top.truism.blog.beandefinition.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.truism.blog.beandefinition.xml.CacheService;
import top.truism.blog.beandefinition.xml.UserService;

public class XmlBeanDefinitionExample {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("beanDefinition.xml");
        classPathXmlApplicationContext.refresh();

        UserService userService = classPathXmlApplicationContext.getBean(UserService.class);
        // 张三
        System.out.println(userService.getUserName());

        CacheService cacheService = classPathXmlApplicationContext.getBean(CacheService.class);


    }

}
