package top.truism.blog.populatebean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PopulateBeanExample {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PopulateBeanTestConfig.class);

        ComplexBean complexBean = applicationContext.getBean(ComplexBean.class);
    }

}
