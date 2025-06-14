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
public class ConditionalBeanDefinitionConfig {

    @Bean
    public BeanDefinitionRegistryPostProcessor conditionalBeanProcessor() {
        return new BeanDefinitionRegistryPostProcessor() {
            @Override
            public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
                throws BeansException {

                // 根据环境变量决定是否注册Bean
                String env = System.getProperty("app.env", "dev");

                if ("prod".equals(env)) {
                    BeanDefinition prodBeanDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition(BeanDefinitionUserService.class)
                        .addConstructorArgValue("Production UserService")
                        .getBeanDefinition();

                    registry.registerBeanDefinition("prodUserService", prodBeanDefinition);
                } else {
                    BeanDefinition devBeanDefinition = BeanDefinitionBuilder
                        .genericBeanDefinition(BeanDefinitionUserService.class)
                        .addConstructorArgValue("Development UserService")
                        .getBeanDefinition();

                    registry.registerBeanDefinition("devUserService", devBeanDefinition);
                }
            }

            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
                throws BeansException {
            }
        };
    }
}

