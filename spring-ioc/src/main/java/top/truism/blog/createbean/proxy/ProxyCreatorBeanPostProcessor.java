package top.truism.blog.createbean.proxy;

import java.lang.reflect.Method;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

@Component
public class ProxyCreatorBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
        throws BeansException {
        // 对特定的Bean创建代理
        if (beanClass.isAnnotationPresent(NeedProxy.class)) {
            System.out.println("为 " + beanName + " 创建代理对象");

            // 使用CGLIB创建代理
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args,
                    MethodProxy proxy) throws Throwable {
                    System.out.println("代理方法执行前: " + method.getName());
                    Object result = proxy.invokeSuper(obj, args);
                    System.out.println("代理方法执行后: " + method.getName());
                    return result;
                }
            });

            return enhancer.create();
        }
        return null;
    }
}
