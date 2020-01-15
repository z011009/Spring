package cn.wugou.service;

//真实对象
public class UserServiceImpl implements UserService {
    @Override
    public void select() {
        System.out.println("查询");
    }

    @Override
    public void delete() {
        System.out.println("删除");
    }

    @Override
    public void update() {
        System.out.println("更新");
    }

    @Override
    public void insert() {
        System.out.println("添加");
    }

    //1.改动原有的业务代码，在公司中是大忌
}
