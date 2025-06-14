package top.truism.blog.beandefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.truism.blog.beandefinition.xml.OrderService;

@Component("annotatedUserService")
@Scope("singleton")
public class AnnotatedUserService {

    @Value("${user.default.name:默认用户}")
    private String userName;

    @Value("${user.default.age:18}")
    private Integer age;

    @Autowired
    private OrderService orderService;

    public void processUser() {
        System.out.println("处理用户: " + userName + ", 年龄: " + age);
    }
}
