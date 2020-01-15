package cn.wugou.pojo;

import lombok.Data;

@Data
public class UserT {
    private String name;

    public UserT(){
        System.out.println("UserT被创建了");
    }
}
