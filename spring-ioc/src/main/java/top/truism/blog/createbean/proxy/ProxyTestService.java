package top.truism.blog.createbean.proxy;

import org.springframework.stereotype.Component;

@Component
@NeedProxy
public class ProxyTestService {
    public void doSomething() {
        System.out.println("执行业务逻辑");
    }
}
