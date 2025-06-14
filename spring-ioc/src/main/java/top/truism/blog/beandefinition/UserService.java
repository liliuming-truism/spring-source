package top.truism.blog.beandefinition;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    private String serviceName;

    public UserService() {}

    public UserService(String serviceName) {
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
