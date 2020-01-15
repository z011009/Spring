package cn.wugou.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component //就是说明这个类被Spring接管了，注册到容器中
public class User {
    @Value("无垢")//属性注入值
    private String name;
}
