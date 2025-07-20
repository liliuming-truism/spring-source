package top.truism.blog.createbean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class UserService implements BeanNameAware, BeanFactoryAware,
    InitializingBean, DisposableBean {

    private String beanName;
    private BeanFactory beanFactory;


    public UserService() {
        System.out.println("1. UserService构造器被调用");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("2. BeanNameAware.setBeanName被调用: " + name);
        this.beanName = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3. BeanFactoryAware.setBeanFactory被调用");
        this.beanFactory = beanFactory;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("4. @PostConstruct方法被调用");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("5. InitializingBean.afterPropertiesSet被调用");
    }

    public void customInit() {
        System.out.println("6. 自定义init-method被调用");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("7. @PreDestroy方法被调用");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("8. DisposableBean.destroy被调用");
    }

    public void customDestroy() {
        System.out.println("9. 自定义destroy-method被调用");
    }
}

