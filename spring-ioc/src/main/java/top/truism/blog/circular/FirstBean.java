package top.truism.blog.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FirstBean {

    @Autowired
    private SecondBean secondBean;

}
