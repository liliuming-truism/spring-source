package top.truism.blog.beandefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class BeanDefinitionModifier implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException {

        // 获取现有的BeanDefinition
        if (beanFactory.containsBeanDefinition("userService")) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");

            // 修改作用域
            beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);

            // 添加属性值
            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.add("serviceName", "Modified UserService");

            System.out.println("Modified existing BeanDefinition for userService");
        }
    }
}

