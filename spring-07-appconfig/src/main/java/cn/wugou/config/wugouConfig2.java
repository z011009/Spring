package cn.wugou.config;

import cn.wugou.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//这个也会被spring容器托管，注册到容器中，因为他本是也是个@Component，
//@Configuration 代表这是一个配置类，就和我们之前看的beans.xml
@Configuration
@ComponentScan("cn.wugou.pojo")
public class wugouConfig2 {

    //注册一个bean，相当于bean标签
    //这个方法的方法名，相当于bean标签中id属性
    //这个方法的返回值，相当于bean标签中class属性
    @Bean
    public User user(){
        return new User();
    }


}
