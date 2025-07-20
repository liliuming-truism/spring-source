package top.truism.blog.circular;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.Assert;

/**
 * 测试循环依赖场景，debug源码
 */
@Configuration
@ComponentScan("top.truism.blog.circular")
public class CircularBeanExample {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext
            = new AnnotationConfigApplicationContext(CircularBeanExample.class);
        FirstBean firstBean = applicationContext.getBean(FirstBean.class);

        Assert.notNull(firstBean, "first bean non null.");

    }

}
