package top.truism.blog.beanfactory;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireBeanFactoryExample {
    public static void main(String[] args) {
        // 创建 ApplicationContext
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("autowire-context.xml");
        // 获取 AutowireCapableBeanFactory
        AutowireCapableBeanFactory autowireFactory = context.getAutowireCapableBeanFactory();

        // 1. 演示 createBean 方法
        SimpleService service1 = autowireFactory.createBean(SimpleService.class);
        service1.setMessage("Created by createBean");
        service1.showMessage();

        // 2. 演示 autowire 方法
        SimpleService service2 = new SimpleService();
        autowireFactory.autowire(SimpleService.class, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);
        service2.setMessage("Autowired by name");
        service2.showMessage();

        // 3. 演示 initializeBean 方法
        SimpleService service3 = new SimpleService();
        service3.setMessage("Before initialization");
        SimpleService initializedBean = (SimpleService) autowireFactory.initializeBean(service3, "service3");
        initializedBean.showMessage();

        // 4. 演示 autowireBean 方法
        SimpleService service4 = new SimpleService();
        autowireFactory.autowireBean(service4);
        service4.setMessage("Autowired automatically");
        service4.showMessage();

        // 5. 演示 configureBean 方法
        SimpleService service5 = new SimpleService();
        autowireFactory.configureBean(service5, "simpleService");
        service5.setMessage("Configured by Spring");
        service5.showMessage();

        context.close();
    }
}
