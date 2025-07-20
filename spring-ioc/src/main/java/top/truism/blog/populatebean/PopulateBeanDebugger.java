package top.truism.blog.populatebean;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PopulateBeanDebugger implements InstantiationAwareBeanPostProcessor {

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof ComplexBean) {
            System.out.println("=== 实例化后处理 ===");
            System.out.println("Bean: " + beanName + " 实例化完成，准备填充属性");
        }
        return true; // 继续属性填充
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName)
        throws BeansException {
        if (bean instanceof ComplexBean) {
            System.out.println("=== 属性后处理 ===");
            System.out.println("Bean: " + beanName + " 开始处理注解注入");

            // 可以在这里修改或添加属性值
            if (pvs instanceof MutablePropertyValues) {
                MutablePropertyValues mpvs = (MutablePropertyValues) pvs;
                // 添加额外的属性值
                mpvs.add("extraProperty", "Added by PostProcessor");
            }
        }
        return pvs;
    }
}