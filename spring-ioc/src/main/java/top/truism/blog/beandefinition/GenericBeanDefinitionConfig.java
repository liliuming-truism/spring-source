package top.truism.blog.beandefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericBeanDefinitionConfig {

    @Bean
    public BeanDefinitionRegistryPostProcessor genericBeanDefinitionProcessor() {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
                throws BeansException {

                // 创建GenericBeanDefinition
                GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
                beanDefinition.setBeanClass(BeanDefinitionUserService.class);
                beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);

                // 设置属性值
                MutablePropertyValues propertyValues = new MutablePropertyValues();
                propertyValues.add("serviceName", "Generic UserService");
                beanDefinition.setPropertyValues(propertyValues);

                // 设置初始化方法
                beanDefinition.setInitMethodName("init");
                beanDefinition.setDestroyMethodName("destroy");

                registry.registerBeanDefinition("genericUserService", beanDefinition);
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
                throws BeansException {
            }
        };
    }
}
