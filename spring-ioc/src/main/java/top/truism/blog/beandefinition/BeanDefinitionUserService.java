package top.truism.blog.beandefinition;

import org.springframework.stereotype.Component;

@Component
public class BeanDefinitionUserService {
    private String serviceName;

    public BeanDefinitionUserService() {}

    public BeanDefinitionUserService(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void sayHello() {
        System.out.println("Hello from " + serviceName);
    }

    public void init() {
        System.out.println("UserService initialized: " + serviceName);
    }

    public void destroy() {
        System.out.println("UserService destroyed: " + serviceName);
    }
}
