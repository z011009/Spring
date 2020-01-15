package cn.wugou;

import cn.wugou.config.wugouConfig;
import cn.wugou.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void test1() {
        //如果完全使用了配置类的方式，我们就只能通过AnnotationConfig上下文来获取容器，通过配置类的class对象加载！
        ApplicationContext context = new AnnotationConfigApplicationContext(wugouConfig.class);
        User user = context.getBean("user", User.class);
        System.out.println(user.getName());
    }
}
