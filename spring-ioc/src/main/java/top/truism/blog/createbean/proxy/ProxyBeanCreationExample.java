package top.truism.blog.createbean.proxy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("top.truism.blog.createbean.proxy")
@Configuration
public class ProxyBeanCreationExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProxyBeanCreationExample.class);

        ProxyTestService proxyTestService = applicationContext.getBean(ProxyTestService.class);

        proxyTestService.doSomething();

    }

}
