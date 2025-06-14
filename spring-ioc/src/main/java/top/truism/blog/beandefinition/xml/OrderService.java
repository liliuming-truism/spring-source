package top.truism.blog.beandefinition.xml;

import lombok.Data;

@Data
public class OrderService {

    public OrderService() {
    }

    public OrderService(String name, UserService userService) {
        this.name = name;
        this.userService = userService;
    }

    private String name;

    private UserService userService;

}
