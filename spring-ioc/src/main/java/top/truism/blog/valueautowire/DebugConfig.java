package top.truism.blog.valueautowire;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class DebugConfig implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
        throws BeansException {

        // 获取valueAutowireExample的BeanDefinition
        BeanDefinition bd = beanFactory.getBeanDefinition("valueAutowireExample");
        PropertyValues pvs = bd.getPropertyValues();

        System.out.println("=== BeanDefinition PropertyValues Debug ===");
        System.out.println("PropertyValues数量: " + pvs.getPropertyValues().length);

        for (PropertyValue pv : pvs.getPropertyValues()) {
            log.info("属性: {} = {}", pv.getName(), pv.getValue());
        }

        // 结果：PropertyValues数量: 0
        // 没有任何@Value或@Autowired的信息！
    }
}

