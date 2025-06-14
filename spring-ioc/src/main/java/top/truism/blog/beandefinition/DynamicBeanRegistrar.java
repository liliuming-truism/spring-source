package top.truism.blog.beandefinition;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

@Component
public class DynamicBeanRegistrar implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException {

        if (beanFactory instanceof BeanDefinitionRegistry) {
            BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;

            // 动态创建多个Bean
            for (int i = 1; i <= 3; i++) {
                BeanDefinition beanDefinition = BeanDefinitionBuilder
                    .genericBeanDefinition(UserService.class)
                    .addPropertyValue("serviceName", "Service-" + i)
                    .setScope(BeanDefinition.SCOPE_SINGLETON)
                    .getBeanDefinition();

                registry.registerBeanDefinition("userService" + i, beanDefinition);
            }
        }
    }
}
