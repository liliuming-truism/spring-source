package top.truism.blog.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class PersonFactoryBean implements FactoryBean<Person> {

    @Override
    public Person getObject() throws Exception {
        return new Person("lisi", 20);
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
