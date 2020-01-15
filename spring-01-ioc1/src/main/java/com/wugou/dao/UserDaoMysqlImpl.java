package com.wugou.dao;

public class UserDaoMysqlImpl implements  UserDao{
    @Override
    public void getUser() {
        System.out.println("默认mysql获取用户");
    }
}
