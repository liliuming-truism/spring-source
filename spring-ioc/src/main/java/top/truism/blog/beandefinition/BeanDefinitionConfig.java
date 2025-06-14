package top.truism.blog.beandefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDefinitionConfig {

    @Bean
    public BeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor() {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
                throws BeansException {

                // 使用BeanDefinitionBuilder创建BeanDefinition
                BeanDefinition beanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(UserService.class)
                    .addConstructorArgValue("Dynamic UserService")
                    .setScope(BeanDefinition.SCOPE_SINGLETON)
                    .getBeanDefinition();

                // 注册到Spring容器
                registry.registerBeanDefinition("dynamicUserService", beanDefinition);
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
                throws BeansException {
                // 可以在这里修改已存在的BeanDefinition
            }
        };
    }
}
