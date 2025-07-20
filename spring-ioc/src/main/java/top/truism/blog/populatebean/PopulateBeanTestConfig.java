package top.truism.blog.populatebean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class PopulateBeanTestConfig {

    @Bean
    public ComplexBean.Plugin plugin1() {
        return new ComplexBean.Plugin("Plugin1");
    }

    @Bean
    public ComplexBean.Plugin plugin2() {
        return new ComplexBean.Plugin("Plugin2");
    }
}
